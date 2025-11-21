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
