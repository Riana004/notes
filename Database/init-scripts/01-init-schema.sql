-- ============================
-- TABLE AnneeEtude
-- ============================
CREATE TABLE AnneeEtude (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- ============================
-- TABLE Etudiant
-- ============================
CREATE TABLE Etudiant (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    ETU VARCHAR(50) UNIQUE NOT NULL
);

-- ============================
-- TABLE AnneeUniversitaire
-- ============================
CREATE TABLE AnneeUniversitaire (
    id SERIAL PRIMARY KEY,
    AnneeDebut INT NOT NULL,
    AnneeFin INT NOT NULL
);

-- ============================
-- TABLE Session
-- ============================
CREATE TABLE Session (
    id SERIAL PRIMARY KEY,
    periodeAnnee INT NOT NULL,
    periodeMois INT NOT NULL,
    idAnneeUniversitaire INT NOT NULL REFERENCES AnneeUniversitaire(id)
);

-- ============================
-- TABLE Inscription
-- ============================
CREATE TABLE Inscription (
    idEtudiant INT NOT NULL REFERENCES Etudiant(id),
    idAnneeEtude INT NOT NULL REFERENCES AnneeEtude(id),

    PRIMARY KEY (idEtudiant, idAnneeEtude)
);

-- ============================
-- TABLE Semestre
-- ============================
CREATE TABLE Semestre (
    id SERIAL PRIMARY KEY,
    idAnneeEtude INT NOT NULL REFERENCES AnneeEtude(id),
    nom VARCHAR(100) NOT NULL
);

-- ============================
-- TABLE Matiere
-- ============================
CREATE TABLE Matiere (
    id SERIAL PRIMARY KEY,
    idSemestre INT NOT NULL REFERENCES Semestre(id),
    code VARCHAR(50) NOT NULL,
    intitule VARCHAR(150) NOT NULL,
    credit INT NOT NULL
);

-- ============================
-- TABLE Note
-- ============================
CREATE TABLE Note (
    id SERIAL PRIMARY KEY,
    idMatiere INT NOT NULL REFERENCES Matiere(id),
    idEtudiant INT NOT NULL REFERENCES Etudiant(id),
    idSession INT NOT NULL REFERENCES Session(id),
    valeur NUMERIC(5,2) NOT NULL
);
