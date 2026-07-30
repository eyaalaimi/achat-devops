# Achat DevOps Project

## Description
Ce projet a pour objectif d’industrialiser, automatiser et sécuriser une application Spring Boot à travers une chaîne CI/CD complète. Il s’inscrit dans le cadre du module DevOps et vise à mettre en place les bonnes pratiques de développement, d’intégration continue, de livraison continue, de conteneurisation, de supervision et de sécurité.

Ce projet consiste à transformer une application existante en solution DevOps robuste, en intégrant des outils comme Jenkins, Docker, Sonar, Nexus, Prometheus et Grafana.

## Features

- Analyse et prise en main d’un projet existant
- Mise en place d’un pipeline CI/CD avec Jenkins
- Tests automatisés avec JUnit
- Analyse de qualité du code avec Sonar
- Gestion des artefacts avec Nexus
- Conteneurisation avec Docker et Docker Compose
- Supervision continue avec Prometheus et Grafana
- Sécurisation de la chaîne CI/CD

## Technologies Used

- Java / Spring Boot
- Maven
- Docker
- Jenkins
- SonarQube
- Nexus
- Prometheus
- Grafana
- MySQL / PostgreSQL (selon configuration)

## Installation

```bash
git clone https://github.com/yourusername/achat-devops-project.git
cd achat-devops-project
./mvnw clean install
```

## Run the project

```bash
./mvnw spring-boot:run
```

ou

```bash
docker-compose up --build
```

## Project Structure

```text
achat/
src/
  main/
    java/
    resources/
  test/
Dockerfile
docker-compose.yml
Jenkinsfile
README.md
```

## Screenshots

(Add screenshots here)

## Author

Mariem Dridi

## Contexte pédagogique

Dans le cadre du module DevOps, l’objectif est de se familiariser avec la culture DevOps et de mettre en œuvre les différents maillons de la chaîne d’intégration continue et de livraison continue. L’évaluation repose sur 100 % de la note du projet. Le projet est réalisé en mode Scrum, par équipes de 3 à 4 étudiants, sur une durée de 6 semaines de réalisation, suivies d’une 7e semaine de validation finale sous forme de présentation orale et de démonstration technique.

Une application existante est fournie aux équipes au démarrage, à prendre en main, à améliorer, à industrialiser, à automatiser, à superviser et à sécuriser dans une logique DevOps.

## Acquis d’apprentissage

À travers ce projet, vous serez capables de :

- comprendre et appliquer les principes de la culture DevOps ;
- créer une chaîne d’intégration continue avec Jenkins ;
- créer et utiliser des conteneurs Docker pour le déploiement ;
- organiser les versions d’un projet avec Git ;
- construire un dépôt de livraison des artefacts avec Nexus ;
- tester les fonctionnalités implémentées avec JUnit ;
- interpréter les rapports de qualité de code avec Sonar ;
- construire un tableau de bord de supervision avec Prometheus et Grafana ;
- sécuriser la chaîne CI/CD.

## Objectif général du projet

Chaque équipe devra transformer l’application fournie en une solution DevOps, en mettant en place une chaîne complète et cohérente incluant :

- la gestion collaborative du code source ;
- l’intégration continue ;
- les tests automatisés ;
- l’analyse de la qualité du code ;
- la gestion des artefacts ;
- la conteneurisation et le déploiement ;
- la supervision continue ;
- la sécurisation de la chaîne CI/CD.

## Travail demandé

Le projet doit respecter les exigences suivantes :

- prise en main et compréhension du projet existant ;
- gestion du projet en mode Scrum ;
- gestion structurée du code source avec Git ;
- intégration continue avec Jenkins ;
- tests automatisés ;
- vérification de la qualité du code avec Sonar ;
- gestion des artefacts avec Nexus ;
- conteneurisation et déploiement avec Docker ;
- supervision continue avec Prometheus et Grafana ;
- sécurisation de la chaîne CI/CD.

## Déroulement du projet sur 6 semaines

- Semaine 1 : gestion de versions avec Git et cadrage du projet
- Semaine 2 : intégration continue avec Jenkins
- Semaine 3 : qualité du code avec Sonar et gestion des artefacts avec Nexus
- Semaine 4 : conteneurisation et préparation du déploiement avec Docker
- Semaine 5 : supervision continue avec Prometheus et Grafana
- Semaine 6 : sécurisation de la chaîne CI/CD

## Livrables finaux attendus

- lien du dépôt Git ;
- backlog et suivi Scrum ;
- Jenkinsfile ou script pipeline ;
- fichiers Docker ;
- documentation d’installation et d’exécution ;
- tests automatisés ;
- rapport Sonar commenté ;
- preuve de publication dans Nexus ;
- captures ou exports du monitoring ;
- rapport de sécurité de la semaine 6.
