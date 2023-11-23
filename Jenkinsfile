pipeline {
    agent any
    environment {
        GIT_REPO_URL = 'https://github.com/SaraBriki/devops-deployment-lab.git'
    }
    stages{
        stage('Pull from GitHub') {
            steps {
                echo "======== FETCHING ========"
                checkout([$class: 'GitSCM', branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: GIT_REPO_URL]]])
            }
        }

        stage('Build with Maven') {
            steps {
                echo "======== MAVEN BUILD ========"
                bat 'mvn clean install'
            }
        }

        stage('Build Docker Image'){
            steps{
                echo "======== BUILDING ========"
		        bat 'docker build -t sarabriki/my-app:%BUILD_NUMBER% .'
            }
        }

        stage('Test Run Container'){
            steps{
                echo "======== TESTING ========"
		        bat 'docker run -d --name test-app sarabriki/my-app:%BUILD_NUMBER%'
            }
        }

        stage('Push to Docker Hub'){
            steps{
                echo "======== PUSHING ========"
                withCredentials([usernamePassword(
                    credentialsId: 'docker_hub_credentials',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD')
                ]) {
                    bat 'docker login -u %DOCKER_USERNAME% -p %DOCKER_PASSWORD%'
                }
                bat 'docker push sarabriki/my-app:%BUILD_NUMBER%'
            }
        }

        stage('Clean Environment'){
            steps{
                echo "======== CLEANING ========"
                bat 'docker stop test-app'
                bat 'docker rm test-app'
                bat 'docker rmi sarabriki/my-app:%BUILD_NUMBER%'
            }
        }
    }
}