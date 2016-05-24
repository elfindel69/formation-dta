package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.List;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Commande;

/**
 * Affichage de la liste des Pizzas
 * 
 * @author Valentin
 *
 */
public class ListerCommandesNonTraiteesOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_LES_COMMANDES = "Lister les commandes non traitées";
	private IDaoFactory daoFact;
	private ICommandeDao commandeDao;

	/**
	 * Constructeur
	 * 
	 * @param daoFact
	 *            - lien vers la DAO
	 */
	public ListerCommandesNonTraiteesOptionMenu(IDaoFactory daoFact) {
		super(LISTER_LES_COMMANDES, daoFact);

	}

	/**
	 * execution du menu
	 * 
	 * @return flag d'execution (true)
	 */
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
		List<Commande> commandes = commandeDao.findCommandesNonTraitees();
		if (commandes != null) {
			commandes.stream().sorted(Comparator.comparing(Commande::getNoCommande)).forEach(System.out::println);
		}

	}
}
