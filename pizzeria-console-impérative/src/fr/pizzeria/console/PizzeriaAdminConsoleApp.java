package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		int menu1 = 0;
		Scanner sc = new Scanner(System.in);
		Object[][] pizzas = new Object[8][];
		pizzas[0] = new Object[3];
		pizzas[0][0] = "PEP";
		pizzas[0][1] = "Pépéroni";
		pizzas[0][2] = 12.50;
		pizzas[1] = new Object[3];
		pizzas[1][0] = "MAR";
		pizzas[1][1] = "Margherita";
		pizzas[1][2] = 14.00;
		pizzas[2] = new Object[3];
		pizzas[2][0] = "REI";
		pizzas[2][1] = "Reine";
		pizzas[2][2] = 11.50;
		pizzas[3] = new Object[3];
		pizzas[3][0] = "FRO";
		pizzas[3][1] = "La 4 fromages";
		pizzas[3][2] = 12.00;
		pizzas[4] = new Object[3];
		pizzas[4][0] = "CAN";
		pizzas[4][1] = "La cannibale";
		pizzas[4][2] = 12.50;
		pizzas[5] = new Object[3];
		pizzas[5][0] = "SAV";
		pizzas[5][1] = "La savoyarde";
		pizzas[5][2] = 13.00;
		pizzas[6] = new Object[3];
		pizzas[6][0] = "ORI";
		pizzas[6][1] = "L'orientale";
		pizzas[6][2] = 13.50;
		pizzas[7] = new Object[3];
		pizzas[7][0] = "IND";
		pizzas[7][1] = "L'indienne";
		pizzas[7][2] = 14.00;
		
		do{ 
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			 menu1 = sc.nextInt();
			switch(menu1){
				case 1 : //Liste
					System.out.println("Liste des pizzas");
					for(Object[] item : pizzas){
						System.out.println(item[0]+" -> "+item[1]+"("+item[2]+"€)");
					}
					break; 
				case 2 :
					System.out.println("Ajout d'une nouvelle pizza");
					break;
				case 3 :
					System.out.println("Mise à jour d'une pizza");
					break;
				case 4 :
					System.out.println("Supprimer une pizza");
					break;
				default:
					System.out.println("Aurevoir :(");
					break;
			}
	
		}while(menu1 != 99);
		sc.close();
	}

}
