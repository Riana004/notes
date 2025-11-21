<!-- components/StudentsList.vue -->
<script>
export default {
  name: 'StudentsList',
  props: ['selectedStudent', 'selectedSemestre'],
  data() {
    return {
      students: [
        {
          idEtudiant: 1,
          matricule: "ET001",
          nom: "Dupont",
          prenom: "Jean",
          moyenneS1: 14.5,
          moyenneS2: 15.2,
          moyenneS3: 13.8,
          moyenneS4Dev: 16.0,
          moyenneS4Web: 14.5,
          moyenneS4Bdd: 15.8
        },
        {
          idEtudiant: 2,
          matricule: "ET002", 
          nom: "Martin",
          prenom: "Marie",
          moyenneS1: 12.8,
          moyenneS2: 13.5,
          moyenneS3: 14.2,
          moyenneS4Dev: 13.0,
          moyenneS4Web: 15.5,
          moyenneS4Bdd: 12.8
        }
      ]
    }
  },
  methods: {
    goBack() {
      this.$emit('go-back')
    },
    showReleveNotes(student, semestre) {
      this.$emit('show-releve', student, semestre)
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
            <th>Moyenne S4 Dev</th>
            <th>Moyenne S4 Web</th>
            <th>Moyenne S4 BDD</th>
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
            <td class="moyenne-cell" @click="showReleveNotes(student, 'S4Dev')">
              {{ student.moyenneS4Dev }}
            </td>
            <td class="moyenne-cell" @click="showReleveNotes(student, 'S4Web')">
              {{ student.moyenneS4Web }}
            </td>
            <td class="moyenne-cell" @click="showReleveNotes(student, 'S4Bdd')">
              {{ student.moyenneS4Bdd }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
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