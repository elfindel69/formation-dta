package fr.pizzeria.console;

import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.dao.pizza.PizzaDaoJPAImpl;
import fr.pizzeria.factory.DaoFactoryGenericImpl;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.Menu;

public class PizzeriaClientConsoleApp {
	private static IDaoFactory daoFact;

	private PizzeriaClientConsoleApp() {

	}

	public static void main(String[] args) {
		System.out.println("DAO JPA");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console-client");
		PizzaDaoJPAImpl pizza = new PizzaDaoJPAImpl(em);
		daoFact = new DaoFactoryGenericImpl(pizza,null,null);
		lancerApplication();
		em.close();

	}

	private static void lancerApplication() {
		// scanner
		try (Scanner sc = new Scanner(System.in)) {// liste des pizzas
			Menu menu = new Menu(sc, daoFact);
			menu.afficher();
		}

	}

}
