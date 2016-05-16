package fr.pizzeria.console;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJDBCImpl;
import fr.pizzeria.dao.PizzaDaoJPAImpl;
import fr.pizzeria.exceptions.DaoException;

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
		int daoImplConf = Integer.parseInt(confString);
		

		switch (daoImplConf) {
		case 0:
			
			System.err.println("Veuillez configurer l’application avec une implémentation base de données. ");
			
		
			break;
		case 1:
			
			System.err.println("Veuillez configurer l’application avec une implémentation base de données. ");
			
			break;
		case 2:
			System.out.println("DAO JDBC");
			ResourceBundle jdbcBundle = ResourceBundle.getBundle("jdbc");
			String driver = jdbcBundle.getString("jdbc.driver");
			String url = jdbcBundle.getString("jdbc.url");
			String user = jdbcBundle.getString("jdbc.user");
			String password = jdbcBundle.getString("jdbc.password");
			try {
				lancerApplication(new PizzaDaoJDBCImpl(driver,url,user,password),true);
			} catch (DaoException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("DAO JPA");
			try {
				java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
				EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console-objet-java8");
				lancerApplication(new PizzaDaoJPAImpl(em),true);
				em.close();
	
			} catch (DaoException e) {
				e.printStackTrace();
			}
			break;
		default:
			System.err.println("Aucune configuration DAO trouvée!");
			break;
		}

	}

	public static void lancerApplication(IPizzaDao impl, boolean menuJdbc) throws DaoException{
		// scanner
		try (Scanner sc = new Scanner(System.in)) {// liste des pizzas
			fr.pizzeria.ihm.menu.Menu menu = new fr.pizzeria.ihm.menu.Menu(sc, impl,menuJdbc);
			menu.afficher();
		}
		

	}
}
