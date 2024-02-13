pipeline {
    environment{
        back_repository = "fangdol888onssafy/brush-buddy-spring"
        front_repository = "fangdol888onssafy/brush-buddy-vue"
        ai_repository = "summa1234/brushbuddy"

        back_img_name = "brush-buddy-spring"
        front_img_name ="brush-buddy-vue"
        ai_img_name = "brush-buddy-ai"

        docker_compose_back_name = "spring"
        docker_compose_front_name = "vue"
        docker_compose_ai_name = "ai"

        DOCKERHUB_CREDENTIALS = credentials("my-dockerhub-token")
        dockerImage = ''
        SSH_CONNECTION = "ubuntu@i10a205.p.ssafy.io"
    }
    agent any
    stages {
        stage('Git Clone') {
            steps {
                git branch: 'deploy', credentialsId: "my-gitlab-token", url: 'https://lab.ssafy.com/s10-webmobile3-sub2/S10P12A205.git'
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



        stage('BE - Remove Stopped Containers') {
            steps {
                script {

                    // Find stopped containers with the specified name
                    def stoppedContainers = sh(script: "ssh -t ${SSH_CONNECTION} \'docker ps -a --filter \"name=${back_img_name}\" --filter \"status=exited\" --format \'{{.ID}}\'\'", returnStdout: true).trim()

                    // Check if there are any stopped containers
                    if (stoppedContainers) {
                        // Remove stopped containers
                        sh "ssh -t ${SSH_CONNECTION} 'docker rm ${stoppedContainers}'"
                        echo "Stopped containers with name '${back_img_name}' removed."
                    } else {
                        echo "No stopped containers with name '${back_img_name}' found."
                    }
                }
            }
        }

        stage('BE - Deploy') {
            steps {
                sshagent (credentials: ['aws_key']) {
                    script{
                        sh "ssh -o StrictHostKeyChecking=no  ${SSH_CONNECTION} uptime"
                        // sh "ssh -t ${SSH_CONNECTION} 'docker container prune -f'" // 오류난 컨테이너 종료
                        
                        //실행중인 컨테이너 종료 및 삭제
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
                        
                        //docker-compose
                        sh "ssh -t ${SSH_CONNECTION} 'docker-compose pull ${docker_compose_back_name}'"
                        sh "ssh -t ${SSH_CONNECTION} 'docker-compose up -d ${docker_compose_back_name}'"
                    }
                }
            }     
        }
        
        stage('FE - Remove Stopped Containers') {
            steps {
                script {
                    // Find stopped containers with the specified name
                    def stoppedContainers = sh(script: "ssh -t ${SSH_CONNECTION} \'docker ps -a --filter \"name=${front_img_name}\" --filter \"status=exited\" --format \'{{.ID}}\'\'", returnStdout: true).trim()

                    // Check if there are any stopped containers
                    if (stoppedContainers) {
                        // Remove stopped containers
                        sh "ssh -t ${SSH_CONNECTION} 'docker rm ${stoppedContainers}'"
                        echo "Stopped containers with name '${front_img_name}' removed."
                    } else {
                        echo "No stopped containers with name '${front_img_name}' found."
                    }
                }
            }
        }

        stage('FE - Deploy') {
            steps {
                sshagent (credentials: ['aws_key']) {
                    script{
                        sh "ssh -o StrictHostKeyChecking=no  ${SSH_CONNECTION} uptime"

                        //실행중인 컨테이너 종료 및 삭제
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

                        //docker-compose
                        sh "ssh -t ${SSH_CONNECTION} 'docker-compose pull ${docker_compose_front_name}'"
                        sh "ssh -t ${SSH_CONNECTION} 'docker-compose up -d ${docker_compose_front_name}'"
                    }
                }
            }     
        }

        stage('AI - Docker Image Build') {
            steps {
                dir("./Ai/brush-buddy") {
                    script{
                        dockerImage = docker.build ai_repository
                    }
                }
            }
        }

        stage('AI - Docker Image Push') {
            steps {
                echo 'Push Docker'
                script{
                        dockerImage.push("latest")
                }
            }
        }

         stage('AI - Deploy') {
            steps {
                sshagent (credentials: ['aws_key']) {
                    script{
                        sh "ssh -o StrictHostKeyChecking=no  ${SSH_CONNECTION} uptime"
                        //docker-compose
                        sh "ssh -t ${SSH_CONNECTION} 'docker-compose pull ${docker_compose_ai_name}'"
                        sh "ssh -t ${SSH_CONNECTION} 'docker-compose up -d ${docker_compose_ai_name}'"
                    }
                }
            }     
        }
    }

    post {
        success {
        	script {
                def Author_ID = sh(script: "git show -s --pretty=%an", returnStdout: true).trim()
                def Author_Name = sh(script: "git show -s --pretty=%ae", returnStdout: true).trim()
                mattermostSend (color: 'good', 
                message: "빌드 성공: ${env.JOB_NAME} #${env.BUILD_NUMBER} by ${Author_ID}(${Author_Name})\n(<${env.BUILD_URL}|Details>)", 
                endpoint: 'https://meeting.ssafy.com/hooks/sztjehk9gf85dgxu5cps7u8exy', 
                channel: 'jenkins'
                )
            }
        }
        failure {
        	script {
                def Author_ID = sh(script: "git show -s --pretty=%an", returnStdout: true).trim()
                def Author_Name = sh(script: "git show -s --pretty=%ae", returnStdout: true).trim()
                mattermostSend (color: 'danger', 
                message: "빌드 실패: ${env.JOB_NAME} #${env.BUILD_NUMBER} by ${Author_ID}(${Author_Name})\n(<${env.BUILD_URL}|Details>)", 
                endpoint: 'https://meeting.ssafy.com/hooks/sztjehk9gf85dgxu5cps7u8exy', 
                channel: 'jenkins'
                )
            }
        }
    }
}