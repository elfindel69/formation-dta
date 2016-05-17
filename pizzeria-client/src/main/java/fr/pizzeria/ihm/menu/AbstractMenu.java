package fr.pizzeria.ihm.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.options.AbstractOptionMenu;

public abstract class AbstractMenu {
	protected String titre;
	protected Map<Integer, AbstractOptionMenu> mapMenus = new TreeMap<>();
	protected Scanner sc;

	public AbstractMenu(String titre, Scanner sc, IDaoFactory daoFact) {
		super();
		this.titre = titre;
		initialiserOptions(sc, daoFact);
		this.sc = sc;
	}

	protected abstract void initialiserOptions(Scanner sc2, IDaoFactory daoFact);

	public void afficher() {
		boolean continuer = true;
		int saisie;
		while (continuer) {
			System.out.println("*****" + titre + "*****");
			mapMenus.forEach((key, value) -> System.out.println(key + ". " + value.getLibelle()));
			saisie = sc.nextInt();
			continuer = mapMenus.get(saisie).execute();
		}

	}
}
