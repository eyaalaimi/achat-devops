pipeline {
    agent any
    
    tools {
        maven 'Maven3'
        jdk 'JDK11'
    }
    
    environment {
        // SonarQube configuration
        SONAR_HOST_URL = "http://172.10.0.140:9000"
        // Désactiver l'analyse interactive pour SonarQube
        MAVEN_OPTS = "-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true"
    }
    
    stages {
        
        // Étape 1 : Récupération du code depuis GitHub
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
                    // Publier les rapports de tests JUnit
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        // Étape 4 : Analyse SonarQube
        stage('SonarQube Analysis') {
            steps {
                echo '=== Analyse de la qualité du code avec SonarQube ==='
                script {
                    withSonarQubeEnv('sonar-server') {
                        sh '''
                            mvn sonar:sonar \
                                -Dsonar.projectKey=achat-devops \
                                -Dsonar.projectName="Achat DevOps" \
                                -Dsonar.java.binaries=target/classes \
                                -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                        '''
                    }
                }
                echo 'Analyse SonarQube envoyée avec succès !'
            }
        }
        
        // Étape 5 : Attente du Quality Gate SonarQube
        stage('Quality Gate') {
            steps {
                echo '=== Vérification du Quality Gate SonarQube ==='
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
                echo 'Quality Gate vérifié avec succès !'
            }
        }
        
        // Étape 6 : Empaquetage
        stage('Package') {
            steps {
                echo '=== Création du package JAR ==='
                sh 'mvn package -DskipTests'
                echo 'Package créé avec succès !'
            }
        }
        
        // Étape 7 : Déploiement vers Nexus
        stage('Deploy to Nexus') {
            steps {
                echo '=== Déploiement de l\'artéfact vers Nexus ==='
                sh 'mvn deploy -DskipTests'
                echo 'Artéfact déployé avec succès sur Nexus !'
            }
        }
        
    }
    
    post {
        success {
            echo '╔══════════════════════════════════════════════════════════╗'
            echo '║                                                          ║'
            echo '║     ✅ PIPELINE TERMINÉ AVEC SUCCÈS !                    ║'
            echo '║                                                          ║'
            echo '║     📊 SonarQube : http://172.10.0.140:9000              ║'
            echo '║     📦 Nexus      : http://172.10.0.140:8081             ║'
            echo '║                                                          ║'
            echo '╚══════════════════════════════════════════════════════════╝'
        }
        failure {
            echo '╔══════════════════════════════════════════════════════════╗'
            echo '║                                                          ║'
            echo '║     ❌ PIPELINE ÉCHOUÉ !                                  ║'
            echo '║                                                          ║'
            echo '║     Vérifiez les logs ci-dessus pour plus de détails.    ║'
            echo '║                                                          ║'
            echo '╚══════════════════════════════════════════════════════════╝'
        }
        always {
            echo '=== Fin du pipeline ==='
        }
    }
}
