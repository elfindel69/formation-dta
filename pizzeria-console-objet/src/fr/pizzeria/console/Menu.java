package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class Menu {
	
	
	public static void afficherMenu(){
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
	}
	
	
	public static void majPizza(Scanner sc,Pizza[] pizzas){
		System.out.println("Mise à jour d'une pizza");
		//Utils.afficherListePizza(pizzas);
		System.out.println("Veuillez choisir la pizza à modifier (code)");
		System.out.println("99 pour abandonner");
		String code2 = sc.next();

		if (code2 != "99") {
			int index= Utils.rechercheIndexByCode(code2,pizzas);
			if(index != -1){
				pizzas[index] = Utils.MAJPizza(sc,pizzas[index]);
			}else{
				System.out.println("erreur, cette pizza n'existe pas");
			}
		}
	}
	
	public static void supprPizza(Scanner sc,Pizza[] pizzas){
		System.out.println("Supprimer une pizza");
		//Utils.afficherListePizza(pizzas);
		System.out.println("Veuillez choisir la pizza à supprimer (code)");
		System.out.println("99 pour abandonner");
		String code3 = sc.next();
		if (code3 != "99") {
			int index = Utils.rechercheIndexByCode(code3,pizzas);
			if(index != -1){
				pizzas[index] = null;
				--Pizza.nbPizzas;
			}else{
				System.out.println("erreur, cette pizza n'existe pas");
			}
		}
	}
}
