package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.options.AbstractOptionMenu;
import fr.pizzeria.ihm.menu.options.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.MAJPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.options.SupprimerPizzaOptionMenu;

public class Menu {
	private static final String MENU_LIBELLE = "Pizzeria Administration";
	private Map<Integer, AbstractOptionMenu> mapMenus;
	private Scanner sc;

	public Menu(Scanner sc, IPizzaDao pizzaDao) {
		super();
		initialiserOptions(sc, pizzaDao);
		this.sc = sc;
	}

	private void initialiserOptions(Scanner scanner, IPizzaDao pizzaDao) {
		mapMenus = new TreeMap<Integer, AbstractOptionMenu>();
		mapMenus.put(1, new ListerPizzaOptionMenu(pizzaDao));
		mapMenus.put(2, new NouvellePizzaOptionMenu(scanner, pizzaDao));
		mapMenus.put(3, new MAJPizzaOptionMenu(scanner, pizzaDao));
		mapMenus.put(4, new SupprimerPizzaOptionMenu(scanner, pizzaDao));
		mapMenus.put(99, new QuitterOptionMenu());
	}

	public void afficher() {

		boolean continuer = true;
		int saisie;
		while (continuer) {
			System.out.println("*****" + MENU_LIBELLE + "*****");
			for (Entry<Integer,AbstractOptionMenu> menuEntry: mapMenus.entrySet()) {
				System.out.println(menuEntry.getKey() + ". " + menuEntry.getValue().getLibelle());
			}
			
			saisie = sc.nextInt();
			continuer = mapMenus.get(saisie).execute();
		}
	}
}
