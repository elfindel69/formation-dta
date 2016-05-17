package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class ListerCommandesOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_LES_COMMANDES = "Lister les commandes";
	private Client client;
	private ICommandeDao commandeDao;

	public ListerCommandesOptionMenu(Scanner scanner, Client newClient, IDaoFactory daoFact) {
		super(LISTER_LES_COMMANDES, scanner, daoFact);
		this.client = newClient;

	}

	@Override
	public boolean execute() {
		System.out.println(LISTER_LES_COMMANDES);
		commandeDao = daoFact.createCommandeDao();
		affichageListeCommandes();

		return true;
	}

	/**
	 * Affichage de la liste
	 */
	void affichageListeCommandes() {
		List<Commande> commandes = commandeDao.findAllCommandes(client);
		if (commandes != null) {
			commandes.stream().sorted(Comparator.comparing(Commande::getNoCommande)).forEach(System.out::println);
		}

	}
}
