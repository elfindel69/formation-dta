package fr.pizzeria.console;

import java.util.Scanner;

/**
 * Classe principale - administration de la pizzeria
 * @author ETY
 *
 */
public class PizzeriaAdminConsoleApp {
	/**
	 * Création d'une pizza
	 * @param code code ('AAA') de la pizza
	 * @param nom nom de la pizza
	 * @param prix prix de la pizza
	 * @return Pizza pizza créée 
	 */
	public static Pizza creerPizza(String code,String nom,double prix){
		Pizza pizza = new Pizza();
		pizza.id = Pizza.nbPizzas;
		Pizza.nbPizzas++;
		pizza.code = code;
		pizza.nom = nom;
		pizza.prix = prix;
		return pizza;
	}
	
	/**
	 * affiche la liste des pizzas
	 * @param pizzas liste à afficher
	 */
	public static void afficherListePizza(Pizza[] pizzas){
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i] != null) {
				System.out.println(pizzas[i].code + " -> " + pizzas[i].nom + "(" + pizzas[i].prix + "€)");
			}
		}
	}
	
	/**
	 * Recherche l'index de la Pizza à partir de son code
	 * @param code code à rechercher
	 * @param pizzas liste cible
	 * @return int index de la pizza, -1 si erreur
	 */
	public static int rechercheIndexByCode(String code, Pizza[] pizzas){
		int index = -1;
		for (int i = 0; i<pizzas.length; i++) {
			if (pizzas[i]!= null&&pizzas[i].code.equals(code)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	/**
	 * Saisie de création / MAJ pizza
	 * @param sc interface de saisie
	 * @return Pizza pizza créée
	 */
	public static Pizza ajoutPizza(Scanner sc){
		System.out.println("Veuillez saisir le code...");
		String code = sc.next();
		System.out.println("Veuillez saisir le nom (sans espace)...");
		String nom = sc.next();
		System.out.println("Veuillez saisir le prix...");
		Double prix = sc.nextDouble();
		return creerPizza(code,nom,prix);
	}
	
	/**
	 * main - Gestion des pizzas
	 * @param args
	 */
	public static void main(String[] args) {
		//choix du menu
		int menu1 = 0;
		//scanner
		Scanner sc = new Scanner(System.in);
		//liste des pizzas
		Pizza[] pizzas = new Pizza[100];
		pizzas[0] = creerPizza ( "PEP", "Pépéroni", 12.50 );
		pizzas[1] = creerPizza ( "MAR", "Margherita", 14.00 );
		pizzas[2] = creerPizza ( "REI", "Reine", 11.50 );
		pizzas[3] = creerPizza ( "FRO", "La 4 fromages", 12.00 );
		pizzas[4] = creerPizza ( "CAN", "La cannibale", 12.50 );
		pizzas[5] = creerPizza ( "SAV", "La savoyarde", 13.00 );
		pizzas[6] = creerPizza ( "ORI", "L'orientale", 13.50 );
		pizzas[7] = creerPizza ( "IND", "L'indienne", 14.00 );

		do {
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			menu1 = sc.nextInt();
			switch (menu1) {
			case 1: // Liste
				System.out.println("Liste des pizzas");
				afficherListePizza(pizzas);
				System.out.println("-------"+ Pizza.nbPizzas+" pizzas créées depuis l’initialisation du programme");
				break;
			case 2:// ajout
				System.out.println("Ajout d'une nouvelle pizza");
				int pizzToAdd = -1;
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i] == null) {
						pizzToAdd = i;
						break;
					}
				}
				pizzas[pizzToAdd] = ajoutPizza(sc);

				break;
			case 3:// MAJ
				System.out.println("Mise à jour d'une pizza");
				System.out.println("Liste des pizzas");
				afficherListePizza(pizzas);
				System.out.println("Veuillez choisir la pizza à modifier (code)");
				System.out.println("99 pour abandonner");
				String code2 = sc.next();

				if (code2 != "99") {
					int index= rechercheIndexByCode(code2,pizzas);
					if(index != -1){
						pizzas[index] = ajoutPizza(sc);
					}else{
						System.out.println("erreur, cette pizza n'existe pas");
					}
				}
				break;
			case 4:// suppr
				System.out.println("Supprimer une pizza");
				System.out.println("Liste des pizzas");
				afficherListePizza(pizzas);
				System.out.println("Veuillez choisir la pizza à supprimer (code)");
				System.out.println("99 pour abandonner");
				String code3 = sc.next();
				if (code3 != "99") {
					int index = rechercheIndexByCode(code3,pizzas);
					if(index != -1){
						pizzas[index] = null;
						--Pizza.nbPizzas;
					}else{
						System.out.println("erreur, cette pizza n'existe pas");
					}
				}
				break;
			default:
				System.out.println("Aurevoir :(");
				break;
			}

		} while (menu1 != 99);
		sc.close();
	}
}
