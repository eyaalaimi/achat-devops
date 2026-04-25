pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                echo '=== Récupération du code depuis GitHub ==='
                checkout scm
                echo 'Code récupéré avec succès !'
            }
        }
        
        stage('Environment Info') {
            steps {
                echo '=== Informations environnement ==='
                sh '''
                    echo "Java version:"
                    java -version
                    echo "Maven version:"
                    mvn --version
                '''
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
                echo '=== Exécution des tests ==='
                sh 'mvn test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                echo '=== Création du package ==='
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Archive') {
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
