<!-- components/SemesterList.vue -->
<script>
import { API_ENDPOINTS } from '../config'

export default {
  name: 'SemesterList',
  data() {
    return {
      semesters: [],
      isLoaded: false,
      isFetching: false,
      error: null
    }
  },
  async mounted() {
    console.log('SemesterList mounted - should only appear once')
    await this.loadSemesters()
  },
  methods: {
    async loadSemesters() {
      console.log('loadSemesters called, isLoaded:', this.isLoaded, 'isFetching:', this.isFetching)
      
      // Double protection contre les appels multiples
      if (this.isLoaded || this.isFetching) {
        console.log('Already loaded or fetching, returning')
        return
      }
      
      this.isFetching = true
      this.error = null
      console.log('Fetching semesters from:', API_ENDPOINTS.semestres)
      
      try {
        const response = await fetch(API_ENDPOINTS.semestres, {
          headers: {
            'Content-Type': 'application/json'
          }
        })
        
        if (!response.ok) {
          throw new Error(`HTTP ${response.status}`)
        }
        
        const result = await response.json()
        const data = result.data || result
        
        if (data) {
          this.semesters = data
          this.isLoaded = true
          console.log('Semesters loaded successfully:', data)
        }
      } catch (err) {
        this.error = err.message
        console.error('Error loading semesters:', err)
      } finally {
        this.isFetching = false
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
  background: white;
  border: 1px solid var(--border-color);
  border-radius: 8px;
  padding: 20px;
  text-align: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.semester-card:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
  transform: translateY(-2px);
}

.students-link {
  background: var(--primary-color);
  color: white;
  border: none;
  padding: 12px 24px;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
  font-weight: 500;
  transition: all 0.3s ease;
}

.students-link:hover {
  background: var(--primary-dark);
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(79, 70, 229, 0.3);
}
</style>