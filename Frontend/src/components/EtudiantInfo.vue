<!-- components/EtudiantInfo.vue -->
<script>
export default {
  name: 'EtudiantInfo',
  props: ['selectedStudent', 'fetchData'],
  data() {
    return {
      etudiantInfo: null
    }
  },
  async mounted() {
    if (this.selectedStudent) {
      await this.loadEtudiantInfo()
    }
  },
  methods: {
    async loadEtudiantInfo() {
      const data = await this.fetchData(
        `http://localhost:8080/api/notes/etudiants/${this.selectedStudent.idEtudiant}/info`
      )
      if (data) {
        this.etudiantInfo = data
      }
    },
    goBack() {
      this.$emit('go-back')
    },
    showReleveNotes(semestre, parcours = null) {
      this.$emit('show-releve', this.etudiantInfo, semestre, parcours)
    },
    showNotesAnnee(annee) {
      this.$emit('show-notes-annee', this.etudiantInfo, annee)
    }
  }
}
</script>

<template>
  <div class="etudiant-info">
    <button @click="goBack" class="back-btn">← Retour aux étudiants</button>
    
    <div class="header">
      <h2>Informations de l'Étudiant</h2>
    </div>

    <div v-if="etudiantInfo" class="student-card">
      <div class="student-identity">
        <h3>{{ etudiantInfo.nom }} {{ etudiantInfo.prenom }}</h3>
        <p><strong>Matricule:</strong> {{ etudiantInfo.matricule }}</p>
        <p><strong>ID:</strong> {{ etudiantInfo.idEtudiant }}</p>
      </div>

      <div class="annees-section">
        <h3>Notes par Année Universitaire</h3>
        <div class="annees-links">
          <button @click="showNotesAnnee('L1')" class="annee-btn">L1</button>
          <button @click="showNotesAnnee('L2')" class="annee-btn">L2</button>
        </div>
      </div>

      <div class="moyennes-section">
        <h3>Moyennes par Semestre</h3>
        <div class="moyennes-grid">
          <div class="moyenne-card" @click="showReleveNotes('S1')">
            <h4>Semestre 1</h4>
            <div class="moyenne-value">{{ etudiantInfo.moyenneS1 }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S2')">
            <h4>Semestre 2</h4>
            <div class="moyenne-value">{{ etudiantInfo.moyenneS2 }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S3')">
            <h4>Semestre 3</h4>
            <div class="moyenne-value">{{ etudiantInfo.moyenneS3 }}/20</div>
          </div>
          <div 
            v-for="(moyenne, parcours) in etudiantInfo.moyennesS4" 
            :key="parcours"
            class="moyenne-card" 
            @click="showReleveNotes('S4', parcours)"
          >
            <h4>S4 {{ parcours }}</h4>
            <div class="moyenne-value">{{ moyenne }}/20</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Styles inchangés */
.etudiant-info {
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
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

.student-card {
  background: #2d2d2d;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.3);
  overflow: hidden;
  border: 1px solid #444;
}

.student-identity {
  background: #3498db;
  color: white;
  padding: 30px;
  text-align: center;
}

.student-identity h3 {
  margin: 0 0 15px 0;
  font-size: 1.8em;
}

.student-identity p {
  margin: 8px 0;
  font-size: 1.1em;
}

.annees-section {
  padding: 20px;
  background: #252525;
  border-bottom: 1px solid #444;
}

.annees-section h3 {
  color: white;
  margin-bottom: 15px;
  text-align: center;
}

.annees-links {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.annee-btn {
  background: #9b59b6;
  color: white;
  border: none;
  padding: 12px 30px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 1.1em;
  font-weight: bold;
  transition: all 0.3s ease;
}

.annee-btn:hover {
  background: #8e44ad;
  transform: translateY(-2px);
}

.moyennes-section {
  padding: 30px;
}

.moyennes-section h3 {
  color: white;
  margin-bottom: 20px;
  text-align: center;
  font-size: 1.5em;
}

.moyennes-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
}

.moyenne-card {
  background: #252525;
  border: 2px solid #444;
  border-radius: 10px;
  padding: 20px;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s ease;
}

.moyenne-card:hover {
  transform: translateY(-5px);
  border-color: #3498db;
  box-shadow: 0 5px 15px rgba(52, 152, 219, 0.3);
}

.moyenne-card h4 {
  margin: 0 0 15px 0;
  color: white;
  font-size: 1.1em;
}

.moyenne-value {
  font-size: 1.5em;
  font-weight: bold;
  color: #27ae60;
  background: #1a1a1a;
  padding: 10px;
  border-radius: 8px;
  border: 2px solid #27ae60;
}
</style>