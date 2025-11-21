// Configuration de l'API
export const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080'

export const API_ENDPOINTS = {
  semestres: `${API_BASE_URL}/api/notes/semestres`,
  etudiantsMoyennes: `${API_BASE_URL}/api/notes/etudiants/moyennes`,
  etudiantInfo: (id) => `${API_BASE_URL}/api/notes/etudiants/${id}/info`,
  releveNote: (id, semestre) => `${API_BASE_URL}/api/notes/etudiants/${id}/releve/${semestre}`,
  notesAnnee: (id, annee) => `${API_BASE_URL}/api/notes/etudiants/${id}/annee/${annee}`
}
