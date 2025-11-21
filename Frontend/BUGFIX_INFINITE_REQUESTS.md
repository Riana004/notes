# Correction des Requêtes Infinies

## Problème Identifié

Le frontend effectuait des requêtes infinies vers le backend à cause de la réactivité de Vue.js :

### Causes
1. **Props réactives** : La fonction `fetchData` passée en prop changeait sa référence à chaque rendu
2. **Rechargement dans `mounted()`** : Chaque composant appelait ses fonctions de chargement sans vérifier si les données étaient déjà chargées
3. **Pas de cache** : Aucun mécanisme pour éviter de refaire la même requête plusieurs fois

## Solution Implémentée

### 1. Flags de Chargement
Ajout de flags `isLoaded` dans chaque composant pour tracker si les données ont déjà été chargées :

```javascript
data() {
  return {
    students: [],
    isLoaded: false  // ✅ Nouveau flag
  }
}
```

### 2. Vérification Avant Chargement
Les méthodes de chargement vérifient maintenant si les données sont déjà chargées :

```javascript
async loadStudents() {
  if (this.isLoaded) return  // ✅ Sortie rapide
  const data = await this.fetchData(API_ENDPOINTS.etudiantsMoyennes)
  if (data) {
    this.students = data
    this.isLoaded = true  // ✅ Marquer comme chargé
  }
}
```

### 3. Clés Uniques pour Données Paramétrées
Pour les composants qui dépendent de paramètres (étudiant ID, semestre, etc.), ajout de clés uniques :

```javascript
data() {
  return {
    releve: null,
    isLoaded: false,
    loadedKey: null  // ✅ Clé unique pour cette requête
  }
}

async loadReleve() {
  const key = `${this.selectedStudent.idEtudiant}-${this.selectedSemestre}-${this.selectedParcours || ''}`
  
  if (this.isLoaded && this.loadedKey === key) {
    return  // ✅ Même requête = pas de rechargement
  }
  // ... chargement des données
  this.loadedKey = key
}
```

### 4. Key sur Composant Dynamique
Ajout d'une `key` sur le composant dynamique pour forcer la recréation uniquement lors du changement de vue :

```vue
<component 
  :is="currentView"
  :key="currentView"  <!-- ✅ Force recréation seulement si vue change -->
  ...
/>
```

## Fichiers Modifiés

- ✅ `Frontend/src/components/StudentsList.vue`
- ✅ `Frontend/src/components/SemesterList.vue`
- ✅ `Frontend/src/components/EtudiantInfo.vue`
- ✅ `Frontend/src/components/ReleveNotes.vue`
- ✅ `Frontend/src/components/NotesAnnee.vue`
- ✅ `Frontend/src/App.vue`

## Résultat Attendu

- ✅ **1 seule requête** par composant lors du premier chargement
- ✅ **Pas de requêtes infinies** même si le composant se re-rend
- ✅ **Rechargement uniquement** quand l'utilisateur change de page/étudiant/semestre
- ✅ **Performance améliorée** et charge serveur réduite

## Test

Ouvrez la console du navigateur (F12) → Onglet Network :
- Avant : Des dizaines/centaines de requêtes identiques
- Après : 1 requête par endpoint, uniquement lors du premier chargement

## Cache et Actualisation

Si vous voulez forcer un rechargement :
1. Ajoutez un bouton "Actualiser" qui reset `isLoaded = false`
2. Ou utilisez Ctrl+Shift+R pour vider le cache du navigateur
