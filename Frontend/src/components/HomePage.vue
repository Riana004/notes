<!-- components/HomePage.vue -->
<template>
  <div class="home-page fade-in">
    <div class="hero-section">
      <div class="hero-content">
        <h1 class="hero-title">🎓 Système de Gestion des Notes</h1>
        <p class="hero-subtitle">Plateforme de consultation des résultats académiques</p>
        
        <div class="features-grid grid grid-3 mt-4">
          <div class="feature-card card">
            <div class="feature-icon">📚</div>
            <h3>Semestres</h3>
            <p>Consultez les semestres S1 à S4</p>
            <button @click="$emit('show-students')" class="btn-primary mt-2">Voir les semestres</button>
          </div>
          
          <div class="feature-card card">
            <div class="feature-icon">👥</div>
            <h3>Étudiants</h3>
            <p>Liste complète des étudiants et leurs moyennes</p>
            <button @click="$emit('show-students')" class="btn-secondary mt-2">Voir les étudiants</button>
          </div>
          
          <div class="feature-card card">
            <div class="feature-icon">📊</div>
            <h3>Statistiques</h3>
            <p>Relevés de notes et moyennes détaillées</p>
            <button @click="$emit('show-students')" class="btn-outline mt-2">Explorer</button>
          </div>
        </div>

        <div class="card mt-4">
          <h3 class="mb-2">ℹ️ Informations</h3>
          <ul class="info-list">
            <li>✅ Consultation des notes par semestre</li>
            <li>✅ Moyennes S1, S2, S3 et S4 (tous parcours)</li>
            <li>✅ Gestion des options et matières obligatoires</li>
            <li>✅ Relevés de notes détaillés</li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomePage'
}
</script>

<style scoped>
.home-page {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
}

.hero-section {
  text-align: center;
}

.hero-title {
  font-size: 2.5rem;
  font-weight: 800;
  background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
  margin-bottom: 1rem;
}

.hero-subtitle {
  font-size: 1.25rem;
  color: var(--text-secondary);
  margin-bottom: 3rem;
}

.features-grid {
  margin-top: 2rem;
}

.feature-card {
  text-align: center;
  padding: 2rem;
  transition: transform 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-8px);
}

.feature-icon {
  font-size: 3rem;
  margin-bottom: 1rem;
}

.feature-card h3 {
  font-size: 1.5rem;
  font-weight: 700;
  color: var(--text-primary);
  margin-bottom: 0.5rem;
}

.feature-card p {
  color: var(--text-secondary);
  margin-bottom: 1rem;
}

.info-list {
  list-style: none;
  padding: 0;
}

.info-list li {
  padding: 0.5rem 0;
  color: var(--text-secondary);
  text-align: left;
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 1.75rem;
  }
  
  .hero-subtitle {
    font-size: 1rem;
  }
}
</style>
