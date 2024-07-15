pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        REGISTRY = "parkjsdev3232"
        NUMVERIFY_API_KEY = credentials('numverify-api-key')
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/park-jsdev/phone-validator'
            }
        }
        stage('Build and Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('', env.DOCKER_HUB_CREDENTIALS) {
                        def frontendImage = docker.build("${env.REGISTRY}/frontend:latest", "./frontend")
                        def backendImage = docker.build("${env.REGISTRY}/backend:latest", "./backend")
                        frontendImage.push()
                        backendImage.push()
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh 'docker-compose down'
                    sh """
                    NUMVERIFY_API_KEY=${env.NUMVERIFY_API_KEY} docker-compose up -d
                    """
                }
            }
        }
    }
}
