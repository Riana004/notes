# Documentation des Endpoints - API Notes

## Base URL
`http://localhost:8080/api/notes`

## Endpoints Disponibles

### 1. Liste des semestres
**GET** `/api/notes/semestres`

Retourne la liste de tous les semestres disponibles.

**Réponse:**
```json
[
  {
    "idSemestre": 1,
    "libellle": "S1"
  },
  {
    "idSemestre": 2,
    "libellle": "S2"
  }
]
```

---

### 2. Liste des étudiants avec leurs moyennes S1 à S4
**GET** `/api/notes/etudiants/moyennes`

Retourne tous les étudiants avec leurs moyennes pour chaque semestre (S1 à S4). Pour S4, les moyennes pour tous les parcours sont calculées.

**Réponse:**
```json
[
  {
    "idEtudiant": 1,
    "matricule": "ETU001",
    "nom": "Dupont",
    "prenom": "Jean",
    "moyenneS1": 12.50,
    "moyenneS2": 13.25,
    "moyenneS3": 14.00,
    "moyennesS4": {
      "Développement": 15.00,
      "Bases de Données et Réseaux": 14.50,
      "Web et Design": 13.75
    }
  }
]
```

---

### 3. Relevé de note d'un étudiant pour un semestre
**GET** `/api/notes/etudiants/{idEtudiant}/releve/{semestre}?parcours={parcours}`

Retourne le relevé de notes détaillé d'un étudiant pour un semestre donné.

**Paramètres:**
- `idEtudiant` (path) : ID de l'étudiant
- `semestre` (path) : Code du semestre (S1, S2, S3, S4)
- `parcours` (query, optionnel) : Nom du parcours (requis pour S4)
  - Valeurs possibles pour S4 :
    - "Développement"
    - "Bases de Données et Réseaux"
    - "Web et Design"

**Exemple:** `/api/notes/etudiants/1/releve/S4?parcours=Développement`

**Réponse:**
```json
{
  "idEtudiant": 1,
  "matricule": "ETU001",
  "nom": "Dupont",
  "prenom": "Jean",
  "semestre": "S4",
  "parcours": "Développement",
  "notes": [
    {
      "ue": "INF207",
      "intitule": "Éléments d'algorithmique",
      "credit": 6,
      "note": 15.00,
      "isOption": false
    },
    {
      "ue": "INF205",
      "intitule": "Système d'information",
      "credit": 6,
      "note": 14.50,
      "isOption": true
    }
  ],
  "moyenne": 14.75,
  "totalCredits": 30
}
```

**Note:** Les options sont automatiquement gérées - seule la meilleure note parmi les UE optionnelles d'un même groupe est retenue.

---

### 4. Informations d'un étudiant avec moyennes
**GET** `/api/notes/etudiants/{idEtudiant}/info`

Retourne les informations d'un étudiant avec ses moyennes pour tous les semestres. Pour S4, les moyennes pour tous les parcours sont calculées.

**Paramètres:**
- `idEtudiant` (path) : ID de l'étudiant

**Exemple:** `/api/notes/etudiants/1/info`

**Réponse:**
```json
{
  "idEtudiant": 1,
  "matricule": "ETU001",
  "nom": "Dupont",
  "prenom": "Jean",
  "moyenneS1": 12.50,
  "moyenneS2": 13.25,
  "moyenneS3": 14.00,
  "moyennesS4": {
    "Développement": 15.00,
    "Bases de Données et Réseaux": 14.50,
    "Web et Design": 13.75
  }
}
```

---

### 5. Notes par semestre pour une année universitaire
**GET** `/api/notes/etudiants/{idEtudiant}/annee/{anneeUniversitaire}`

Retourne les notes de tous les semestres d'une année universitaire (L1 ou L2) pour un étudiant. Pour S4, les notes de tous les parcours sont retournées.

**Paramètres:**
- `idEtudiant` (path) : ID de l'étudiant
- `anneeUniversitaire` (path) : Code de l'année (L1 ou L2)

**Exemple:** `/api/notes/etudiants/1/annee/L1`

**Réponse:**
```json
[
  {
    "idEtudiant": 1,
    "matricule": "ETU001",
    "nom": "Dupont",
    "prenom": "Jean",
    "anneeUniversitaire": "L1",
    "semestre": "S1",
    "notesParParcours": {
      "Connaissances Scientifiques et Techniques de Base": [
        {
          "ue": "INF101",
          "intitule": "Programmation procédurale",
          "credit": 7,
          "note": 14.00,
          "isOption": false
        }
      ]
    }
  },
  {
    "idEtudiant": 1,
    "matricule": "ETU001",
    "nom": "Dupont",
    "prenom": "Jean",
    "anneeUniversitaire": "L2",
    "semestre": "S4",
    "notesParParcours": {
      "Développement": [
        {
          "ue": "INF207",
          "intitule": "Éléments d'algorithmique",
          "credit": 6,
          "note": 15.00,
          "isOption": false
        }
      ],
      "Bases de Données et Réseaux": [...],
      "Web et Design": [...]
    }
  }
]
```

---

## Gestion des Options

### Règles de calcul des options :
- **option = 0** : Matière obligatoire (toujours prise en compte)
- **option > 0** : Matières optionnelles groupées par numéro d'option
  - Seule la meilleure note du groupe est retenue pour le calcul de la moyenne

### Exemples pour S4 - Parcours Développement :
- **Option 0** : INF207 (obligatoire), INF210 (obligatoire), MTH203 (obligatoire)
- **Option 1** : Choix entre INF204, INF205, INF206 → meilleure note retenue
- **Option 2** : Choix entre MTH204, MTH205, MTH206 → meilleure note retenue

---

## Codes d'erreur

- **404 Not Found** : Étudiant, semestre ou année universitaire non trouvé
- **500 Internal Server Error** : Erreur serveur

---

## Notes importantes

1. **Parcours S4** : Pour les endpoints retournant les moyennes, toutes les moyennes pour les 3 parcours S4 (Développement, Bases de Données et Réseaux, Web et Design) sont calculées et retournées.

2. **Calcul de moyenne** : La moyenne est pondérée par les crédits de chaque matière.

3. **Options** : Le système sélectionne automatiquement la meilleure note parmi les UE optionnelles d'un même groupe.

4. **Années universitaires** : Seules L1 et L2 sont supportées actuellement (contenant S1-S2 et S3-S4 respectivement).
