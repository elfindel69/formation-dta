package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

public class NouvellePizzaOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_MSG = "Ajout d'une nouvelle pizza";
	private static final String AJOUTER_LIBELLE_MENU = "Ajouter une nouvelle pizza";

	public NouvellePizzaOptionMenu(Scanner sc) {
		super(AJOUTER_LIBELLE_MENU);
	}

	@Override
	public boolean execute() {
		System.out.println(AJOUTER_MSG);
		/*int pizzToAdd = -1;
		boolean placeTrouvee = false;
		for (int i = 0; i < tabPizza.length; i++) {
			if (tabPizza[i] == null) {
				pizzToAdd = i;
				placeTrouvee = true;
				break;
			}
		}
		if(placeTrouvee){
			System.out.println("Veuillez saisir le code...");
			String code = sc.next();
			System.out.println("Veuillez saisir le nom (sans espace)...");
			String nom = sc.next();
			System.out.println("Veuillez saisir le prix...");
			Double prix = sc.nextDouble();
			tabPizza[pizzToAdd] = new Pizza(code,nom,prix);
		}
		else{
			System.out.println("Erreur, plus de place possible");
		}*/
		return true;
	}

}



