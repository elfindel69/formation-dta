package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;

/**
 * Menu de suppression d'une pizza
 * 
 * @author Valentin
 *
 */
public class SupprimerPizzaOptionMenu extends AbstractOptionMenu {

	private static final String SUPPR_PIZZA_MSG_OK = "Pizza Supprimée ^^";
	private static final String SUPPRIMER_PIZZA_LIBELLE_MENU = "Supprimer une pizza";

	/**
	 * Constructeur
	 * 
	 * @param sc
	 *            - scanner
	 * @param dao
	 *            - lien vers la DAO
	 */
	public SupprimerPizzaOptionMenu(Scanner sc, IPizzaDao pizzaDao) {
		super(SUPPRIMER_PIZZA_LIBELLE_MENU, sc, pizzaDao);
	}

	/**
	 * execution du menu
	 * 
	 * @return flag d'execution - supression effectuee
	 */
	@Override
	public boolean execute() {
		
		System.out.println("Supprimer une pizza");
		affichageListe();
		System.out.println("Veuillez choisir la pizza à supprimer (code)");
		System.out.println(MENU_MSG_CODE_ABANDON);
		String codePizza = sc.next();
		if (!codePizza.equals(MENU_CODE_ABANDON)) {
			try {
				pizzaDao.deletePizza(codePizza);
				System.out.println(SUPPR_PIZZA_MSG_OK);
				
			} catch (DaoException e) {
				System.err.println(e.getMessage());
				
			}
		} 
		return true;

	}

}
