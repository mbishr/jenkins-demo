pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                 git branch: 'main', url: 'https://github.com/mbishr/jenkins-demo.git'
            }
            
        }
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                sh './mvnw clean package'
            }
            
            post {
                success {
                    junit '*target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
