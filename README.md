# Oeuvres - Exposition du Musée des Beaux-Arts - Orléans Métropole — Réalisé par Balbino ROdrigo et Anas HENNOUZ

## Description du projet
Ce projet consiste en une API développée avec NestJS et une application Android qui permettent de visualiser des données sur les expositions et œuvres d’art présentées dans les musées en France, et plus particulièrement au Musée des Beaux-Arts d'Orléans. Ces données sont extraites de la plateforme **OpenData** et affichées de manière intuitive pour l'utilisateur.

## Fonctionnalités principales
### API NestJS
- Chargement des données OpenData au démarrage de l'API.
- Endpoints pour :
    - Obtenir un résumé des données (expositions, œuvres, etc.).
    - Consulter les détails d’une œuvre spécifique.
    - Ajouter de nouvelles œuvres ou expositions.
    - Supprimer des œuvres ou expositions existantes.
    - Mettre en favori ou retirer des favoris des éléments.
- Déploiement sur CleverCloud.

### Application Android
- Affichage des données récupérées via l’API sous trois formes :
    1. Carte interactive.
    2. Liste des expositions et œuvres disponibles.
    3. Détails d’une œuvre ou exposition spécifique.
- Fonctionnalité de mise en favoris pour les éléments.

## Spécifications techniques
### API NestJS
- **Données en JSON** avec des champs pour :
    - L’URL d’une image pour chaque œuvre ou exposition.
    - La latitude et la longitude pour les éléments géolocalisables.
- Endpoints :
    - GET /expositions : Résumé des données.
    - GET /expositions/:id : Détail d’un élément.
    - POST /expositions : Ajouter une nouvelle œuvre ou exposition.
    - DELETE /expositions/:id : Supprimer une œuvre ou exposition.
    - PUT /expositions/:id : Mise à jour du statut favori.

### Application Android
- Utilisation de Fragments pour :
    - Liste des expositions et œuvres.
    - Détails des éléments.
- Deux activités principales, incluant une Toolbar pour le rafraîchissement des données.
- Les données sont synchronisées avec l'API en temps réel.

## Dépendances utilisées
- API : NestJS, déployée sur CleverCloud.
- Application Android :
    - Retrofit : pour les appels API.
    - Google Maps API : pour l'affichage sur une carte.
    - Material Design : pour l'interface utilisateur.

## Instructions pour exécuter le projet
1. API NestJS :
    - Clonez le repository de l’API.
    - Installez les dépendances avec `npm install`.
    - Lancez l'API avec `npm run start`.

2. Application Android :
    - Clonez le repository de l'application Android.
    - Importez le projet dans Android Studio.
    - Configurez les clés nécessaires (par ex. Google Maps API).
    - Lancez l'application sur un simulateur ou un appareil Android.

## Limitations
Les fonctionnalités supplémentaires mentionnées dans les spécifications, telles que la recherche, la pagination, et le mode hors connexion, n’ont pas été implémentées.

## Réalisation
Ce projet est réalisé dans le cadre du cours ISMIN C&S par Balbino Rodrigo et Anas HENNOUZ

