pipeline {
    agent any
    stages {
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