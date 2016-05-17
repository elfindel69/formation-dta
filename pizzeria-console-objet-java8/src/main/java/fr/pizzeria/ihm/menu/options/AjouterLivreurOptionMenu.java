package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.ILivreurDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Livreur;

public class AjouterLivreurOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_UN_LIVREUR = "Ajouter un Livreur";
	private static final String MENU_MSG_SAISIE_CODE = "code...";

	public AjouterLivreurOptionMenu(Scanner scanner, IDaoFactory pizzaDao) {
		super(AJOUTER_UN_LIVREUR, scanner, pizzaDao);
	}

	@Override
	public boolean execute() {
		System.out.println(AJOUTER_UN_LIVREUR);
		ICommandeDao commandeDao = pizzaDao.createCommandeDao();
		affichageListeCommandes(commandeDao);
		Commande commande = saisieCommande(sc, commandeDao);
		
		if(commande != null){
			ILivreurDao livreurDao = pizzaDao.createLivreurDao();
			affichageListeLivreurs(livreurDao);
			Livreur livreur = saisieLivreur(sc, livreurDao);
			commande.setLivreur(livreur);
			try {
				commandeDao.updateCommande(commande);
			} catch (DaoException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			System.out.println(commande);
		}
	
		return true;
	}

	private Livreur saisieLivreur(Scanner sc, ILivreurDao livreurDao) {
		System.out.println(MENU_MSG_SAISIE_CODE);
		String nom = sc.next();
		Livreur livreur = null;
		try {
			livreur = livreurDao.getLivreurByNom(nom);
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		return livreur;
	}

	private void affichageListeLivreurs(ILivreurDao livreurDao) {

		List<Livreur> livreurs = livreurDao.findAddLivreurs();
		if (livreurs != null) {
			livreurs.stream().sorted(Comparator.comparing(Livreur::getNom)).forEach(System.out::println);
		}

	}

	private Commande saisieCommande(Scanner sc, ICommandeDao commandeDao) {

		System.out.println(MENU_MSG_SAISIE_CODE);
		String code = sc.next();
		Commande commande = null;
		try {
			commande = commandeDao.getCommandeByCode(code);
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		}
		return commande;
	}

	/**
	 * Affichage de la liste
	 * 
	 * @param commandeDao
	 */
	void affichageListeCommandes(ICommandeDao commandeDao) {

		List<Commande> commandes = commandeDao.findCommandesNonTraitees();
		if (commandes != null) {
			commandes.stream().sorted(Comparator.comparing(Commande::getNoCommande)).forEach(System.out::println);
		}

	}

}
