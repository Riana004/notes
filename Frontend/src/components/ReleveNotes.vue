<!-- components/ReleveNotes.vue -->
<script>
import { API_ENDPOINTS } from '../config'

export default {
  name: 'ReleveNotes',
  props: ['selectedStudent', 'selectedSemestre', 'selectedParcours'],
  data() {
    return {
      releve: null,
      isLoaded: false,
      isFetching: false,
      loadedKey: null,
      error: null
    }
  },
  async mounted() {
    console.log('ReleveNotes mounted for:', this.selectedSemestre, this.selectedParcours)
    if (this.selectedStudent && this.selectedSemestre) {
      await this.loadReleve()
    }
  },
  methods: {
    async loadReleve() {
      const key = `${this.selectedStudent.idEtudiant}-${this.selectedSemestre}-${this.selectedParcours || ''}`
      
      if ((this.isLoaded || this.isFetching) && this.loadedKey === key) {
        console.log('ReleveNotes already loaded for this key')
        return
      }
      
      this.isFetching = true
      this.error = null
      
      let url = API_ENDPOINTS.releveNote(this.selectedStudent.idEtudiant, this.selectedSemestre)
      if (this.selectedSemestre === 'S4' && this.selectedParcours) {
        url += `?parcours=${encodeURIComponent(this.selectedParcours)}`
      }
      
      console.log('Fetching releve from:', url)
      
      try {
        const response = await fetch(url, {
          headers: { 'Content-Type': 'application/json' }
        })
        
        if (!response.ok) throw new Error(`HTTP ${response.status}`)
        
        const result = await response.json()
        const data = result.data || result
        
        if (data) {
          this.releve = data
          this.isLoaded = true
          this.loadedKey = key
          console.log('Releve loaded')
        }
      } catch (err) {
        this.error = err.message
        console.error('Error loading releve:', err)
      } finally {
        this.isFetching = false
      }
    },
    goBack() {
      this.$emit('go-back')
    }
  }
}
</script>

<template>
  <div class="releve-notes">
    <button @click="goBack" class="back-btn">← Retour</button>
    
    <div v-if="releve" class="releve-content">
      <div class="releve-header">
        <h2>Relevé de Notes - {{ releve.semestre }}</h2>
        <div v-if="selectedParcours" class="parcours-info">
          Parcours: {{ selectedParcours }}
        </div>
      </div>

      <div class="student-info">
        <p><strong>Matricule:</strong> {{ releve.matricule }}</p>
        <p><strong>Nom:</strong> {{ releve.nom }} {{ releve.prenom }}</p>
        <p><strong>Parcours:</strong> {{ releve.parcours }}</p>
      </div>

      <div class="notes-table">
        <table>
          <thead>
            <tr>
              <th>Code UE</th>
              <th>Intitulé</th>
              <th>Crédits</th>
              <th>Note</th>
              <th>Type</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="note in releve.notes" :key="note.ue" :class="{ 'option-ue': note.isOption }">
              <td>{{ note.ue }}</td>
              <td>{{ note.intitule }}</td>
              <td>{{ note.credit }}</td>
              <td>{{ note.note }}/20</td>
              <td>
                <span v-if="note.isOption" class="option-badge">Option</span>
                <span v-else class="obligatoire-badge">Obligatoire</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="releve-summary">
        <p><strong>Moyenne générale:</strong> {{ releve.moyenne }}/20</p>
        <p><strong>Total crédits:</strong> {{ releve.totalCredits }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Styles inchangés */
.releve-notes {
  padding: 20px;
  max-width: 900px;
  margin: 0 auto;
}

.back-btn {
  background: var(--secondary-color);
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 6px;
  cursor: pointer;
  margin-bottom: 20px;
  font-weight: 500;
  transition: all 0.3s ease;
}

.back-btn:hover {
  background: var(--secondary-dark);
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(107, 114, 128, 0.3);
}

.releve-header {
  text-align: center;
  margin-bottom: 30px;
}

.releve-header h2 {
  color: var(--primary-color);
  border-bottom: 2px solid var(--primary-color);
  padding-bottom: 10px;
}

.parcours-info {
  margin-top: 10px;
  font-style: italic;
  color: var(--text-secondary);
}

.student-info {
  background: white;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
  border: 1px solid var(--border-color);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.student-info p {
  margin: 8px 0;
  font-size: 1.1em;
  color: var(--text-primary);
}

.notes-table {
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border: 1px solid var(--border-color);
}

th, td {
  border: 1px solid var(--border-color);
  padding: 12px;
  text-align: left;
}

th {
  background: var(--primary-color);
  color: white;
  text-align: center;
  font-weight: 600;
}

td {
  text-align: left;
  color: var(--text-primary);
}

td:nth-child(3),
td:nth-child(4) {
  text-align: center;
}

.option-ue {
  background-color: #fef3c7;
}

.option-badge {
  background: #ffa000;
  color: white;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8em;
  font-weight: bold;
}

.obligatoire-badge {
  background: #4caf50;
  color: white;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8em;
  font-weight: bold;
}

tr:nth-child(even):not(.option-ue) {
  background: var(--light-bg);
}

tr:hover {
  background: #f3f4f6;
}

.releve-summary {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  border: 1px solid var(--border-color);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.releve-summary p {
  margin: 10px 0;
  font-size: 1.2em;
  font-weight: bold;
  color: var(--text-primary);
}
</style>