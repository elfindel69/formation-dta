package fr.pizzeria.console;

import java.util.Scanner;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.pizzeria.factory.DaoFactoryJPAImpl;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.ihm.menu.Menu;

public class PizzeriaClientConsoleApp {
	private static IDaoFactory daoFact;

	private PizzeriaClientConsoleApp() {

	}

	public static void main(String[] args) {
		System.out.println("DAO JPA");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria-console-client");
		daoFact = DaoFactoryJPAImpl.getImpl(entityManagerFactory);
		lancerApplication();
		entityManagerFactory.close();

	}

	private static void lancerApplication() {
		// scanner
		try (Scanner sc = new Scanner(System.in)) {// liste des pizzas
			Menu menu = new Menu(sc, daoFact);
			menu.afficher();
		}

	}

}