package fr.pizzeria.ihm.menu.options;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Affichage de la liste des Pizzas
 * @author Valentin
 *
 */
public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZA_MSG_NB_PIZZAS = " pizzas créées depuis l�initialisation du programme";
	private static final String LISTER_PIZZA_MSG = "Liste des Pizzas";
	private static final String LISTER_PIZZA_LIBELLE_MENU = "Lister pizzas";
	
	/**
	 * Constructeur
	 * @param pizzaDao - lien vers la DAO
	 */
	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(LISTER_PIZZA_LIBELLE_MENU,pizzaDao);
	}

	/**
	 * execution du menu
	 * @return flag d'execution (true)
	 */
	@Override
	public boolean execute() {
		System.out.println(LISTER_PIZZA_MSG);
		affichageListe();
		System.out.println("-------" + Pizza.nbPizzas + LISTER_PIZZA_MSG_NB_PIZZAS);
		return true;
	}

}
