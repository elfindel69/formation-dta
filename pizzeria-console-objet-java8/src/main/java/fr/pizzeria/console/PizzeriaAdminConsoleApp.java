package fr.pizzeria.console;

import java.io.IOException;
import java.util.ResourceBundle;
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
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String confString = bundle.getString("dao.impl");
		int daoImplConf = Integer.valueOf(confString);
		System.out.println(confString);

		switch (daoImplConf) {
		case 0:
			lancerApplication(new PizzaDaoImpl());
			break;
		case 1:
			lancerApplication(new PizzaDaoFilesImpl());
			break;
		default:
			System.err.println("Aucune configuration DAO trouvée!");
			break;
		}

	}

	public static void lancerApplication(IPizzaDao impl) {
		// scanner
		try (Scanner sc = new Scanner(System.in)) {// liste des pizzas
			fr.pizzeria.ihm.menu.Menu menu = new fr.pizzeria.ihm.menu.Menu(sc, impl);
			menu.afficher();
		}
		

	}
}
