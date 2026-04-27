pipeline {
    agent any
    
    tools {
        // Utilisez EXACTEMENT les noms que vous voyez dans Jenkins
        maven 'M2_HOME'      // Ou 'Maven-3', 'maven3', etc.
        jdk 'JAVA_HOME'      // Ou 'JDK-11', 'jdk11', etc.
    }
    
    environment {
        SONAR_HOST_URL = "http://172.10.0.140:9000"
        MAVEN_OPTS = "-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true"
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
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                echo '=== Exécution des tests unitaires ==='
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo '=== Analyse SonarQube ==='
                script {
                    withSonarQubeEnv('sonar-server') {
                        sh 'mvn sonar:sonar -Dsonar.projectKey=achat-devops -Dsonar.projectName="Achat DevOps"'
                    }
                }
            }
        }
        
        stage('Quality Gate') {
            steps {
                echo '=== Vérification Quality Gate ==='
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '=== Création du package JAR ==='
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                echo '=== Déploiement vers Nexus ==='
                sh 'mvn deploy -DskipTests'
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
