package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFilesImpl;
import fr.pizzeria.dao.PizzaDaoImpl;

/**
 * Classe principale - administration de la pizzeria
 * 
 * @author Valentin
 *
 */
public class PizzeriaAdminConsoleApp {

	/**
	 * main - Gestion des pizzas
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// scanner
		Scanner sc = new Scanner(System.in);
		// liste des pizzas

		IPizzaDao dao = new PizzaDaoFilesImpl();
		fr.pizzeria.ihm.menu.Menu menu = new fr.pizzeria.ihm.menu.Menu(sc, dao);
		menu.afficher();
		sc.close();
	}
}
