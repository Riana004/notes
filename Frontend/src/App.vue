<!-- App.vue -->
<script>
import SemesterList from './components/SemesterList.vue'
import StudentsList from './components/StudentsList.vue'
import ReleveNotes from './components/ReleveNotes.vue'
import EtudiantInfo from './components/EtudiantInfo.vue'
import NotesAnnee from './components/NotesAnnee.vue'
import HomePage from './components/HomePage.vue'

const API_BASE = 'http://localhost:8080/api/notes'

export default {
  name: 'App',
  components: {
    SemesterList,
    StudentsList,
    ReleveNotes,
    EtudiantInfo,
    NotesAnnee,
    HomePage
  },
  data() {
    return {
      currentView: 'HomePage',
      selectedStudent: null,
      selectedSemestre: null,
      selectedAnnee: null,
      selectedParcours: null,
      loading: false,
      error: null
    }
  },
  methods: {
    async showSemesters() {
      this.currentView = 'SemesterList'
      this.clearSelectedData()
    },
    
    async showStudents() {
      this.currentView = 'StudentsList'
    },
    
    async showReleve(student, semestre, parcours = null) {
      this.selectedStudent = student
      this.selectedSemestre = semestre
      this.selectedParcours = parcours
      this.currentView = 'ReleveNotes'
    },
    
    async showEtudiantInfo(student) {
      this.selectedStudent = student
      this.currentView = 'EtudiantInfo'
    },
    
    async showNotesAnnee(student, annee) {
      this.selectedStudent = student
      this.selectedAnnee = annee
      this.currentView = 'NotesAnnee'
    },
    
    showHome() {
      this.currentView = 'HomePage'
      this.clearSelectedData()
    },
    
    clearSelectedData() {
      this.selectedStudent = null
      this.selectedSemestre = null
      this.selectedAnnee = null
      this.selectedParcours = null
    },
    
    async fetchData(url) {
      this.loading = true
      this.error = null
      try {
        const response = await fetch(url)
        if (!response.ok) throw new Error('Erreur API')
        const data = await response.json()
        return data.data // Extrait les données de ApiResponse
      } catch (err) {
        this.error = 'Erreur de chargement des données'
        console.error(err)
        return null
      } finally {
        this.loading = false
      }
    }
  }
}
</script>

<template>
  <div id="app">
    <div class="header">
      <button @click="showSemesters" class="semester-btn">Semestres</button>
    </div>
    
    <div class="main">
      <div v-if="loading" class="loading">Chargement...</div>
      <div v-else-if="error" class="error">{{ error }}</div>
      <component 
        v-else
        :is="currentView" 
        :selectedStudent="selectedStudent"
        :selectedSemestre="selectedSemestre"
        :selectedAnnee="selectedAnnee"
        :selectedParcours="selectedParcours"
        :fetchData="fetchData"
        @show-students="showStudents"
        @show-releve="showReleve"
        @show-etudiant-info="showEtudiantInfo"
        @show-notes-annee="showNotesAnnee"
        @go-back="showHome"
      />
    </div>
  </div>
</template>

<style>
/* Styles inchangés */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  height: 100%;
}

#app {
  min-height: 100vh;
  font-family: system-ui, -apple-system, sans-serif;
  background: #1a1a1a;
  color: white;
}

.header {
  padding: 15px 20px;
  background: #2d2d2d;
  border-bottom: 1px solid #444;
}

.semester-btn {
  padding: 10px 20px;
  background: #404040;
  border: 1px solid #555;
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

.semester-btn:hover {
  background: #505050;
}

.main {
  padding: 20px;
}

.loading, .error {
  text-align: center;
  padding: 40px;
  font-size: 1.2em;
}

.error {
  color: #e74c3c;
}
</style>