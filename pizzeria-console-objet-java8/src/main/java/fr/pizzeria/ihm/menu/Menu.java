package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.options.AbstractOptionMenu;
import fr.pizzeria.ihm.menu.options.GrouperPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.ImportPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.MAJPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.MaxPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.options.SupprimerPizzaOptionMenu;
import fr.pizzeria.model.DesactiverOptionMenu;

public class Menu {
	private static final String MENU_LIBELLE = "Pizzeria Administration";
	private Map<Integer, AbstractOptionMenu> mapMenus;
	private Scanner sc;

	public Menu(Scanner sc, IPizzaDao pizzaDao, boolean menuJdbc) {
		super();
		initialiserOptions(sc, pizzaDao,menuJdbc);
		this.sc = sc;
	}

	private void initialiserOptions(Scanner scanner, IPizzaDao pizzaDao,boolean menuJdbc) {
		mapMenus = new TreeMap<>();
		mapMenus.put(1, new ListerPizzaOptionMenu(pizzaDao));
		mapMenus.put(2, new NouvellePizzaOptionMenu(scanner, pizzaDao));
		mapMenus.put(3, new MAJPizzaOptionMenu(scanner, pizzaDao));
		mapMenus.put(4, new SupprimerPizzaOptionMenu(scanner, pizzaDao));
		mapMenus.put(5, new GrouperPizzaOptionMenu(pizzaDao));
		mapMenus.put(6, new MaxPizzaOptionMenu(pizzaDao));
		if(menuJdbc){
			mapMenus.put(7, new ImportPizzaOptionMenu(pizzaDao));
		}
		mapMenus.put(99, new QuitterOptionMenu());
		
	}

	public void afficher() {

		boolean continuer = true;
		int saisie;
		while (continuer) {
			System.out.println("*****" + MENU_LIBELLE + "*****");
			mapMenus.forEach((key, value) -> {
				DesactiverOptionMenu annotation = value.getClass().getAnnotation(DesactiverOptionMenu.class);

				if (annotation == null) {
					System.out.println(key + ". " + value.getLibelle());
				} else {
					System.out.println(annotation.libelleOption());
				}
			});

			saisie = sc.nextInt();
			continuer = mapMenus.get(saisie).execute();
		}
	}
}
