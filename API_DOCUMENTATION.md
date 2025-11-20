# API Documentation - Notes d'étudiants

## Endpoints disponibles

### 1. Récupérer les notes par semestre

**Endpoint:** `GET /api/notes/etudiant/{etu}/semestre/{semestre}`

**Description:** Récupère toutes les notes d'un étudiant pour un semestre spécifique.

**Paramètres:**
- `etu` (path) : Numéro ETU de l'étudiant (ex: ETU001)
- `semestre` (path) : Nom du semestre. Utiliser des underscores pour les espaces (ex: Semestre_1 ou "Semestre 1")

**Exemples de requêtes:**
```
GET http://localhost:8080/api/notes/etudiant/ETU001/semestre/Semestre_1
GET http://localhost:8080/api/notes/etudiant/ETU001/semestre/Semestre 1
GET http://localhost:8080/api/notes/etudiant/ETU002/semestre/Semestre_3
```

**Réponse (200 OK):**
```json
{
  "etudiant": "Martin Jean",
  "etu": "ETU001",
  "periode": "Semestre 1",
  "notes": [
    {
      "codeMatiere": "MATH101",
      "intituleMatiere": "Mathematiques Fondamentales",
      "credit": 6,
      "valeur": 14.5,
      "semestre": "Semestre 1"
    },
    {
      "codeMatiere": "INFO101",
      "intituleMatiere": "Introduction a la Programmation",
      "credit": 6,
      "valeur": 16.0,
      "semestre": "Semestre 1"
    },
    {
      "codeMatiere": "PHY101",
      "intituleMatiere": "Physique Generale",
      "credit": 3,
      "valeur": 12.0,
      "semestre": "Semestre 1"
    }
  ],
  "moyenne": 14.33,
  "totalCredits": 15
}
```

### 2. Récupérer les notes par année

**Endpoint:** `GET /api/notes/etudiant/{etu}/annee/{annee}`

**Description:** Récupère toutes les notes d'un étudiant pour une année d'étude spécifique (tous les semestres de cette année).

**Paramètres:**
- `etu` (path) : Numéro ETU de l'étudiant (ex: ETU001)
- `annee` (path) : Nom de l'année d'étude. Utiliser des underscores pour les espaces (ex: Licence_1 ou "Licence 1")

**Exemples de requêtes:**
```
GET http://localhost:8080/api/notes/etudiant/ETU001/annee/Licence_1
GET http://localhost:8080/api/notes/etudiant/ETU001/annee/Licence 1
GET http://localhost:8080/api/notes/etudiant/ETU002/annee/Licence_2
GET http://localhost:8080/api/notes/etudiant/ETU001/annee/Master_1
```

**Réponse (200 OK):**
```json
{
  "etudiant": "Martin Jean",
  "etu": "ETU001",
  "periode": "Licence 1",
  "notes": [
    {
      "codeMatiere": "MATH101",
      "intituleMatiere": "Mathematiques Fondamentales",
      "credit": 6,
      "valeur": 14.5,
      "semestre": "Semestre 1"
    },
    {
      "codeMatiere": "INFO101",
      "intituleMatiere": "Introduction a la Programmation",
      "credit": 6,
      "valeur": 16.0,
      "semestre": "Semestre 1"
    },
    {
      "codeMatiere": "MATH102",
      "intituleMatiere": "Algebre Lineaire",
      "credit": 6,
      "valeur": 13.5,
      "semestre": "Semestre 2"
    },
    {
      "codeMatiere": "INFO102",
      "intituleMatiere": "Structures de Donnees",
      "credit": 6,
      "valeur": 17.0,
      "semestre": "Semestre 2"
    }
  ],
  "moyenne": 15.25,
  "totalCredits": 24
}
```

### Codes de réponse

- **200 OK** : La requête a réussi, les notes sont retournées
- **404 Not Found** : Aucune donnée trouvée (étudiant inexistant, semestre/année invalide, ou aucune note)

## Données disponibles

### Étudiants
- ETU001 : Jean Martin
- ETU002 : Marie Dubois
- ETU003 : Pierre Bernard
- ETU004 : Sophie Thomas
- ETU005 : Luc Robert

### Années d'étude
- Licence 1
- Licence 2
- Licence 3
- Master 1
- Master 2

### Semestres
- Semestre 1 (Licence 1)
- Semestre 2 (Licence 1)
- Semestre 3 (Licence 2)
- Semestre 4 (Licence 2)
- Semestre 5 (Licence 3)
- Semestre 6 (Licence 3)

## Démarrage de l'application

### Avec Docker Compose
```bash
docker-compose up -d
```

L'application sera accessible sur `http://localhost:8080`

### Arrêter l'application
```bash
docker-compose down
```

### Réinitialiser la base de données
```bash
docker-compose down -v
docker-compose up -d
```
