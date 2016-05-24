package fr.pizzeria.console;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.factory.DaoFactoryJPAImpl;
import fr.pizzeria.factory.IDaoFactory;

/**
 * Classe principale - administration de la pizzeria
 * 
 * @author Valentin
 *
 */
public class PizzeriaAdminConsoleApp {
	private static IDaoFactory daoFact;

	private PizzeriaAdminConsoleApp() {

	}

	/**
	 * main - Gestion des pizzas
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		ResourceBundle bundle = ResourceBundle.getBundle("application");
		String confString = bundle.getString("dao.impl");
		int daoImplConf = Integer.parseInt(confString);

		switch (daoImplConf) {
		case 0:
			System.err.println("Veuillez configurer l’application avec une implémentation base de données. ");
			break;
		case 1:
			System.err.println("Veuillez configurer l’application avec une implémentation base de données. ");
			break;
		case 2:
			launchJDBC();
			break;
		case 3:
			launchJPA();
			break;
		default:
			System.err.println("Aucune configuration DAO trouvée!");
			break;
		}

	}

	private static void launchJPA() {
		System.out.println("DAO JPA");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console");
		daoFact = DaoFactoryJPAImpl.getImpl(em);
		lancerApplication(true);
		em.close();
	}

	private static void launchJDBC() {
		// TODO implémentation JDBC ?
		/*
		 * System.out.println("DAO JDBC"); ResourceBundle jdbcBundle =
		 * ResourceBundle.getBundle("jdbc"); String driver =
		 * jdbcBundle.getString("jdbc.driver"); String url =
		 * jdbcBundle.getString("jdbc.url"); String user =
		 * jdbcBundle.getString("jdbc.user"); String password =
		 * jdbcBundle.getString("jdbc.password");
		 */
		// lancerApplication(new PizzaDaoJDBCImpl(driver, url, user, password),
		// true);
	}

	public static void lancerApplication(boolean menuJdbc) {
		// scanner
		try (Scanner sc = new Scanner(System.in)) {// liste des pizzas
			fr.pizzeria.ihm.menu.Menu menu = new fr.pizzeria.ihm.menu.Menu(sc, daoFact, menuJdbc);
			menu.afficher();
		}

	}
}
