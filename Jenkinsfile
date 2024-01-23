pipeline {
    environment{
        back_repository = "fangdol888onssafy/brush-buddy-spring"
        front_repository = "fangdol888onssafy/brush-buddy-vue"
        back_img_name = "brush-buddy-spring"
        front_img_name ="brush-buddy-vue"
        DOCKERHUB_CREDENTIALS = credentials("my-dockerhub-token")
        dockerImage = ''
        SSH_CONNECTION = "ubuntu@i10a205.p.ssafy.io"
    }
    agent any
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'master', credentialsId: "my-gitlab-token", url: 'https://lab.ssafy.com/s10-webmobile3-sub2/S10P12A205.git'
            }
        }
        stage('Login'){
          steps{
              sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin' // docker hub 로그인
          }
      }
      
        stage('BE - Build') {
            steps {
                dir("./Backend/brush-buddy") {
                    sh "./gradlew clean build"
                }
            }
        }
        stage('BE - Docker Image Build') {
            steps {
                dir("./Backend/brush-buddy") {
                    script{
                        dockerImage = docker.build back_repository
                    }
                }
            }
        }

        stage('BE - Docker Image Push') {
            steps {
                echo 'Push Docker'
                script{
                        dockerImage.push("latest")
                }
            }
        }

        stage('FE - Build'){
            steps{
                dir("./Frontend/brush-buddy"){
                    sh "npm install"
                    sh "npm run build"
                }
            }
        }
        stage('FE - Docker Image Build') {
            steps {
                dir("./Frontend/brush-buddy") {
                    script{
                        dockerImage = docker.build front_repository
                    }
                }
            }
        }

        stage('FE - Docker Image Push') {
            steps {
                echo 'Push Docker'
                script{
                        dockerImage.push("latest")
                }
            }
        }
        
        stage('BE - Deploy') {
            steps {
                sshagent (credentials: ['aws_key']) {
                    script{
                        sh "ssh -o StrictHostKeyChecking=no  ${SSH_CONNECTION} uptime"
                        sh "ssh -t ${SSH_CONNECTION} 'docker container prune -f'" // 오류난 컨테이너 종료
                        
                        script {
                            def existingContainerId = sh(script: "ssh -t ${SSH_CONNECTION} 'docker ps -q -f name=${back_img_name}'", returnStdout: true).trim()
                            if (existingContainerId) {
                                sh "ssh -t ${SSH_CONNECTION} 'docker stop ${existingContainerId}'"
                                sh "ssh -t ${SSH_CONNECTION} 'docker rm ${existingContainerId}'"
                            } else {
                                echo "No existing container with the name ${back_img_name} found."
                            }
                        }
                        script {
                            // 기존 이미지 삭제
                            def existingImageId = sh(script: "ssh -t ${SSH_CONNECTION} 'docker images -q ${back_repository}:latest'", returnStdout: true).trim()
                            if (existingImageId) {
                                sh "ssh -t ${SSH_CONNECTION} 'docker rmi ${existingImageId}'"
                            } else {
                                echo "No existing image with the name ${back_repository}:latest found."
                            }
                        }
                        sh "ssh -t ${SSH_CONNECTION} 'docker pull ${back_repository}:latest'"
                        sh "ssh -t ${SSH_CONNECTION} 'docker run -d -p 5000:8080 --name ${back_img_name} ${back_repository}:latest'"
                        
                    }
                }
            }     
        }
        
        stage('FE - Deploy') {
            steps {
                sshagent (credentials: ['aws_key']) {
                    script{
                        sh "ssh -o StrictHostKeyChecking=no  ${SSH_CONNECTION} uptime"
                        script {
                            def existingContainerId = sh(script: "ssh -t ${SSH_CONNECTION} 'docker ps -q -f name=${front_img_name}'", returnStdout: true).trim()
                            if (existingContainerId) {
                                sh "ssh -t ${SSH_CONNECTION} 'docker stop ${existingContainerId}'"
                                sh "ssh -t ${SSH_CONNECTION} 'docker rm ${existingContainerId}'"
                            } else {
                                echo "No existing container with the name ${front_img_name} found."
                            }
                        }
                        script {
                            // 기존 이미지 삭제
                            def existingImageId = sh(script: "ssh -t ${SSH_CONNECTION} 'docker images -q ${front_repository}:latest'", returnStdout: true).trim()
                            if (existingImageId) {
                                sh "ssh -t ${SSH_CONNECTION} 'docker rmi ${existingImageId}'"
                            } else {
                                echo "No existing image with the name ${front_repository}:latest found."
                            }
                        }
                        sh "ssh -t ${SSH_CONNECTION} 'docker pull ${front_repository}:latest'"
                        sh "ssh -t ${SSH_CONNECTION} 'docker run -d -p 80:80 --name ${front_img_name} ${front_repository}:latest'"
                    }
                }
            }     
        }
        
    }
}