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
    showSemesters() {
      this.currentView = 'SemesterList'
      this.clearSelectedData()
    },
    
    showStudents() {
      this.currentView = 'StudentsList'
    },
    
    showReleve(student, semestre, parcours = null) {
      this.selectedStudent = student
      this.selectedSemestre = semestre
      this.selectedParcours = parcours
      this.currentView = 'ReleveNotes'
    },
    
    showEtudiantInfo(student) {
      this.selectedStudent = student
      this.currentView = 'EtudiantInfo'
    },
    
    showNotesAnnee(student, annee) {
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
      // Éviter les appels en double
      if (this.loading) {
        console.log('Appel en cours, ignoré:', url)
        return null
      }
      
      this.loading = true
      this.error = null
      try {
        console.log('Fetching:', url)
        // Ajouter un timeout de 10 secondes
        const controller = new AbortController()
        const timeoutId = setTimeout(() => controller.abort(), 10000)
        
        const response = await fetch(url, { 
          signal: controller.signal,
          headers: {
            'Content-Type': 'application/json'
          }
        })
        
        clearTimeout(timeoutId)
        
        if (!response.ok) {
          throw new Error(`Erreur HTTP: ${response.status}`)
        }
        
        const data = await response.json()
        console.log('Données reçues:', data)
        
        // Gestion de ApiResponse<T>
        if (data && data.data !== undefined) {
          return data.data
        }
        return data
      } catch (err) {
        if (err.name === 'AbortError') {
          this.error = 'Timeout: Le serveur ne répond pas. Vérifiez que le backend Docker est démarré.'
        } else {
          this.error = `Erreur de chargement: ${err.message}. Vérifiez que le backend est accessible sur http://localhost:8080`
        }
        console.error('Erreur fetch:', err)
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
    <header class="app-header">
      <h1>🎓 Gestion des Notes</h1>
      <nav class="app-nav">
        <button @click="showHome" class="btn-outline">🏠 Accueil</button>
        <button @click="showSemesters" class="btn-primary">📚 Semestres</button>
        <button @click="showStudents" class="btn-secondary">👥 Étudiants</button>
      </nav>
    </header>
    
    <main class="p-4">
      <div v-if="loading" class="loading-container">
        <div class="spinner"></div>
        <p class="text-secondary">Chargement en cours...</p>
      </div>
      
      <div v-else-if="error" class="alert alert-error">
        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" width="20" height="20">
          <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd"/>
        </svg>
        <div>
          <strong>Erreur</strong>
          <p>{{ error }}</p>
        </div>
      </div>
      
      <keep-alive v-else>
        <component 
          :is="currentView"
          :key="`${currentView}-${selectedStudent?.idEtudiant}-${selectedSemestre}-${selectedAnnee}-${selectedParcours}`"
          :selectedStudent="selectedStudent"
          :selectedSemestre="selectedSemestre"
          :selectedAnnee="selectedAnnee"
          :selectedParcours="selectedParcours"
          @show-students="showStudents"
          @show-releve="showReleve"
          @show-etudiant-info="showEtudiantInfo"
          @show-notes-annee="showNotesAnnee"
          @go-back="showHome"
        />
      </keep-alive>
    </main>
  </div>
</template>
