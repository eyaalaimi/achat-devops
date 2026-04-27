pipeline {
    agent any
    
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
                dir('achat') {
                    sh 'mvn clean compile'
                }
            }
        }
        
        stage('Test') {
            steps {
                dir('achat') {
                    script {
                        // Check if there are test files before running tests
                        def testFiles = findFiles(glob: 'src/test/java/**/*.java')
                        if (testFiles.size() > 0) {
                            sh 'mvn test'
                        } else {
                            echo '⚠️ No test files found - skipping test execution'
                        }
                    }
                }
            }
            post {
                always {
                    script {
                        dir('achat') {
                            // Only publish if reports exist
                            if (fileExists('target/surefire-reports/TEST-*.xml')) {
                                junit 'target/surefire-reports/*.xml'
                                echo '✅ Test reports published'
                            } else {
                                echo 'ℹ️ No test reports to publish'
                            }
                        }
                    }
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo '=== Analyse SonarQube ==='
                dir('achat') {
                    withSonarQubeEnv('sonar-server') {
                        sh 'mvn sonar:sonar'
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
                dir('achat') {
                    sh 'mvn package -DskipTests'
                }
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                echo '=== Déploiement vers Nexus ==='
                dir('achat') {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }
    }
    
    post {
        success {
            echo '╔══════════════════════════════════════════════════════════╗'
            echo '║     ✅ PIPELINE RÉUSSI !                                 ║'
            echo '║     📊 SonarQube: http://172.10.0.140:9000              ║'
            echo '║     📦 Nexus: http://172.10.0.140:8081                  ║'
            echo '╚══════════════════════════════════════════════════════════╝'
        }
        failure {
            echo '❌ PIPELINE ÉCHOUÉ ! Vérifiez les logs.'
        }
    }
}
pipeline {
    agent any
    
    environment {
        SONAR_HOST_URL = "http://172.10.0.140:9000"
        DOCKER_REGISTRY = "docker.io"  // Docker Hub
        DOCKER_IMAGE = "eyaalaimi/achat-devops"  // Remplacez par votre username Docker Hub
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
                dir('achat') {
                    sh 'mvn clean compile'
                }
            }
        }
        
        stage('Test') {
            steps {
                dir('achat') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'achat/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo '=== Analyse SonarQube ==='
                dir('achat') {
                    withSonarQubeEnv('sonar-server') {
                        sh 'mvn sonar:sonar'
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
                dir('achat') {
                    sh 'mvn package -DskipTests'
                }
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                echo '=== Déploiement vers Nexus ==='
                dir('achat') {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }
        
        // NOUVEAU STAGE 1 : Construction de l'image Docker
        stage('Docker Build') {
            steps {
                echo '=== Construction de l\'image Docker ==='
                dir('achat') {
                    script {
                        // Construire l'image avec le numéro du build comme tag
                        sh "docker build -t ${DOCKER_IMAGE}:build-${BUILD_NUMBER} ."
                        sh "docker tag ${DOCKER_IMAGE}:build-${BUILD_NUMBER} ${DOCKER_IMAGE}:latest"
                        echo "Image Docker construite : ${DOCKER_IMAGE}:build-${BUILD_NUMBER}"
                    }
                }
            }
        }
        
        // NOUVEAU STAGE 2 : Push vers Docker Hub
        stage('Docker Push') {
            steps {
                echo '=== Push de l\'image vers Docker Hub ==='
                dir('achat') {
                    script {
                        // Se connecter à Docker Hub (credentials à configurer dans Jenkins)
                        withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'DOCKER_PWD', usernameVariable: 'DOCKER_USER')]) {
                            sh "echo $DOCKER_PWD | docker login -u $DOCKER_USER --password-stdin"
                            sh "docker push ${DOCKER_IMAGE}:build-${BUILD_NUMBER}"
                            sh "docker push ${DOCKER_IMAGE}:latest"
                            echo "Image pushée avec succès sur Docker Hub"
                        }
                    }
                }
            }
        }
        
        // NOUVEAU STAGE 3 : Test du conteneur
        stage('Docker Test Run') {
            steps {
                echo '=== Test de l\'image Docker ==='
                script {
                    // Arrêter et supprimer l'ancien conteneur s'il existe
                    sh "docker stop achat-test || true"
                    sh "docker rm achat-test || true"
                    // Lancer un nouveau conteneur de test
                    sh "docker run -d --name achat-test -p 8081:8080 ${DOCKER_IMAGE}:build-${BUILD_NUMBER}"
                    // Attendre que l'application démarre
                    sh "sleep 15"
                    // Tester que le conteneur répond
                    sh "docker ps | grep achat-test"
                    echo "✅ Conteneur de test démarré avec succès sur le port 8081"
                }
            }
        }
        
        // NOUVEAU STAGE 4 : Docker Compose (optionnel)
        stage('Docker Compose') {
            steps {
                echo '=== Lancement avec Docker Compose ==='
                dir('achat') {
                    script {
                        // Arrêter l'ancienne composition
                        sh "docker compose down || true"
                        // Lancer la nouvelle composition
                        sh "docker compose up -d"
                        echo "✅ Application démarrée avec Docker Compose"
                    }
                }
            }
        }
    }
    
    post {
        success {
            echo '╔══════════════════════════════════════════════════════════════╗'
            echo '║     ✅ PIPELINE COMPLET RÉUSSI !                             ║'
            echo '║                                                              ║'
            echo '║     📊 SonarQube: http://172.10.0.140:9000                  ║'
            echo '║     📦 Nexus: http://172.10.0.140:8081                      ║'
            echo '║     🐳 Docker Hub: https://hub.docker.com/r/eyaalaimi/achat-devops ║'
            echo '║     🚀 Application: http://172.10.0.140:8081                ║'
            echo '║                                                              ║'
            echo '╚══════════════════════════════════════════════════════════════╝'
        }
        failure {
            echo '❌ PIPELINE ÉCHOUÉ ! Vérifiez les logs.'
        }
        always {
            // Nettoyage : arrêter le conteneur de test
            script {
                sh "docker stop achat-test || true"
                sh "docker rm achat-test || true"
            }
        }
    }
}
