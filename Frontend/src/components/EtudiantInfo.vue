<!-- components/EtudiantInfo.vue -->
<script>
export default {
  name: 'EtudiantInfo',
  data() {
    return {
      etudiant: {
        idEtudiant: 1,
        matricule: "ET001",
        nom: "Dupont",
        prenom: "Jean",
        moyenneS1: 14.5,
        moyenneS2: 15.2,
        moyenneS3: 13.8,
        moyenneS4Dev: 16.0,
        moyenneS4Web: 14.5,
        moyenneS4BDD: 15.8
      }
    }
  },
  methods: {
    goBack() {
      this.$emit('go-back')
    },
    showReleveNotes(semestre) {
      this.$emit('show-releve', this.etudiant, semestre)
    },
    showNotesAnnee(annee) {
      this.$emit('show-notes-annee', this.etudiant, annee)
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

    <div class="student-card">
      <div class="student-identity">
        <h3>{{ etudiant.nom }} {{ etudiant.prenom }}</h3>
        <p><strong>Matricule:</strong> {{ etudiant.matricule }}</p>
        <p><strong>ID:</strong> {{ etudiant.idEtudiant }}</p>
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
            <div class="moyenne-value">{{ etudiant.moyenneS1 }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S2')">
            <h4>Semestre 2</h4>
            <div class="moyenne-value">{{ etudiant.moyenneS2 }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S3')">
            <h4>Semestre 3</h4>
            <div class="moyenne-value">{{ etudiant.moyenneS3 }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S4Dev')">
            <h4>S4 Développement</h4>
            <div class="moyenne-value">{{ etudiant.moyenneS4Dev }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S4Web')">
            <h4>S4 Web</h4>
            <div class="moyenne-value">{{ etudiant.moyenneS4Web }}/20</div>
          </div>
          <div class="moyenne-card" @click="showReleveNotes('S4BDD')">
            <h4>S4 Base de Données</h4>
            <div class="moyenne-value">{{ etudiant.moyenneS4BDD }}/20</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
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
  color: #2c3e50;
  border-bottom: 2px solid #3498db;
  padding-bottom: 10px;
}

.student-card {
  background: white;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0,0,0,0.1);
  overflow: hidden;
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
  background: #f8f9fa;
  border-bottom: 1px solid #e9ecef;
}

.annees-section h3 {
  color: #2c3e50;
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
  color: #2c3e50;
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
  background: #f8f9fa;
  border: 2px solid #e9ecef;
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
  color: #2c3e50;
  font-size: 1.1em;
}

.moyenne-value {
  font-size: 1.5em;
  font-weight: bold;
  color: #27ae60;
  background: white;
  padding: 10px;
  border-radius: 8px;
  border: 2px solid #27ae60;
}
</style>