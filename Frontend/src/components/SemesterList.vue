<!-- components/SemesterList.vue -->
<script>
export default {
  name: 'SemesterList',
  props: ['fetchData'],
  data() {
    return {
      semesters: []
    }
  },
  async mounted() {
    await this.loadSemesters()
  },
  methods: {
    async loadSemesters() {
      const data = await this.fetchData('http://localhost:8080/api/notes/semestres')
      if (data) {
        this.semesters = data
      }
    },
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
  text-align: center;
}

.semesters-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 15px;
  margin-bottom: 30px;
}

.semester-card {
  background: #2d2d2d;
  border: 1px solid #444;
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