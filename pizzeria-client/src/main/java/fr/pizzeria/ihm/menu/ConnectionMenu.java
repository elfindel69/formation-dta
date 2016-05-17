package fr.pizzeria.ihm.menu;

import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.options.CommanderPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.ListerCommandesOptionMenu;
import fr.pizzeria.ihm.menu.options.QuitterOptionMenu;
import fr.pizzeria.model.Client;

public class ConnectionMenu extends AbstractMenu {
	protected static final String MENU_LIBELLE = "Pizzeria Client";
	public ConnectionMenu(Scanner sc, Client client, IDaoFactory daoFact) {
		super(MENU_LIBELLE, sc, daoFact);
		initialiserOptions(sc,client,daoFact);
	}

	
	protected void initialiserOptions(Scanner sc2, Client client, IDaoFactory daoFact) {
		mapMenus = new TreeMap<>();
		mapMenus.put(1, new CommanderPizzaOptionMenu(sc2, client, daoFact));
		 mapMenus.put(2, new ListerCommandesOptionMenu(sc2,client, daoFact));
		mapMenus.put(99, new QuitterOptionMenu());

	}


	@Override
	protected void initialiserOptions(Scanner sc2, IDaoFactory daoFact) {
		// TODO Auto-generated method stub
		
	}

}
