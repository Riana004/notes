-- ============================
-- TABLE annee_etude
-- ============================
CREATE TABLE annee_etude (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- ============================
-- TABLE etudiant
-- ============================
CREATE TABLE etudiant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    etu VARCHAR(50) UNIQUE NOT NULL
);

-- ============================
-- TABLE annee_universitaire
-- ============================
CREATE TABLE annee_universitaire (
    id SERIAL PRIMARY KEY,
    annee_debut INT NOT NULL,
    annee_fin INT NOT NULL
);

-- ============================
-- TABLE session
-- ============================
CREATE TABLE session (
    id SERIAL PRIMARY KEY,
    periode_annee INT NOT NULL,
    periode_mois INT NOT NULL,
    id_annee_universitaire INT NOT NULL REFERENCES annee_universitaire(id)
);

-- ============================
-- TABLE inscription
-- ============================
CREATE TABLE inscription (
    id_etudiant INT NOT NULL REFERENCES etudiant(id),
    id_annee_etude INT NOT NULL REFERENCES annee_etude(id),
    id_annee_universitaire INT NOT NULL REFERENCES annee_universitaire(id),
    
    PRIMARY KEY (id_etudiant, id_annee_etude, id_annee_universitaire)
);

-- ============================
-- TABLE semestre
-- ============================
CREATE TABLE semestre (
    id SERIAL PRIMARY KEY,
    id_annee_etude INT NOT NULL REFERENCES annee_etude(id),
    nom VARCHAR(100) NOT NULL
);

-- ============================
-- TABLE matiere
-- ============================
CREATE TABLE matiere (
    id SERIAL PRIMARY KEY,
    id_semestre INT NOT NULL REFERENCES semestre(id),
    code VARCHAR(50) NOT NULL,
    intitule VARCHAR(150) NOT NULL,
    credit INT NOT NULL
);

-- ============================
-- TABLE note
-- ============================
CREATE TABLE note (
    id SERIAL PRIMARY KEY,
    id_matiere INT NOT NULL REFERENCES matiere(id),
    id_etudiant INT NOT NULL REFERENCES etudiant(id),
    id_session INT NOT NULL REFERENCES session(id),
    valeur NUMERIC(5,2) NOT NULL
);