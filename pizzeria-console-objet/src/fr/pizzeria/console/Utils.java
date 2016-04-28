package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class Utils {
	
	
	/**
	 * Recherche l'index de la Pizza à partir de son code
	 * @param code code à rechercher
	 * @param pizzas liste cible
	 * @return int index de la pizza, -1 si erreur
	 */
	public static int rechercheIndexByCode(String code, Pizza[] pizzas){
		int index = -1;
		for (int i = 0; i<pizzas.length; i++) {
			if (pizzas[i]!= null&&pizzas[i].getCode().equals(code)) {
				index = i;
				break;
			}
		}
		return index;
	}
	

	
	/**
	 * Saisie de MAJ pizza
	 * @param sc interface de saisie
	 * @param pizza à MAJ 
	 * @return Pizza pizza créée
	 */
	public static Pizza MAJPizza(Scanner sc, Pizza pizza){
		Pizza newPizza = new Pizza();
		System.out.println("Veuillez saisir le code...");
		newPizza.setCode(sc.next());
		System.out.println("Veuillez saisir le nom (sans espace)...");
		newPizza.setNom(sc.next());
		System.out.println("Veuillez saisir le prix...");
		newPizza.setPrix(sc.nextDouble());
		newPizza.setId(pizza.getId());
		return newPizza;
	}
}
