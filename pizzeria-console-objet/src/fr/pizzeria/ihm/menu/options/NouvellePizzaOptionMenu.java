package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class NouvellePizzaOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_MSG = "Ajout d'une nouvelle pizza";
	private static final String AJOUTER_LIBELLE_MENU = "Ajouter une nouvelle pizza";

	public NouvellePizzaOptionMenu(Scanner sc,IPizzaDao dao) {
		super(AJOUTER_LIBELLE_MENU,sc,dao);
	}

	@Override
	public boolean execute() {
		System.out.println(AJOUTER_MSG);
		System.out.println("Veuillez saisir le code...");
		String code = sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)...");
		String nom = sc.next();
		System.out.println("Veuillez saisir le prix...");
		Double prix = sc.nextDouble();
		boolean savePizza = pizzaDao.savePizza(new Pizza(code,nom,prix));
		if(savePizza){
			System.out.println("Pizza ajoutée ^^");
		}
		else{
			System.out.println("Erreur, plus de place possible");
		}
		return savePizza;
	}

}



