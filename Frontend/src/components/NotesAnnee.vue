<!-- components/NotesAnnee.vue -->
<script>
export default {
  name: 'NotesAnnee',
  props: ['selectedStudent', 'selectedAnnee', 'fetchData'],
  data() {
    return {
      notesAnnee: []
    }
  },
  async mounted() {
    if (this.selectedStudent && this.selectedAnnee) {
      await this.loadNotesAnnee()
    }
  },
  methods: {
    async loadNotesAnnee() {
      const data = await this.fetchData(
        `http://localhost:8080/api/notes/etudiants/${this.selectedStudent.idEtudiant}/annee/${this.selectedAnnee}`
      )
      if (data) {
        this.notesAnnee = data
      }
    },
    goBack() {
      this.$emit('go-back')
    }
  }
}
</script>

<template>
  <div class="notes-annee">
    <button @click="goBack" class="back-btn">← Retour aux informations</button>
    
    <div class="header">
      <h2>Notes - {{ selectedAnnee }}</h2>
    </div>

    <div v-if="notesAnnee.length > 0" class="student-info">
      <p><strong>Matricule:</strong> {{ notesAnnee[0].matricule }}</p>
      <p><strong>Nom:</strong> {{ notesAnnee[0].nom }} {{ notesAnnee[0].prenom }}</p>
      <p><strong>Année Universitaire:</strong> {{ notesAnnee[0].anneeUniversitaire }}</p>
    </div>

    <div class="semestres-container">
      <div v-for="semestre in notesAnnee" :key="semestre.semestre" class="semestre-section">
        <h3 class="semestre-title">Semestre {{ semestre.semestre }}</h3>
        
        <div v-for="(notes, parcours) in semestre.notesParParcours" :key="parcours" class="parcours-section">
          <h4 v-if="Object.keys(semestre.notesParParcours).length > 1" class="parcours-title">
            Parcours: {{ parcours }}
          </h4>
          
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
                <tr v-for="note in notes" :key="note.ue" :class="{ 'option-ue': note.isOption }">
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
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.notes-annee {
  padding: 20px;
  max-width: 1000px;
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

.header {
  text-align: center;
  margin-bottom: 30px;
}

.header h2 {
  color: white;
  border-bottom: 2px solid #9b59b6;
  padding-bottom: 10px;
}

.student-info {
  background: #252525;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 30px;
  border: 1px solid #444;
}

.student-info p {
  margin: 8px 0;
  font-size: 1.1em;
}

.semestres-container {
  display: flex;
  flex-direction: column;
  gap: 40px;
}

.semestre-section {
  background: #2d2d2d;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.3);
  overflow: hidden;
  border: 1px solid #444;
}

.semestre-title {
  background: #9b59b6;
  color: white;
  margin: 0;
  padding: 15px 20px;
  font-size: 1.3em;
}

.parcours-section {
  padding: 20px;
}

.parcours-title {
  color: #bdc3c7;
  margin-bottom: 15px;
  font-size: 1.1em;
  border-bottom: 1px solid #444;
  padding-bottom: 10px;
}

.notes-table {
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: #252525;
}

th, td {
  border: 1px solid #444;
  padding: 12px;
  text-align: left;
}

th {
  background: #8e44ad;
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
  background: #2d2d2d;
}

tr:hover {
  background: #333;
}
</style>