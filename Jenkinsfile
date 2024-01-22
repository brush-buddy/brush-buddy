pipeline {
    agent any
    stages {
        stage("Set Variable") {
            steps {
                script {
                    JENKINS_ITEM_NAME = "brush-buddy"
                    BRANCH = "master"
                    EXECUTE_PROFILE = "dev"
                    SSH_CONNECTION = "ubuntu@{WAS_SERVER_IP}"
                    SSH_CONNECTION_CREDENTIAL = "aws_key"
                }
            }
        }
        stage('Git Clone') {
            steps {
                git branch: 'master', credentialsId: "my-gitlab-token", url: 'https://lab.ssafy.com/s10-webmobile3-sub2/S10P12A205.git'
            }
        }
        stage('BE-Build') {
            steps {
                dir("./Backend/brush-buddy") {
                    sh "./gradlew clean build"
                }
            }
        }
        stage('BE-Docker Image Build') {
            steps {
                dir("./backend/brush-buddy/dockerfile") {
                    sh "docker build -t brush-buddy-spring:1.0 ./"
                }
            }
        }

        stage('BE-Docker Image Push') {
            steps {
                sh '''
                docker tag brush-buddy-spring:1.0 {Private Registry IP}/momo-sping-application:0.0
                docker push {Private Registry IP}/momo-sping-application:0.0
                '''
            }
        }

        stage('Deploy') {
            steps {
                sshagent(credentials: [SSH_CONNECTION_CREDENTIAL]) {
                    sh "ssh -o StrictHostKeyChecking=no ${SSH_CONNECTION} uptime"
                    sh "ssh -t ${SSH_CONNECTION} 'docker rm -f momo-app'"
                    sh "ssh -t ${SSH_CONNECTION} 'docker rmi -f {Private Registry IP}/momo-sping-application:0.0'"
                    sh "ssh -t ${SSH_CONNECTION} 'docker pull {Private Registry IP}/momo-sping-application:0.0'"
                    sh "ssh -t ${SSH_CONNECTION} 'docker run -d -p 8080:8080 -v /home/ubuntu/applicationlog:/momo -e PROFILE=${EXECUTE_PROFILE} --name momo-app {Private Registry IP}/momo-sping-application:0.0'"
                }
            }     
        }

        stage('FE-Build'){
            steps{
                dir("./Frontend/brush-buddy"){
                    sh "npm install"
                    sh "npm run build"
                }
            }
        }
    }
}