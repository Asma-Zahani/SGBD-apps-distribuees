# SGBD-APPS-DISTRIBUEES

> Comptes rendus — Atelier Technologie de Développement et SGBD pour les Applications Distribuées II

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=flat&logo=java&logoColor=white)
![JPA](https://img.shields.io/badge/JPA%20%2F%20Hibernate-59666C?style=flat&logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)

---

## Présentation

Ce dépôt regroupe les travaux pratiques réalisés dans le cadre du module **Atelier Technologie de Développement et SGBD pour les Applications Distribuées II**. Chaque dossier correspond à une technologie d'accès aux bases de données en Java, avec les sources, configurations et exercices associés.

---

## Structure du dépôt

### `JDBC/`
Connexion directe à une base de données relationnelle via l'API JDBC.  
Gestion des `Connection`, `Statement`, `PreparedStatement`, `ResultSet` et transactions.

### `JPA/`
Mapping objet-relationnel (ORM) avec **JPA** et **Hibernate**.  
Définition des entités (`@Entity`, `@Table`, relations `@OneToMany`, `@ManyToOne`…), persistance et cycle de vie des objets.

### `JPQL/`
Requêtes orientées objet sur les entités JPA avec **JPQL**.  
Utilisation de `TypedQuery`, `NamedQuery`, jointures, agrégations et projections.

### `CommandesManager/`
Application Java de gestion de commandes en ligne basée sur **JPA / Hibernate**.  
Modélisation complète d'un système e-commerce avec gestion des stocks, validation des commandes et calcul automatique des totaux.

**Entités** : `Client`, `Produit`, `Commande`, `LigneCommande`  
**Relations** : `@OneToMany`, `@ManyToOne` avec cascade et contraintes métier  
**Fonctionnalités** :
- CRUD complet sur toutes les entités
- Vérification et décrémentation du stock avant validation de commande
- Interdiction de commande si stock insuffisant
- Calcul automatique du total par commande
- Statistiques sur les produits les plus vendus

---

## Technologies utilisées

| Technologie | Rôle |
|-------------|------|
| Java | Langage principal |
| JDBC | Accès bas niveau aux bases de données |
| JPA / Hibernate | ORM, mapping objet-relationnel, gestion des stocks |
| JPQL | Langage de requête orienté objet |
| MySQL / PostgreSQL | SGBD utilisé |
| Maven / Gradle | Gestion des dépendances |

---

## Prérequis

- Java JDK 11+
- Un SGBD installé (MySQL ou PostgreSQL)
- Maven ou Gradle
- Un IDE (IntelliJ IDEA, Eclipse…)

---

## Auteur

**Asma ZAHANI** — Étudiant en Master CCDAD  
Institut Supérieur des Études Technologiques de Sousse · DÉPARTEMENT TECHNOLOGIES DE L'INFORMATIQUE

---

> Ce dépôt est à vocation pédagogique.