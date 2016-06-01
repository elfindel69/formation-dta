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

	private Scanner sc;
	private IDaoFactory daoFactory;

	@Autowired
	public Menu(Scanner sc, IDaoFactory daoFactory) {
		super();

		this.daoFactory = daoFactory;
		this.sc = sc;
	}

	@PostConstruct
	private void initialiserOptions() {
		mapMenus = new TreeMap<>();
		mapMenus.put(1, new ListerPizzaOptionMenu(daoFactory));
		mapMenus.put(2, new NouvellePizzaOptionMenu(sc, daoFactory));
		mapMenus.put(3, new MAJPizzaOptionMenu(sc, daoFactory));
		mapMenus.put(4, new SupprimerPizzaOptionMenu(sc, daoFactory));
		mapMenus.put(5, new GrouperPizzaOptionMenu(daoFactory));
		mapMenus.put(6, new MaxPizzaOptionMenu(daoFactory));
		mapMenus.put(7, new ImportPizzaOptionMenu(daoFactory));
		mapMenus.put(8, new ListerCommandesNonTraiteesOptionMenu(daoFactory));

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
