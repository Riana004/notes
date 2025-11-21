<!-- components/ReleveNotes.vue -->
<script>
export default {
  name: 'ReleveNotes',
  props: ['selectedStudent', 'selectedSemestre', 'selectedParcours', 'fetchData'],
  data() {
    return {
      releve: null
    }
  },
  async mounted() {
    if (this.selectedStudent && this.selectedSemestre) {
      await this.loadReleve()
    }
  },
  methods: {
    async loadReleve() {
      let url = `http://localhost:8080/api/notes/etudiants/${this.selectedStudent.idEtudiant}/releve/${this.selectedSemestre}`
      
      // Ajouter le paramètre parcours pour S4
      if (this.selectedSemestre === 'S4' && this.selectedParcours) {
        url += `?parcours=${encodeURIComponent(this.selectedParcours)}`
      }
      
      const data = await this.fetchData(url)
      if (data) {
        this.releve = data
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

.releve-header {
  text-align: center;
  margin-bottom: 30px;
}

.releve-header h2 {
  color: white;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

.parcours-info {
  margin-top: 10px;
  font-style: italic;
  color: #bdc3c7;
}

.student-info {
  background: #252525;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
  border: 1px solid #444;
}

.student-info p {
  margin: 8px 0;
  font-size: 1.1em;
}

.notes-table {
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: #2d2d2d;
  box-shadow: 0 2px 5px rgba(0,0,0,0.3);
  border: 1px solid #444;
}

th, td {
  border: 1px solid #444;
  padding: 12px;
  text-align: left;
}

th {
  background: #404040;
  color: white;
  text-align: center;
}

td {
  text-align: left;
}

td:nth-child(3),
td:nth-child(4) {
  text-align: center;
}

.option-ue {
  background-color: #3a2d39;
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
  background: #252525;
}

tr:hover {
  background: #333;
}

.releve-summary {
  background: #252525;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  border: 1px solid #444;
}

.releve-summary p {
  margin: 10px 0;
  font-size: 1.2em;
  font-weight: bold;
}
</style>