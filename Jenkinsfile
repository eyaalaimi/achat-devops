pipeline {
    agent any
    
    environment {
        SONAR_HOST_URL = "http://172.10.0.140:9000"
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '=== Récupération du code depuis GitHub ==='
                checkout scm
            }
        }
        
        stage('Clean & Compile') {
            steps {
                echo '=== Nettoyage et compilation ==='
                // Changez "backend" par le nom de votre sous-dossier
                dir('backend') {
                    sh 'mvn clean compile'
                }
            }
        }
        
        stage('Test') {
            steps {
                dir('backend') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                dir('backend') {
                    withSonarQubeEnv('sonar-server') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Package') {
            steps {
                dir('backend') {
                    sh 'mvn package -DskipTests'
                }
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                dir('backend') {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }
    }
    
    post {
        success {
            echo '✅ PIPELINE RÉUSSI !'
        }
        failure {
            echo '❌ PIPELINE ÉCHOUÉ !'
        }
    }
}
