package fr.pizzeria.ihm.menu.options;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Menu de création d'une Pizza
 * @author Valentin
 *
 */
public class NouvellePizzaOptionMenu extends AbstractOptionMenu {

	private static final String AJOUTER_PIZZA_MSG_OK = "Pizza ajoutée ^^";
	private static final String AJOUTER_MSG = "Ajout d'une nouvelle pizza";
	private static final String AJOUTER_LIBELLE_MENU = "Ajouter une nouvelle pizza";

	/**
	 * Constructeur
	 * @param sc - scanner
	 * @param dao - lien vers la DAO
	 */
	public NouvellePizzaOptionMenu(Scanner sc,IPizzaDao dao) {
		super(AJOUTER_LIBELLE_MENU,sc,dao);
	}

	
	/**
	 * execution du menu
	 * @return true
	 */
	@Override
	public boolean execute() {
		
		System.out.println(AJOUTER_MSG);
		try{
			Pizza newPizza = saisiePizza(sc);
			pizzaDao.savePizza(newPizza);
			System.out.println(AJOUTER_PIZZA_MSG_OK);
		
		}
		catch(SavePizzaException e){
			System.err.println(e.getMessage());
			
		}catch(InputMismatchException e){
			System.err.println("erreur "+sc.next()+" n'est pas un nombre");
		}
		
		return true;
	}

}



