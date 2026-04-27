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
        
        stage('Find POM and Build') {
            steps {
                echo '=== Recherche du pom.xml et compilation ==='
                script {
                    // Trouver le dossier contenant pom.xml
                    def pomDir = sh(script: "find . -name 'pom.xml' -printf '%h\n' | head -1", returnStdout: true).trim()
                    echo "pom.xml trouvé dans : ${pomDir}"
                    
                    if (pomDir.isEmpty()) {
                        error "Aucun fichier pom.xml trouvé dans le projet !"
                    }
                    
                    // Se déplacer dans le dossier du pom.xml et exécuter Maven
                    dir(pomDir) {
                        sh 'mvn clean compile'
                        sh 'mvn test'
                        sh 'mvn package -DskipTests'
                        
                        // SonarQube
                        withSonarQubeEnv('sonar-server') {
                            sh 'mvn sonar:sonar'
                        }
                        
                        // Déploiement Nexus
                        sh 'mvn deploy -DskipTests'
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
