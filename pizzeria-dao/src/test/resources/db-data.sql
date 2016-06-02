INSERT INTO `pizza` (`id`, `categorie`, `code`, `nom`, `prix`, `url_image`) VALUES
(1, 'VIANDE', 'CAN', 'La cannibale', '12.50', NULL),
(2, 'SANS_VIANDE', 'FRO', 'La 4 fromages', '12.00', NULL),
(3, 'VIANDE', 'GAR', 'gargantua', '17.00', NULL),
(4, 'VIANDE', 'HAV', 'Havaienne', '10.00', NULL),
(5, 'VIANDE', 'IND', 'L''indienne', '14.00', NULL),
(6, 'SANS_VIANDE', 'MAR', 'Margherita', '14.00', NULL),
(7, 'VIANDE', 'ORI', 'L''orientale', '13.50', NULL),
(8, 'VIANDE', 'PEP', 'Peperoni', '12.50', NULL),
(9, 'VIANDE', 'REI', 'reine', '11.40', NULL),
(10, 'POISSON', 'SAM', 'La saumonetta', '14.00', NULL),
(11, 'SANS_VIANDE', 'VEG', 'vegan', '10.00', NULL);

INSERT INTO `client` (`id`, `email`, `nom`, `mot_de_passe`, `prenom`) VALUES
(1, 'toto@toto.fr', 'Titi', 'toto', 'Toto'),
(2, 'joe@dalton.fr', 'Dalton', 'joe', 'Joe');