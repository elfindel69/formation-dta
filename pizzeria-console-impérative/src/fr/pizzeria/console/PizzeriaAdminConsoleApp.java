package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		int menu1 = 0;
		Scanner sc = new Scanner(System.in);
		Object[][] pizzas = new Object[100][3];
		pizzas[0] = new Object[] { "PEP", "Pépéroni", 12.50 };
		pizzas[1] = new Object[] { "MAR", "Margherita", 14.00 };
		pizzas[2] = new Object[] { "REI", "Reine", 11.50 };
		pizzas[3] = new Object[] { "FRO", "La 4 fromages", 12.00 };
		pizzas[4] = new Object[] { "CAN", "La cannibale", 12.50 };
		pizzas[5] = new Object[] { "SAV", "La savoyarde", 13.00 };
		pizzas[6] = new Object[] { "ORI", "L'orientale", 13.50 };
		pizzas[7] = new Object[] { "IND", "L'indienne", 14.00 };

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
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] != null) {
						System.out.println(pizzas[i][0] + " -> " + pizzas[i][1] + "(" + pizzas[i][2] + "€)");
					}
				}
				break;
			case 2:// ajout
				System.out.println("Ajout d'une nouvelle pizza");
				int pizzToAdd = -1;
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] == null) {
						pizzToAdd = i;
						break;
					}
				}
				System.out.println("Veuillez saisir le code...");
				String code = sc.next();
				System.out.println("Veuillez saisir le nom (sans espace)...");
				String nom = sc.next();
				System.out.println("Veuillez saisir le prix...");
				Double prix = sc.nextDouble();
				pizzas[pizzToAdd] = new Object[] { code, nom, prix };

				break;
			case 3:// MAJ
				System.out.println("Mise à jour d'une pizza");
				System.out.println("Liste des pizzas");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] != null) {
						System.out.println(pizzas[i][0] + " -> " + pizzas[i][1] + "(" + pizzas[i][2] + "€)");
					}
				}
				System.out.println("Veuillez choisir la pizza à modifier (code)");
				System.out.println("99 pour abandonner");
				String code2 = sc.next();

				if (code2 != "99") {
					int index = -1;
					for (int i = 0; i < pizzas.length; i++) {
						if (pizzas[i][0].equals(code2)) {
							index = i;
							break;
						}
					}
					System.out.println("Veuillez saisir le code...");
					String code1 = sc.next();
					System.out.println("Veuillez saisir le nom (sans espace)...");
					String nom1 = sc.next();
					System.out.println("Veuillez saisir le prix...");
					Double prix1 = sc.nextDouble();
					pizzas[index] = new Object[] { code1, nom1, prix1 };
				}
				break;
			case 4:// suppr
				System.out.println("Supprimer une pizza");
				System.out.println("Liste des pizzas");
				for (int i = 0; i < pizzas.length; i++) {
					if (pizzas[i][0] != null) {
						System.out.println(pizzas[i][0] + " -> " + pizzas[i][1] + "(" + pizzas[i][2] + "€)");
					}
				}
				System.out.println("Veuillez choisir la pizza à modifier (code)");
				System.out.println("99 pour abandonner");
				String code3 = sc.next();
				if (code3 != "99") {
					int index = -1;
					for (int i = 0; i < pizzas.length; i++) {
						if (pizzas[i][0].equals(code3)) {
							index = i;
							break;
						}
					}
					pizzas[index] = new Object[3];
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
