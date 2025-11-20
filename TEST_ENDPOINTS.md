# Tests des endpoints de l'API Notes

## 1. Health Check
```bash
curl http://localhost:8080/api/health
```

## 2. Login (Authentification)
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"admin\",\"password\":\"password\"}"
```

## 3. Récupérer les notes par semestre
```bash
# Semestre 1
curl http://localhost:8080/api/notes/etudiant/ETU001/semestre/Semestre_1

# Semestre 2
curl http://localhost:8080/api/notes/etudiant/ETU001/semestre/Semestre_2

# Semestre 3
curl http://localhost:8080/api/notes/etudiant/ETU001/semestre/Semestre_3
```

## 4. Récupérer les notes par année
```bash
# Licence 1
curl http://localhost:8080/api/notes/etudiant/ETU001/annee/Licence_1

# Licence 2
curl http://localhost:8080/api/notes/etudiant/ETU001/annee/Licence_2

# Licence 3
curl http://localhost:8080/api/notes/etudiant/ETU001/annee/Licence_3
```

## 5. Test avec un étudiant inexistant (Devrait retourner 404)
```bash
curl -v http://localhost:8080/api/notes/etudiant/ETU999/semestre/Semestre_1
```

## 6. Test avec PowerShell
```powershell
# Health Check
Invoke-WebRequest -Uri "http://localhost:8080/api/health" | Select-Object -ExpandProperty Content

# Notes par semestre
Invoke-WebRequest -Uri "http://localhost:8080/api/notes/etudiant/ETU001/semestre/Semestre_1" | Select-Object -ExpandProperty Content

# Notes par année
Invoke-WebRequest -Uri "http://localhost:8080/api/notes/etudiant/ETU001/annee/Licence_1" | Select-Object -ExpandProperty Content
```

## Réponses attendues

### Succès (200)
```json
{
  "status": "success",
  "data": {
    "etudiant": "Martin Jean",
    "etu": "ETU001",
    "periode": "Semestre 1",
    "notes": [
      {
        "codeMatiere": "MATH101",
        "intituleMatiere": "Mathematiques Fondamentales",
        "credit": 6,
        "valeur": 14.50,
        "semestre": "Semestre 1"
      }
    ],
    "moyenne": 14.33,
    "totalCredits": 15
  },
  "error": null
}
```

### Erreur - Étudiant non trouvé (404)
```json
{
  "status": "error",
  "data": null,
  "error": {
    "code": "STUDENT_NOT_FOUND",
    "message": "Aucun étudiant trouvé avec le numéro ETU: ETU999",
    "details": null
  }
}
```

### Erreur - Notes non trouvées (404)
```json
{
  "status": "error",
  "data": null,
  "error": {
    "code": "NOTES_NOT_FOUND",
    "message": "Aucune note trouvée pour le semestre: Semestre 10",
    "details": null
  }
}
```
