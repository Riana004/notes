<!-- components/SemesterList.vue -->
<script>
export default {
  name: 'SemesterList',
  data() {
    return {
      semesters: [
        { idSemestre: 1, libellle: "Semestre 1" },
        { idSemestre: 2, libellle: "Semestre 2" },
        { idSemestre: 3, libellle: "Semestre 3" },
        { idSemestre: 4, libellle: "Semestre 4" }
      ]
    }
  },
  methods: {
    showStudents() {
      this.$emit('show-students')
    }
  }
}
</script>

<template>
  <div class="semester-list">
    <h2>Liste des Semestres</h2>
    
    <div class="semesters-grid">
      <div 
        v-for="semester in semesters" 
        :key="semester.idSemestre"
        class="semester-card"
      >
        <h3>{{ semester.libellle }}</h3>
        <p>ID: {{ semester.idSemestre }}</p>
      </div>
    </div>

    <button @click="showStudents" class="students-link">
      Voir les étudiants avec leurs moyennes
    </button>
  </div>
</template>

<style scoped>
.semester-list {
  padding: 20px;
}

.semesters-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
  margin-bottom: 30px;
}

.semester-card {
  background: none;
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 20px;
  text-align: center;
}

.students-link {
  background: #3498db;
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
}

.students-link:hover {
  background: #2980b9;
}
</style>