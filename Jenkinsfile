pipeline {
    agent any

    options {
        timeout(5)
    }

    stages {
        stage('Build app') {
            agent {
                docker { image 'gradle:8.11.1-jdk23' }
            }

            steps {
                sh './gradlew clean build --no-daemon'
            }
        }

        stage('Build docker image') {
            when {
                branch 'main'
            }
            environment {
                DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
            }

            steps {
                script {
                    docker.withRegistry('https://registry-1.docker.io/v2/', 'dockerhub-credentials') {
                        def imageName = "${DOCKER_CREDENTIALS_USR}/at_app"

                        def gradleVersion = sh(script: "./gradlew -q printVersion", returnStdout: true).trim()
                        def gitCommit = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                        def imageTag = "${gradleVersion}.${gitCommit}"

                        def customImage = docker.build("${imageName}:${imageTag}")
                        customImage.push()
                        customImage.push('latest')
                    }
                }
            }
        }
    }
}
