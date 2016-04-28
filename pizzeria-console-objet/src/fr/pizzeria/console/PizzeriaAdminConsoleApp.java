package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

/**
 * Classe principale - administration de la pizzeria
 * @author Valentin
 *
 */
public class PizzeriaAdminConsoleApp {
	
	/**
	 * main - Gestion des pizzas
	 * @param args
	 */
	public static void main(String[] args) {
		
		//scanner
		Scanner sc = new Scanner(System.in);
		//liste des pizzas
		Pizza[] pizzas = new Pizza[100];
		pizzas[0] = new Pizza ( "PEP", "Pépéroni", 12.50 );
		pizzas[1] = new Pizza ( "MAR", "Margherita", 14.00 );
		pizzas[2] = new Pizza ( "REI", "Reine", 11.50 );
		pizzas[3] = new Pizza ( "FRO", "La 4 fromages", 12.00 );
		pizzas[4] = new Pizza ( "CAN", "La cannibale", 12.50 );
		pizzas[5] = new Pizza ( "SAV", "La savoyarde", 13.00 );
		pizzas[6] = new Pizza ( "ORI", "L'orientale", 13.50 );
		pizzas[7] = new Pizza ( "IND", "L'indienne", 14.00 );

		fr.pizzeria.ihm.menu.Menu menu = new fr.pizzeria.ihm.menu.Menu(sc);
		menu.afficher();
		sc.close();
	}
}
