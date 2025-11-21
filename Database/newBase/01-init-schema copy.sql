CREATE TABLE Etudiant(
   id_etudiant SERIAL,
   matricule VARCHAR(50)  NOT NULL,
   nom VARCHAR(50)  NOT NULL,
   prenom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_etudiant),
   UNIQUE(matricule)
);

CREATE TABLE Semestre(
   id_semestre SERIAL,
   libellle VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_semestre)
);

CREATE TABLE Matiere(
   id_matiere SERIAL,
   ue VARCHAR(50)  NOT NULL,
   intitule VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_matiere),
   UNIQUE(ue)
);

CREATE TABLE InscriptionSemestre(
   id_inscriptionsemestre SERIAL,
   date_inscription DATE NOT NULL,
   date_fin DATE NOT NULL,
   id_etudiant INTEGER,
   id_semestre INTEGER,
   PRIMARY KEY(id_inscriptionsemestre),
   FOREIGN KEY(id_etudiant) REFERENCES Etudiant(id_etudiant),
   FOREIGN KEY(id_semestre) REFERENCES Semestre(id_semestre)
);

CREATE TABLE Session(
   id_session SERIAL,
   intitule VARCHAR(50)  NOT NULL,
   date_ DATE NOT NULL,
   PRIMARY KEY(id_session)
);

CREATE TABLE Parcours(
   id_parcours SERIAL,
   intitule VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_parcours)
);

CREATE TABLE AnneeUniversitaire(
   id_annee_universitaire SERIAL,
   intitule VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_annee_universitaire)
);

CREATE TABLE AnneeSemestre(
   id_annee_semestre SERIAL,
   id_semestre INTEGER,
   id_annee_universitaire INTEGER,
   PRIMARY KEY(id_annee_semestre),
   FOREIGN KEY(id_semestre) REFERENCES Semestre(id_semestre),
   FOREIGN KEY(id_annee_universitaire) REFERENCES AnneeUniversitaire(id_annee_universitaire)
);

CREATE TABLE SemestreMatiereParcours(
   id_semestre_matiere_parcours SERIAL,
   credit INTEGER NOT NULL,
   option INTEGER NOT NULL,
   id_semestre INTEGER,
   id_matiere INTEGER,
   id_parcours INTEGER,
   PRIMARY KEY(id_semestre_matiere_parcours),
   FOREIGN KEY(id_semestre) REFERENCES Semestre(id_semestre),
   FOREIGN KEY(id_matiere) REFERENCES Matiere(id_matiere),
   FOREIGN KEY(id_parcours) REFERENCES Parcours(id_parcours)
);

CREATE TABLE Examen(
   id_examen SERIAL,
   note NUMERIC(15,2)   NOT NULL,
   id_etudiant INTEGER,
   id_matiere INTEGER,
   id_session INTEGER,
   PRIMARY KEY(id_examen),
   FOREIGN KEY(id_etudiant) REFERENCES Etudiant(id_etudiant),
   FOREIGN KEY(id_matiere) REFERENCES Matiere(id_matiere),
   FOREIGN KEY(id_session) REFERENCES Session(id_session)
);
