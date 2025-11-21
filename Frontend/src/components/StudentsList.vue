<!-- components/StudentsList.vue -->
<script>
import { API_ENDPOINTS } from '../config'

export default {
  name: 'StudentsList',
  data() {
    return {
      students: [],
      isLoaded: false,
      isFetching: false,
      error: null
    }
  },
  async mounted() {
    console.log('StudentsList mounted')
    await this.loadStudents()
  },
  computed: {
    parcoursS4() {
      if (this.students.length > 0 && this.students[0].moyennesS4) {
        return Object.keys(this.students[0].moyennesS4)
      }
      return []
    }
  },
  methods: {
    async loadStudents() {
      if (this.isLoaded || this.isFetching) {
        console.log('StudentsList already loaded or fetching')
        return
      }
      
      this.isFetching = true
      this.error = null
      console.log('Fetching students from:', API_ENDPOINTS.etudiantsMoyennes)
      
      try {
        const response = await fetch(API_ENDPOINTS.etudiantsMoyennes, {
          headers: { 'Content-Type': 'application/json' }
        })
        
        if (!response.ok) throw new Error(`HTTP ${response.status}`)
        
        const result = await response.json()
        const data = result.data || result
        
        if (data) {
          this.students = data
          this.isLoaded = true
          console.log('Students loaded:', data.length)
        }
      } catch (err) {
        this.error = err.message
        console.error('Error loading students:', err)
      } finally {
        this.isFetching = false
      }
    },
    goBack() {
      this.$emit('go-back')
    },
    showReleveNotes(student, semestre, parcours = null) {
      this.$emit('show-releve', student, semestre, parcours)
    },
    showEtudiantInfo(student) {
      this.$emit('show-etudiant-info', student)
    }
  }
}
</script>

<template>
  <div class="fade-in">
    <div class="card">
      <div class="card-header">
        <h2 class="card-title">📋 Liste des Étudiants</h2>
        <p class="card-subtitle">Moyennes par semestre et parcours</p>
      </div>

      <div v-if="isFetching" class="loading-container">
        <div class="spinner"></div>
        <p class="text-secondary">Chargement des étudiants...</p>
      </div>

      <div v-else-if="error" class="alert alert-error">
        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" width="20" height="20">
          <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clip-rule="evenodd"/>
        </svg>
        <div>
          <strong>Erreur de chargement</strong>
          <p>{{ error }}</p>
          <button @click="loadStudents" class="btn-primary mt-2">Réessayer</button>
        </div>
      </div>

      <div v-else-if="students.length === 0" class="alert alert-warning">
        <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20" width="20" height="20">
          <path fill-rule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clip-rule="evenodd"/>
        </svg>
        <div>
          <strong>Aucun étudiant</strong>
          <p>Aucun étudiant trouvé dans la base de données.</p>
        </div>
      </div>

      <div v-else class="table-container">
        <table>
          <thead>
            <tr>
              <th>Matricule</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Moyenne S1</th>
              <th>Moyenne S2</th>
              <th>Moyenne S3</th>
              <th v-for="parcours in parcoursS4" :key="parcours">
                S4 {{ parcours }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="student in students" :key="student.idEtudiant" class="clickable">
              <td class="font-semibold text-primary" @click="showEtudiantInfo(student)">
                {{ student.matricule }}
              </td>
              <td>{{ student.nom }}</td>
              <td>{{ student.prenom }}</td>
              <td @click="showReleveNotes(student, 'S1')" class="clickable text-center">
                <span class="badge badge-primary">{{ student.moyenneS1 || 'N/A' }}</span>
              </td>
              <td @click="showReleveNotes(student, 'S2')" class="clickable text-center">
                <span class="badge badge-primary">{{ student.moyenneS2 || 'N/A' }}</span>
              </td>
              <td @click="showReleveNotes(student, 'S3')" class="clickable text-center">
                <span class="badge badge-primary">{{ student.moyenneS3 || 'N/A' }}</span>
              </td>
              <td 
                v-for="parcours in parcoursS4" 
                :key="parcours"
                @click="showReleveNotes(student, 'S4', parcours)"
                class="clickable text-center"
              >
                <span class="badge badge-success">
                  {{ student.moyennesS4[parcours] || 'N/A' }}
                </span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
.table-container {
  overflow-x: auto;
}

.clickable {
  cursor: pointer;
  transition: all 0.2s ease;
}

.clickable:hover {
  background-color: var(--light-bg);
}

td.clickable:hover {
  transform: scale(1.05);
}
</style>
