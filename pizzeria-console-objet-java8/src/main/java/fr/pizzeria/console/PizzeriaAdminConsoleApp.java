package fr.pizzeria.console;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.pizzeria.ihm.menu.Menu;

/**
 * Classe principale - administration de la pizzeria
 * 
 * @author Valentin
 *
 */
public class PizzeriaAdminConsoleApp {

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
		lancerApplication(confString);
	}

	private static void launchJPA() {
		/*
		 * System.out.println("DAO JPA");
		 * java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.
		 * WARNING); EntityManagerFactory em =
		 * Persistence.createEntityManagerFactory("pizzeria-console"); daoFact =
		 * DaoFactoryJPAImpl.getImpl(em); lancerApplication(); em.close();
		 */
	}

	

	public static void lancerApplication(String confString) {
		// scanner
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml",
				confString)) {// liste des pizzas
			fr.pizzeria.ihm.menu.Menu menu = context.getBean(Menu.class);
			menu.afficher();
		}

	}
}
