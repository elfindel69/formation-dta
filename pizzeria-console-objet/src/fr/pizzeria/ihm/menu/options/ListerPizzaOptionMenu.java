package fr.pizzeria.ihm.menu.options;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZA_MSG = "Liste des Pizzas";
	private static final String LISTER_PIZZA_LIBELLE_MENU = "Lister pizzas";
	
	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(LISTER_PIZZA_LIBELLE_MENU,pizzaDao);
	}

	@Override
	public boolean execute() {
		System.out.println(LISTER_PIZZA_MSG);
		Pizza[] pizzas = pizzaDao.findAllPizzas();
		for (Pizza p : pizzas) {
			if (p != null) {
				System.out.println(p);
			}
		}
		System.out.println("-------" + Pizza.nbPizzas + " pizzas cr��es depuis l�initialisation du programme");
		return true;
	}

}