package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.options.AbstractOptionMenu;
import fr.pizzeria.ihm.menu.options.GrouperPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.ImportPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.ListerCommandesNonTraiteesOptionMenu;
import fr.pizzeria.ihm.menu.options.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.MAJPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.MaxPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.QuitterOptionMenu;
import fr.pizzeria.ihm.menu.options.SupprimerPizzaOptionMenu;
import fr.pizzeria.model.DesactiverOptionMenu;

@Component
public class Menu {
	private static final String MENU_LIBELLE = "Pizzeria Administration";
	private Map<Integer, AbstractOptionMenu> mapMenus;
	@Autowired
	private Scanner sc;
	@Autowired
	private IDaoFactory daoFact;
	private boolean menuJdbc;

	public Menu(boolean menuJdbc) {
		super();
		this.menuJdbc = menuJdbc;
	}

	@PostConstruct
	private void initialiserOptions() {
		mapMenus = new TreeMap<>();
		mapMenus.put(1, new ListerPizzaOptionMenu(daoFact));
		mapMenus.put(2, new NouvellePizzaOptionMenu(sc, daoFact));
		mapMenus.put(3, new MAJPizzaOptionMenu(sc, daoFact));
		mapMenus.put(4, new SupprimerPizzaOptionMenu(sc, daoFact));
		mapMenus.put(5, new GrouperPizzaOptionMenu(daoFact));
		mapMenus.put(6, new MaxPizzaOptionMenu(daoFact));
		if (menuJdbc) {
			mapMenus.put(7, new ImportPizzaOptionMenu(daoFact));
			mapMenus.put(8, new ListerCommandesNonTraiteesOptionMenu(daoFact));
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
