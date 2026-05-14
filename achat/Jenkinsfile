pipeline {
    agent any
    
    tools {
        maven 'maven3'
        jdk 'jdk11'
    }
    
    environment {
        // Variables for your project
        APP_NAME = 'achat'
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=true'
    }
    
    stages {
        stage('1. Checkout SCM') {
            steps {
                echo 'Cloning repository from GitHub...'
                git branch: 'main', 
                    url: 'https://github.com/eyaalaimi/achat-devops.git',
                    credentialsId: ''  // Leave empty for public repo
                echo '✅ Code récupéré avec succès'
            }
        }
        
        stage('2. Clean & Compile') {
            steps {
                echo 'Nettoyage et compilation du projet...'
                sh 'mvn clean compile'
                echo '✅ Compilation réussie'
            }
        }
        
        stage('3. Run Unit Tests') {
            steps {
                echo 'Exécution des tests unitaires...'
                sh 'mvn test'
                echo '✅ Tests exécutés'
            }
            post {
                always {
                    // Publish test results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('4. Package Application') {
            steps {
                echo 'Création du package JAR...'
                sh 'mvn package -DskipTests'
                echo '✅ Package créé: target/*.jar'
            }
        }
        
        stage('5. SonarQube Analysis') {
            steps {
                echo 'Analyse de qualité du code avec SonarQube...'
                // Add Sonar configuration if you have SonarQube server
                sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=your-token'
                echo '✅ Analyse SonarQube terminée'
            }
        }
        
        stage('6. Build Docker Image') {
            steps {
                echo 'Construction de l\'image Docker...'
                script {
                    docker.build("${APP_NAME}:latest", ".")
                }
                echo '✅ Image Docker construite'
            }
        }
        
        stage('7. Publish to Nexus') {
            steps {
                echo 'Publication de l\'artéfact dans Nexus...'
                sh 'mvn deploy'
                echo '✅ Artéfact publié dans Nexus'
            }
        }
    }
    
    post {
        success {
            echo '🎉 PIPELINE RÉUSSI ! 🎉'
            echo 'Build: ' + currentBuild.number
        }
        failure {
            echo '❌ PIPELINE ÉCHOUÉ ❌'
            echo 'Vérifiez les logs ci-dessus pour identifier l\'erreur'
        }
        always {
            echo 'Pipeline terminé à : ' + new Date()
        }
    }
}
