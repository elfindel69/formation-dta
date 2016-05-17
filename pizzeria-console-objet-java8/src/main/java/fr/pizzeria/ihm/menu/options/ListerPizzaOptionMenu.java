package fr.pizzeria.ihm.menu.options;

import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Pizza;

/**
 * Affichage de la liste des Pizzas
 * @author Valentin
 *
 */
public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZA_MSG_NB_PIZZAS = " pizzas créées depuis l'initialisation du programme";
	private static final String LISTER_PIZZA_MSG = "Liste des Pizzas";
	private static final String LISTER_PIZZA_LIBELLE_MENU = "Lister pizzas";
	
	/**
	 * Constructeur
	 * @param daoFact - lien vers la DAO
	 */
	public ListerPizzaOptionMenu(IDaoFactory daoFact) {
		super(LISTER_PIZZA_LIBELLE_MENU,daoFact);
	}

	/**
	 * execution du menu
	 * @return flag d'execution (true)
	 */
	@Override
	public boolean execute() {
		System.out.println(LISTER_PIZZA_MSG);
		affichageListe();
		System.out.println("-------" + Pizza.getNbPizzas() + LISTER_PIZZA_MSG_NB_PIZZAS);
		return true;
	}

}
