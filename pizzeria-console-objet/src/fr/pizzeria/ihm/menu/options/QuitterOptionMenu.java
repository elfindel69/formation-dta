package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

public class QuitterOptionMenu extends AbstractOptionMenu {

	private static final String QUITTER_MSG = "Aurevoir :(";
	private static final String QUITTER_LIBELLE_MENU = "Quitter";

	public QuitterOptionMenu(Scanner sc) {
		super(QUITTER_LIBELLE_MENU,sc);
	}

	@Override
	public boolean execute() {
		System.out.println(QUITTER_MSG);
		return false;
	}

}
