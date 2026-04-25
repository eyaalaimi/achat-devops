pipeline {
    agent any
    
    tools {
        maven 'Maven3'   // À configurer dans Jenkins : Manage Jenkins → Tools
        jdk 'JDK21'      // À configurer dans Jenkins : Manage Jenkins → Tools
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo '=== Récupération du code depuis GitHub ==='
                checkout scm
                echo 'Code récupéré avec succès !'
            }
        }
        
        stage('Clean & Compile') {
            steps {
                echo '=== Nettoyage et compilation du projet ==='
                sh 'mvn clean compile'
                echo 'Compilation terminée avec succès !'
            }
        }
        
        stage('Test') {
            steps {
                echo '=== Exécution des tests unitaires ==='
                sh 'mvn test'
                echo 'Tests exécutés avec succès !'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '=== Création du package JAR ==='
                sh 'mvn package -DskipTests'
                echo 'Package créé avec succès !'
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            }
        }
    }
    
    post {
        always {
            echo '=== Pipeline terminé ==='
        }
        success {
            echo '✅ BUILD SUCCESS !'
        }
        failure {
            echo '❌ BUILD FAILED !'
        }
    }
}
