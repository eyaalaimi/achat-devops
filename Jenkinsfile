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
