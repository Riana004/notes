-- Parcours  
INSERT INTO Parcours (intitule) VALUES  
  ('Connaissances Scientifiques et Techniques de Base'),  
  ('Développement'),  
  ('Bases de Données et Réseaux'),  
  ('Web et Design');

-- Semestres (S1 à S4)  
INSERT INTO Semestre (libellle) VALUES  
  ('S1'), ('S2'), ('S3'), ('S4');

-- Années universitaires (ex : « 2025-2026 »)  
-- Tu peux adapter selon tes années : ici un exemple  
INSERT INTO AnneeUniversitaire (intitule) VALUES  
  ('L1'),  
  ('L2');

-- Lier Semestres et Années  
-- Exemple : S1, S2, S3, S4 dans l’année 2025-2026  
INSERT INTO AnneeSemestre (id_semestre, id_annee_universitaire) VALUES  
  ((SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_annee_universitaire FROM AnneeUniversitaire WHERE intitule = '2025-2026')),  
  ((SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_annee_universitaire FROM AnneeUniversitaire WHERE intitule = '2025-2026')),  
  ((SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_annee_universitaire FROM AnneeUniversitaire WHERE intitule = '2025-2026')),  
  ((SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_annee_universitaire FROM AnneeUniversitaire WHERE intitule = '2025-2026'));

-- Matières (UE) pour S1 à S4  
INSERT INTO Matiere (ue, intitule) VALUES  
  -- S1  
  ('INF101', 'Programmation procédurale'),  
  ('INF104', 'HTML et Introduction au Web'),  
  ('INF107', 'Informatique de Base'),  
  ('MTH101', 'Arithmétique et nombres'),  
  ('MTH102', 'Analyse mathématique'),  
  ('ORG101', 'Techniques de communication'),  

  -- S2  
  ('INF102', 'Bases de données relationnelles'),  
  ('INF103', 'Bases de l’administration système'),  
  ('INF105', 'Maintenance matériel et logiciel'),  
  ('INF106', 'Compléments de programmation'),  
  ('MTH103', 'Calcul Vectoriel et Matriciel'),  
  ('MTH105', 'Probabilité et Statistique'),  

  -- S3  
  ('INF201', 'Programmation orientée objet'),  
  ('INF202', 'Bases de données objets'),  
  ('INF203', 'Programmation système'),  
  ('INF208', 'Réseaux informatiques'),  
  ('MTH201', 'Méthodes numériques'),  
  ('ORG201', 'Bases de gestion'),  

  -- S4 (Développement)  
  ('INF204', 'Système d’information géographique'),  
  ('INF205', 'Système d’information'),  
  ('INF206', 'Interface Homme/Machine'),  
  ('INF207', 'Éléments d’algorithmique'),  
  ('INF210', 'Mini-projet de développement'),  
  ('MTH204', 'Géométrie'),  
  ('MTH205', 'Équations différentielles'),  
  ('MTH206', 'Optimisation'),  
  ('MTH203', 'MAO'),  

  -- S4 (Bases de Données & Réseaux)  
  ('INF211', 'Mini-projet Bases de données / Réseaux'),  
  ('MTH202', 'Analyse des données'),  

  -- S4 (Web & Design)  
  ('INF209', 'Web dynamique'),  
  ('INF212', 'Mini-projet Web & Design');

-- SemestreMatiereParcours pour S1 à S4

