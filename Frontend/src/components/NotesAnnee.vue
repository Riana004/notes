<!-- components/NotesAnnee.vue -->
<script>
export default {
  name: 'NotesAnnee',
  data() {
    return {
      notesAnnee: [
        {
          idEtudiant: 1,
          matricule: "ET001",
          nom: "Dupont",
          prenom: "Jean",
          anneeUniversitaire: "2023-2024",
          semestre: "S1",
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
              ue: "MAT101",
              intitule: "Mathématiques pour l'Informatique",
              credit: 6,
              note: 16.0,
              isOption: false
            }
          ]
        },
        {
          idEtudiant: 1,
          matricule: "ET001",
          nom: "Dupont",
          prenom: "Jean",
          anneeUniversitaire: "2023-2024",
          semestre: "S2",
          notes: [
            {
              ue: "INF201",
              intitule: "Programmation Avancée",
              credit: 6,
              note: 15.0,
              isOption: false
            },
            {
              ue: "INF202", 
              intitule: "Réseaux",
              credit: 6,
              note: 13.5,
              isOption: false
            },
            {
              ue: "MAT201",
              intitule: "Statistiques",
              credit: 6,
              note: 14.5,
              isOption: false
            }
          ]
        }
      ]
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
  <div class="notes-annee">
    <button @click="goBack" class="back-btn">← Retour aux informations</button>
    
    <div class="header">
      <h2>Notes - {{ notesAnnee[0].anneeUniversitaire }}</h2>
    </div>

    <div class="student-info">
      <p><strong>Matricule:</strong> {{ notesAnnee[0].matricule }}</p>
      <p><strong>Nom:</strong> {{ notesAnnee[0].nom }} {{ notesAnnee[0].prenom }}</p>
      <p><strong>Année Universitaire:</strong> {{ notesAnnee[0].anneeUniversitaire }}</p>
    </div>

    <div class="semestres-container">
      <div v-for="semestre in notesAnnee" :key="semestre.semestre" class="semestre-section">
        <h3 class="semestre-title">Semestre {{ semestre.semestre }}</h3>
        
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
              <tr v-for="note in semestre.notes" :key="note.ue" :class="{ 'option-ue': note.isOption }">
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
  color: #2c3e50;
  border-bottom: 2px solid #9b59b6;
  padding-bottom: 10px;
}

.student-info {
  background: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 30px;
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
  background: white;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  overflow: hidden;
}

.semestre-title {
  background: #9b59b6;
  color: white;
  margin: 0;
  padding: 15px 20px;
  font-size: 1.3em;
}

.notes-table {
  padding: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: white;
}

th, td {
  border: 1px solid #ddd;
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
</style>