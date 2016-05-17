package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.StatutCommande;

public class ExpedierCommandeOptionMenu extends AbstractOptionMenu {

	private static final String EXPEDIER_UNE_COMMANDE = "Expédier une commande";
	private static final String MENU_MSG_SAISIE_CODE = "code...";

	public ExpedierCommandeOptionMenu(Scanner scanner, IDaoFactory pizzaDao) {
		super(EXPEDIER_UNE_COMMANDE, scanner, pizzaDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		System.out.println(EXPEDIER_UNE_COMMANDE);
		ICommandeDao commandeDao = pizzaDao.createCommandeDao();
		affichageListeCommandes(commandeDao);
		Commande commande = saisieCommande(sc, commandeDao);

		if (commande != null) {
			commande.setStatut(StatutCommande.EXPEDIE);
			try {
				commandeDao.updateCommande(commande);
			} catch (DaoException e) {
				System.err.println(e.getMessage());
			}
			System.out.println(commande);
		}
		return true;
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

	private void affichageListeCommandes(ICommandeDao commandeDao) {
		List<Commande> commandes = commandeDao.findCommandesNonTraitees();
		if (commandes != null) {
			commandes.stream().sorted(Comparator.comparing(Commande::getNoCommande)).forEach(System.out::println);
		}

	}

}
