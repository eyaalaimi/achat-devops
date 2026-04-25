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
                    echo ""
                    echo "=== Structure du dépôt ==="
                    ls -la
                    echo ""
                    echo "=== Dossier achat ==="
                    ls -la achat/ || echo "Dossier achat non trouvé"
                '''
            }
        }
        
        stage('Clean & Compile') {
            steps {
                echo '=== Nettoyage et compilation du projet ==='
                // Se déplacer dans le dossier achat avant d'exécuter maven
                dir('achat') {
                    sh 'mvn clean compile'
                }
                echo 'Compilation terminée avec succès !'
            }
        }
        
        stage('Test') {
            steps {
                echo '=== Exécution des tests ==='
                dir('achat') {
                    sh 'mvn test'
                }
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
                dir('achat') {
                    sh 'mvn package -DskipTests'
                }
            }
        }
        
        stage('Archive') {
            steps {
                echo '=== Archivage des artefacts ==='
                archiveArtifacts artifacts: 'achat/target/*.jar', allowEmptyArchive: true
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
