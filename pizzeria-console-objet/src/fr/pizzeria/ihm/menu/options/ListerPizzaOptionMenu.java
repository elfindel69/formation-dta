package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

public class ListerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String LISTER_PIZZA_MSG = "Liste des Pizzas";
	private static final String LISTER_PIZZA_LIBELLE_MENU = "Lister pizzas";

	public ListerPizzaOptionMenu(Scanner sc) {
		super(LISTER_PIZZA_LIBELLE_MENU, sc);
	}

	@Override
	public boolean execute() {
		System.out.println(LISTER_PIZZA_MSG);
		/*for (int i = 0; i < tabPizza.length; i++) {
			if (tabPizza[i] != null) {
				System.out.println(tabPizza[i]);
			}
		}
		System.out.println("-------" + Pizza.nbPizzas + " pizzas créées depuis l’initialisation du programme");*/
		return true;
	}

}
