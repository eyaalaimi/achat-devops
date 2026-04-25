pipeline {
    agent any
    
    tools {
        // Ces noms doivent correspondre à ceux configurés dans Jenkins (Outils)
        maven 'Maven3'   // Le nom que vous avez donné dans Configuration des outils
        jdk 'JDK21'      // Le nom que vous avez donné dans Configuration des outils
    }
    
    stages {
        // Étape 1 : Récupération du code depuis Git
        stage('Checkout') {
            steps {
                echo '=== Récupération du code depuis GitHub ==='
                checkout scm
                echo 'Code récupéré avec succès !'
            }
        }
        
        // Étape 2 : Nettoyage et compilation
        stage('Clean & Compile') {
            steps {
                echo '=== Nettoyage et compilation du projet ==='
                sh 'mvn clean compile'
                echo 'Compilation terminée avec succès !'
            }
        }
        
        // Étape 3 : Exécution des tests unitaires
        stage('Test') {
            steps {
                echo '=== Exécution des tests unitaires ==='
                sh 'mvn test'
                echo 'Tests exécutés avec succès !'
            }
            post {
                always {
                    // Publier les rapports de tests
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        // Étape 4 : Empaquetage (création du JAR)
        stage('Package') {
            steps {
                echo '=== Création du package JAR ==='
                sh 'mvn package -DskipTests'
                echo 'Package créé avec succès !'
            }
        }
    }
    
    post {
        always {
            echo '=== Pipeline terminé ==='
        }
        success {
            echo '✅ BUILD SUCCESS ! Toutes les étapes sont passées.'
        }
        failure {
            echo '❌ BUILD FAILED ! Vérifiez les logs ci-dessus.'
        }
    }
}
