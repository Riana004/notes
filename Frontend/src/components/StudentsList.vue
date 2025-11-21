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
  <div class="students-list">
    <button @click="goBack" class="back-btn">← Retour à l'accueil</button>
    
    <h2>Liste des Étudiants avec leurs Moyennes</h2>
    
    <div class="students-table">
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
          <tr v-for="student in students" :key="student.idEtudiant">
            <td class="matricule-cell" @click="showEtudiantInfo(student)">
              {{ student.matricule }}
            </td>
            <td>{{ student.nom }}</td>
            <td>{{ student.prenom }}</td>
            <td class="moyenne-cell" @click="showReleveNotes(student, 'S1')">
              {{ student.moyenneS1 }}
            </td>
            <td class="moyenne-cell" @click="showReleveNotes(student, 'S2')">
              {{ student.moyenneS2 }}
            </td>
            <td class="moyenne-cell" @click="showReleveNotes(student, 'S3')">
              {{ student.moyenneS3 }}
            </td>
            <td 
              v-for="parcours in parcoursS4" 
              :key="parcours"
              class="moyenne-cell" 
              @click="showReleveNotes(student, 'S4', parcours)"
            >
              {{ student.moyennesS4[parcours] }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
/* Styles inchangés */
.students-list {
  padding: 20px;
}

.back-btn {
  background: #95a5a6;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 20px;
}

.back-btn:hover {
  background: #7f8c8d;
}

.students-table {
  overflow-x: auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: #2d2d2d;
}

th, td {
  border: 1px solid #444;
  padding: 12px;
  text-align: center;
}

th {
  background: #404040;
  color: white;
}

.matricule-cell {
  cursor: pointer;
  background: #3a506b;
  font-weight: bold;
  color: white;
}

.matricule-cell:hover {
  background: #4a6080;
}

.moyenne-cell {
  cursor: pointer;
  background: #34495e;
  font-weight: bold;
}

.moyenne-cell:hover {
  background: #415b76;
}

tr:nth-child(even) {
  background: #252525;
}

tr:hover {
  background: #333;
}
</style>