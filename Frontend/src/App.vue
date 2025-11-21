<!-- App.vue -->
<script>
import SemesterList from './components/SemesterList.vue'
import StudentsList from './components/StudentsList.vue'
import ReleveNotes from './components/ReleveNotes.vue'
import EtudiantInfo from './components/EtudiantInfo.vue'
import NotesAnnee from './components/NotesAnnee.vue'
import HomePage from './components/HomePage.vue'

export default {
  name: 'App',
  components: {
    SemesterList,
    StudentsList,
    ReleveNotes,
    EtudiantInfo,
    NotesAnnee,
    HomePage
  },
  data() {
    return {
      currentView: 'HomePage',
      selectedStudent: null,
      selectedSemestre: null,
      selectedAnnee: null
    }
  },
  methods: {
    showSemesters() {
      this.currentView = 'SemesterList'
      this.clearSelectedData()
    },
    showStudents() {
      this.currentView = 'StudentsList'
    },
    showReleve(student, semestre) {
      this.selectedStudent = student
      this.selectedSemestre = semestre
      this.currentView = 'ReleveNotes'
    },
    showEtudiantInfo(student) {
      this.selectedStudent = student
      this.currentView = 'EtudiantInfo'
    },
    showNotesAnnee(student, annee) {
      this.selectedStudent = student
      this.selectedAnnee = annee
      this.currentView = 'NotesAnnee'
    },
    showHome() {
      this.currentView = 'HomePage'
      this.clearSelectedData()
    },
    clearSelectedData() {
      this.selectedStudent = null
      this.selectedSemestre = null
      this.selectedAnnee = null
    }
  }
}
</script>

<template>
  <div id="app">
    <div class="header">
      <button @click="showSemesters" class="semester-btn">Semestres</button>
    </div>
    
    <div class="main">
      <component 
        :is="currentView" 
        :selectedStudent="selectedStudent"
        :selectedSemestre="selectedSemestre"
        :selectedAnnee="selectedAnnee"
        @show-students="showStudents"
        @show-releve="showReleve"
        @show-etudiant-info="showEtudiantInfo"
        @show-notes-annee="showNotesAnnee"
        @go-back="showHome"
      />
    </div>
  </div>
</template>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html, body {
  height: 100%;
}

#app {
  min-height: 100vh;
  font-family: system-ui, -apple-system, sans-serif;
  background: #1a1a1a;
  color: white;
}

.header {
  padding: 15px 20px;
  background: #2d2d2d;
  border-bottom: 1px solid #444;
}

.semester-btn {
  padding: 10px 20px;
  background: #404040;
  border: 1px solid #555;
  color: white;
  cursor: pointer;
  border-radius: 4px;
}

.semester-btn:hover {
  background: #505050;
}

.main {
  padding: 20px;
}
</style>