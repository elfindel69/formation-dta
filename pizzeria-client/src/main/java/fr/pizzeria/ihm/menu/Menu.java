package fr.pizzeria.ihm.menu;

import java.util.Scanner;

import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.options.ConnecterClientOptionMenu;
import fr.pizzeria.ihm.menu.options.InsererClientOptionMenu;
import fr.pizzeria.ihm.menu.options.QuitterOptionMenu;

public class Menu extends AbstractMenu {

	protected static final String MENU_LIBELLE = "Pizzeria Client";

	public Menu(Scanner sc, IDaoFactory daoFact) {
		super(MENU_LIBELLE, sc, daoFact);

	}

	@Override
	protected void initialiserOptions(Scanner sc2, IDaoFactory daoFact) {
		mapMenus.put(1, new InsererClientOptionMenu(sc2, daoFact));
		mapMenus.put(2, new ConnecterClientOptionMenu(sc2, daoFact));
		mapMenus.put(99, new QuitterOptionMenu());

	}

}
