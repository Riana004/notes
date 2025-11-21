-- ============================
-- INSERTION annee_etude
-- ============================
INSERT INTO annee_etude (nom) VALUES 
('Licence 1'),
('Licence 2'),
('Licence 3'),
('Master 1'),
('Master 2');

-- ============================
-- INSERTION etudiant
-- ============================
INSERT INTO etudiant (nom, prenom, etu) VALUES 
('Martin', 'Jean', 'ETU001'),
('Dubois', 'Marie', 'ETU002'),
('Bernard', 'Pierre', 'ETU003'),
('Thomas', 'Sophie', 'ETU004'),
('Robert', 'Luc', 'ETU005');

-- ============================
-- INSERTION annee_universitaire
-- ============================
INSERT INTO annee_universitaire (annee_debut, annee_fin) VALUES 
(2022, 2023),
(2023, 2024),
(2024, 2025);

-- ============================
-- INSERTION session
-- ============================
INSERT INTO session (periode_annee, periode_mois, id_annee_universitaire) VALUES 
-- Sessions 2022-2023
(2022, 1, 1), -- Session 1
(2023, 6, 1), -- Session 2

-- Sessions 2023-2024
(2023, 1, 2), -- Session 1
(2024, 6, 2), -- Session 2

-- Sessions 2024-2025
(2024, 1, 3), -- Session 1
(2025, 6, 3); -- Session 2

-- ============================
-- INSERTION inscription
-- ============================
INSERT INTO inscription (id_etudiant, id_annee_etude, id_annee_universitaire) VALUES 
-- Jean Martin : L1 en 2022-2023, L2 en 2023-2024, L3 en 2024-2025
(1, 1, 1), -- L1 2022-2023
(1, 2, 2), -- L2 2023-2024
(1, 3, 3), -- L3 2024-2025

-- Marie Dubois : L1 en 2022-2023, L2 en 2023-2024
(2, 1, 1), -- L1 2022-2023
(2, 2, 2), -- L2 2023-2024

-- Pierre Bernard : L1 en 2023-2024
(3, 1, 2); -- L1 2023-2024

-- ============================
-- INSERTION semestre
-- ============================
INSERT INTO semestre (id_annee_etude, nom) VALUES 
-- Licence 1
(1, 'Semestre 1'),
(1, 'Semestre 2'),

-- Licence 2
(2, 'Semestre 3'),
(2, 'Semestre 4'),

-- Licence 3
(3, 'Semestre 5'),
(3, 'Semestre 6');

-- ============================
-- INSERTION matiere
-- ============================
INSERT INTO matiere (id_semestre, code, intitule, credit) VALUES 
-- Semestre 1 (L1)
(1, 'MATH101', 'Mathematiques Fondamentales', 6),
(1, 'INFO101', 'Introduction a la Programmation', 6),
(1, 'PHY101', 'Physique Generale', 3),

-- Semestre 2 (L1)
(2, 'MATH102', 'Algebre Lineaire', 6),
(2, 'INFO102', 'Structures de Donnees', 6),
(2, 'ANG101', 'Anglais Technique', 3),

-- Semestre 3 (L2)
(3, 'INFO201', 'Algorithmique Avancee', 6),
(3, 'BD201', 'Bases de Donnees', 6),
(3, 'WEB201', 'Developpement Web', 3),

-- Semestre 4 (L2)
(4, 'INFO202', 'Systemes dExploitation', 6),
(4, 'RES202', 'Resaux Informatiques', 6),
(4, 'GES202', 'Gestion de Projet', 3),

-- Semestre 5 (L3)
(5, 'INFO301', 'Intelligence Artificielle', 6),
(5, 'SEC301', 'Securite Informatique', 6),
(5, 'DEV301', 'Developpement Mobile', 3),

-- Semestre 6 (L3)
(6, 'INFO302', 'Big Data', 6),
(6, 'CLO302', 'Cloud Computing', 6),
(6, 'PRO302', 'Projet de Fin dEtude', 6);

-- ============================
-- INSERTION note
-- ============================
INSERT INTO note (id_matiere, id_etudiant, id_session, valeur) VALUES 
-- Notes de Jean Martin (id=1)

-- L1 Semestre 1 - Session 1 (2022)
(1, 1, 1, 14.5),  -- MATH101
(2, 1, 1, 16.0),  -- INFO101
(3, 1, 1, 12.0),  -- PHY101

-- L1 Semestre 1 - Session 2 (2023) - Rattrapages
(1, 1, 2, 15.0),  -- MATH101 (ameliore)

-- L1 Semestre 2 - Session 2 (2023)
(4, 1, 2, 13.5),  -- MATH102
(5, 1, 2, 17.0),  -- INFO102
(6, 1, 2, 15.5),  -- ANG101

-- L2 Semestre 3 - Session 3 (2023)
(7, 1, 3, 14.0),  -- INFO201
(8, 1, 3, 16.5),  -- BD201
(9, 1, 3, 18.0),  -- WEB201

-- L2 Semestre 4 - Session 4 (2024)
(10, 1, 4, 12.5), -- INFO202
(11, 1, 4, 14.0), -- RES202
(12, 1, 4, 16.5), -- GES202

-- L3 Semestre 5 - Session 5 (2024)
(13, 1, 5, 15.0), -- INFO301
(14, 1, 5, 17.5), -- SEC301
(15, 1, 5, 14.0), -- DEV301

-- Notes de Marie Dubois (id=2)

-- L1 Semestre 1 - Session 1 (2022)
(1, 2, 1, 18.0),  -- MATH101
(2, 2, 1, 15.5),  -- INFO101
(3, 2, 1, 16.0),  -- PHY101

-- L1 Semestre 2 - Session 2 (2023)
(4, 2, 2, 17.5),  -- MATH102
(5, 2, 2, 14.0),  -- INFO102
(6, 2, 2, 16.5),  -- ANG101

-- L2 Semestre 3 - Session 3 (2023)
(7, 2, 3, 13.5),  -- INFO201
(8, 2, 3, 15.0),  -- BD201
(9, 2, 3, 17.0);  -- WEB201