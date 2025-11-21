<!-- components/ReleveNotes.vue -->
<script>
export default {
  name: 'ReleveNotes',
  data() {
    return {
      releve: {
        idEtudiant: 1,
        matricule: "ET001",
        nom: "Dupont",
        prenom: "Jean",
        semestre: "S1",
        parcours: "Informatique",
        moyenne: 14.5,
        totalCredits: 30,
        notes: [
          {
            ue: "INF101",
            intitule: "Algorithmique et Programmation",
            credit: 6,
            note: 15.5,
            isOption: false
          },
          {
            ue: "INF102", 
            intitule: "Base de Données",
            credit: 6,
            note: 14.0,
            isOption: false
          },
          {
            ue: "INF103",
            intitule: "Réseaux et Systèmes",
            credit: 6,
            note: 13.0,
            isOption: false
          },
          {
            ue: "MAT101",
            intitule: "Mathématiques pour l'Informatique",
            credit: 6,
            note: 16.0,
            isOption: false
          },
          {
            ue: "ANG101",
            intitule: "Anglais Technique",
            credit: 3,
            note: 12.5,
            isOption: true
          },
          {
            ue: "COM101",
            intitule: "Communication",
            credit: 3,
            note: 15.0,
            isOption: true
          }
        ]
      }
    }
  },
  methods: {
    goBack() {
      this.$emit('go-back')
    }
  }
}
</script>

<template>
  <div class="releve-notes">
    <button @click="goBack" class="back-btn">← Retour aux étudiants</button>
    
    <div class="releve-header">
      <h2>Relevé de Notes - {{ releve.semestre }}</h2>
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
</template>

<style scoped>
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
  color: #2c3e50;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

.student-info {
  background: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
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
  background: white;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

th, td {
  border: 1px solid #ddd;
  padding: 12px;
  text-align: left;
}

th {
  background: #3498db;
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
  background-color: #fff8e1;
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
  background: #f9f9f9;
}

tr:hover {
  background: #f5f5f5;
}

.releve-summary {
  background: #ecf0f1;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
}

.releve-summary p {
  margin: 10px 0;
  font-size: 1.2em;
  font-weight: bold;
}
</style>