-- Parcours “Connaissances Scientifiques et Techniques de Base”  
-- S1
INSERT INTO SemestreMatiereParcours (credit, option, id_semestre, id_matiere, id_parcours) VALUES  
  (7, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF101'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (5, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF104'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF107'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH101'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH102'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1'), (SELECT id_matiere FROM Matiere WHERE ue = 'ORG101'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base'));

-- S2, même parcours  
INSERT INTO SemestreMatiereParcours (credit, option, id_semestre, id_matiere, id_parcours) VALUES  
  (5, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF102'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (5, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF103'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF105'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF106'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH103'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH105'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base'));

-- S3, même parcours  
INSERT INTO SemestreMatiereParcours (credit, option, id_semestre, id_matiere, id_parcours) VALUES  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF201'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF202'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF203'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF208'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH201'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3'), (SELECT id_matiere FROM Matiere WHERE ue = 'ORG201'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Connaissances Scientifiques et Techniques de Base'));

-- Parcours “Développement”, Semestre 4  
INSERT INTO SemestreMatiereParcours (credit, option, id_semestre, id_matiere, id_parcours) VALUES  
  -- 1 UE parmi INF204 / INF205 / INF206  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF204'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF205'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF206'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  -- INF207 obligatoire  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF207'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  -- mini-projet développement  
  (10, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF210'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  -- 1 UE parmi MTH204 / MTH205 / MTH206  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH204'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH205'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH206'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement')),  
  -- MTH203 obligatoire  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH203'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Développement'));

-- Parcours “Bases de Données et Réseaux”, Semestre 4  
INSERT INTO SemestreMatiereParcours (credit, option, id_semestre, id_matiere, id_parcours) VALUES  
  (6, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF205'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF204'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF206'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF207'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (10, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF211'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH202'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH205'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH206'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH203'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Bases de Données et Réseaux'));

-- Parcours “Web et Design”, Semestre 4  
INSERT INTO SemestreMatiereParcours (credit, option, id_semestre, id_matiere, id_parcours) VALUES  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF204'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF205'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF206'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (6, 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF209'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (10, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'INF212'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH202'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH204'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (4, 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH206'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design')),  
  (4, 0, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'), (SELECT id_matiere FROM Matiere WHERE ue = 'MTH203'), (SELECT id_parcours FROM Parcours WHERE intitule = 'Web et Design'));

-- ---------- SESSIONS ----------
INSERT INTO Session (intitule, date_) VALUES
  ('Normale L1', '2024-01-10'),
  ('Rattrapage L1', '2024-02-10'),
  ('Normale L2', '2025-01-10'),
  ('Rattrapage L2', '2025-02-10');

-- ---------- ETUDIANTS ----------
INSERT INTO Etudiant (matricule, nom, prenom) VALUES
  ('ETU0001', 'Rasolo', 'Mickael'),
  ('ETU0002', 'Randria', 'Fara'),
  ('ETU0003', 'Rakoto', 'Hery'),
  ('ETU0004', 'Rabe', 'Liva'),
  ('ETU0005', 'Rasolonjatovo', 'Nirina');

-- ---------- INSCRIPTIONS (S1 -> S4 pour test) ----------
-- On inscrit chaque étudiant aux semestres S1 -> S4 (dates exemples)
INSERT INTO InscriptionSemestre (date_inscription, date_fin, id_etudiant, id_semestre) VALUES
  ('2023-09-01','2024-01-31', 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1')),
  ('2023-09-01','2024-01-31', 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1')),
  ('2023-09-01','2024-01-31', 3, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1')),
  ('2023-09-01','2024-01-31', 4, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1')),
  ('2023-09-01','2024-01-31', 5, (SELECT id_semestre FROM Semestre WHERE libellle = 'S1')),

  ('2024-02-01','2024-06-30', 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2')),
  ('2024-02-01','2024-06-30', 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2')),
  ('2024-02-01','2024-06-30', 3, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2')),
  ('2024-02-01','2024-06-30', 4, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2')),
  ('2024-02-01','2024-06-30', 5, (SELECT id_semestre FROM Semestre WHERE libellle = 'S2')),

  ('2024-09-01','2025-01-31', 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3')),
  ('2024-09-01','2025-01-31', 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3')),
  ('2024-09-01','2025-01-31', 3, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3')),
  ('2024-09-01','2025-01-31', 4, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3')),
  ('2024-09-01','2025-01-31', 5, (SELECT id_semestre FROM Semestre WHERE libellle = 'S3')),

  ('2025-02-01','2025-06-30', 1, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4')),
  ('2025-02-01','2025-06-30', 2, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4')),
  ('2025-02-01','2025-06-30', 3, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4')),
  ('2025-02-01','2025-06-30', 4, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4')),
  ('2025-02-01','2025-06-30', 5, (SELECT id_semestre FROM Semestre WHERE libellle = 'S4'));

-- ---------- EXAMENS (notes) ----------
-- NOTES S1 (chaque étudiant a notes pour les UE S1)
-- Session Normale L1
-- Etudiant 1
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (12.5, 1, (SELECT id_matiere FROM Matiere WHERE ue='INF101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0, 1, (SELECT id_matiere FROM Matiere WHERE ue='INF104'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.0, 1, (SELECT id_matiere FROM Matiere WHERE ue='INF107'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0, 1, (SELECT id_matiere FROM Matiere WHERE ue='MTH101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (11.0, 1, (SELECT id_matiere FROM Matiere WHERE ue='MTH102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (15.0, 1, (SELECT id_matiere FROM Matiere WHERE ue='ORG101'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- Etudiant 2
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (9.0, 2, (SELECT id_matiere FROM Matiere WHERE ue='INF101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (8.5, 2, (SELECT id_matiere FROM Matiere WHERE ue='INF104'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.5,2, (SELECT id_matiere FROM Matiere WHERE ue='INF107'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,2, (SELECT id_matiere FROM Matiere WHERE ue='MTH101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.5,2, (SELECT id_matiere FROM Matiere WHERE ue='MTH102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.0,2, (SELECT id_matiere FROM Matiere WHERE ue='ORG101'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- Etudiant 3
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (11.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF104'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF107'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,3,(SELECT id_matiere FROM Matiere WHERE ue='ORG101'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- (Students 4 and 5 similar S1)
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (9.5,4,(SELECT id_matiere FROM Matiere WHERE ue='INF101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.0,4,(SELECT id_matiere FROM Matiere WHERE ue='INF104'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (11.0,4,(SELECT id_matiere FROM Matiere WHERE ue='INF107'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,4,(SELECT id_matiere FROM Matiere WHERE ue='MTH101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,4,(SELECT id_matiere FROM Matiere WHERE ue='MTH102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,4,(SELECT id_matiere FROM Matiere WHERE ue='ORG101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),

  (15.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.5,5,(SELECT id_matiere FROM Matiere WHERE ue='INF104'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF107'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.5,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH101'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (11.0,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (16.0,5,(SELECT id_matiere FROM Matiere WHERE ue='ORG101'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- ---------- NOTES S2 (Normale L1) ----------
-- We'll insert at least one attempt per UE S2 per student
-- Student 1 S2
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (13.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (11.5,1,(SELECT id_matiere FROM Matiere WHERE ue='INF105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF106'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.5,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH105'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- Student 2 S2
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (10.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (9.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (11.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF106'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.0,2,(SELECT id_matiere FROM Matiere WHERE ue='MTH103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.5,2,(SELECT id_matiere FROM Matiere WHERE ue='MTH105'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- (Students 3..5 S2)
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (15.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF106'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),

  (11.0,4,(SELECT id_matiere FROM Matiere WHERE ue='INF102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,4,(SELECT id_matiere FROM Matiere WHERE ue='INF103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (10.5,4,(SELECT id_matiere FROM Matiere WHERE ue='INF105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,4,(SELECT id_matiere FROM Matiere WHERE ue='INF106'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (11.5,4,(SELECT id_matiere FROM Matiere WHERE ue='MTH103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (12.0,4,(SELECT id_matiere FROM Matiere WHERE ue='MTH105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),

  (15.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF102'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF105'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.5,5,(SELECT id_matiere FROM Matiere WHERE ue='INF106'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (13.5,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH103'), (SELECT id_session FROM Session WHERE intitule='Normale L1')),
  (14.0,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH105'), (SELECT id_session FROM Session WHERE intitule='Normale L1'));

-- ---------- NOTES S3 (Normale L2) ----------
-- Students have exams for S3
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  -- Student 1 S3
  (13.5,1,(SELECT id_matiere FROM Matiere WHERE ue='INF201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF202'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (11.5,1,(SELECT id_matiere FROM Matiere WHERE ue='INF203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF208'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.0,1,(SELECT id_matiere FROM Matiere WHERE ue='ORG201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Student 2 S3
  (11.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (10.5,2,(SELECT id_matiere FROM Matiere WHERE ue='INF202'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.5,2,(SELECT id_matiere FROM Matiere WHERE ue='INF208'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.5,2,(SELECT id_matiere FROM Matiere WHERE ue='MTH201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,2,(SELECT id_matiere FROM Matiere WHERE ue='ORG201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Student 3 S3
  (15.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF202'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (15.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF208'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH201'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.5,3,(SELECT id_matiere FROM Matiere WHERE ue='ORG201'), (SELECT id_session FROM Session WHERE intitule='Normale L2'));

-- ---------- NOTES S4 (Normale L2) ----------
-- IMPORTANT: for S4, students will have exam rows for:
--   - all mandatory UEs (option = 0) of their parcours
--   - possibly multiple UEs inside an option group (option = 1 or 2) so we can pick the best one

-- Student 1 (choisit Parcours Développement) : we'll insert notes on INF204, INF205, INF206 (group 1) and MTH204,MTH205,MTH206 (group 2) to simulate choix -> best retained
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  -- Obligatoires (Développement): INF207, INF210, MTH203
  (13.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF207'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.5,1,(SELECT id_matiere FROM Matiere WHERE ue='INF210'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Groupe option 1 (INF204 / INF205 / INF206) -> student passed 2 UEs, best should be taken
  (11.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF204'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (15.0,1,(SELECT id_matiere FROM Matiere WHERE ue='INF205'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (10.5,1,(SELECT id_matiere FROM Matiere WHERE ue='INF206'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Groupe option 2 (MTH204 / MTH205 / MTH206) -> student took all, best should be used
  (13.0,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH204'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.5,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH205'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.0,1,(SELECT id_matiere FROM Matiere WHERE ue='MTH206'), (SELECT id_session FROM Session WHERE intitule='Normale L2'));

-- Student 2 (parcours Bases de Données et Réseaux)
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  -- Obligatoire INF205 (for BDD&R)
  (10.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF205'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.5,2,(SELECT id_matiere FROM Matiere WHERE ue='INF211'), (SELECT id_session FROM Session WHERE intitule='Normale L2')), -- mini-projet
  (11.0,2,(SELECT id_matiere FROM Matiere WHERE ue='MTH203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Option groupe 1 (INF204, INF206, INF207) - student tried INF204 and INF206
  (12.0,2,(SELECT id_matiere FROM Matiere WHERE ue='INF204'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.5,2,(SELECT id_matiere FROM Matiere WHERE ue='INF206'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Option groupe 2 (MTH202, MTH205, MTH206) - student took MTH202 & MTH206
  (13.0,2,(SELECT id_matiere FROM Matiere WHERE ue='MTH202'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,2,(SELECT id_matiere FROM Matiere WHERE ue='MTH206'), (SELECT id_session FROM Session WHERE intitule='Normale L2'));

-- Student 3 (parcours Web et Design) — gives variety
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  -- Obligatoires
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF212'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Option groupe 1 (INF204, INF205, INF206, INF209) -> student took INF209 and INF206
  (15.0,3,(SELECT id_matiere FROM Matiere WHERE ue='INF209'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.5,3,(SELECT id_matiere FROM Matiere WHERE ue='INF206'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),

  -- Option groupe 2 (MTH202, MTH204, MTH206) -> student took MTH204 only
  (14.0,3,(SELECT id_matiere FROM Matiere WHERE ue='MTH204'), (SELECT id_session FROM Session WHERE intitule='Normale L2'));

-- Student 4 (mix) - take some options but not all
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (11.5,4,(SELECT id_matiere FROM Matiere WHERE ue='INF207'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.0,4,(SELECT id_matiere FROM Matiere WHERE ue='INF210'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (10.0,4,(SELECT id_matiere FROM Matiere WHERE ue='MTH203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (12.5,4,(SELECT id_matiere FROM Matiere WHERE ue='INF205'), (SELECT id_session FROM Session WHERE intitule='Normale L2')), -- group 1
  (11.0,4,(SELECT id_matiere FROM Matiere WHERE ue='MTH204'), (SELECT id_session FROM Session WHERE intitule='Normale L2')); -- group 2

-- Student 5 (strong student across all)
INSERT INTO Examen (note, id_etudiant, id_matiere, id_session) VALUES
  (16.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF207'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (15.5,5,(SELECT id_matiere FROM Matiere WHERE ue='INF210'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.0,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH203'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF204'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.5,5,(SELECT id_matiere FROM Matiere WHERE ue='INF205'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (15.0,5,(SELECT id_matiere FROM Matiere WHERE ue='INF206'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (14.0,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH204'), (SELECT id_session FROM Session WHERE intitule='Normale L2')),
  (13.0,5,(SELECT id_matiere FROM Matiere WHERE ue='MTH205'), (SELECT id_session FROM Session WHERE intitule='Normale L2'));

-- End of data inserts