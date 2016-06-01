package fr.pizzeria.console;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
		Logger.getLogger("org").setLevel(Level.SEVERE);

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
			Menu menu = context.getBean(Menu.class);
			menu.afficher();
		}
	}

}
