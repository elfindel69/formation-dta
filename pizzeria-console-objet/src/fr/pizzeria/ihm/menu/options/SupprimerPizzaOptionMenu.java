package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Menu de suppression d'une pizza
 * @author Valentin
 *
 */
public class SupprimerPizzaOptionMenu extends AbstractOptionMenu {
	
	private static final String SUPPRIMER_PIZZA_LIBELLE_MENU = "Supprimer une pizza";

	/**
	 * Constructeur
	 * @param sc - scanner
	 * @param dao - lien vers la DAO
	 */
	public SupprimerPizzaOptionMenu(Scanner sc, IPizzaDao pizzaDao) {
		super(SUPPRIMER_PIZZA_LIBELLE_MENU,sc,pizzaDao);
	}

	
	/**
	 * execution du menu
	 * @return flag d'execution - supression effectuee
	 */
	@Override
	public boolean execute() {
		boolean result = false;
		System.out.println("Supprimer une pizza");
		Pizza[] pizzas = pizzaDao.findAllPizzas();
		for (Pizza p : pizzas) {
			if (p != null) {
				System.out.println(p);
			}
		}
		System.out.println("Veuillez choisir la pizza à supprimer (code)");
		System.out.println("99 pour abandonner");
		String codePizza = sc.next();
		if (codePizza != "99") {
			result = pizzaDao.deletePizza(codePizza);
			if (result){
				System.out.println("Pizza Supprimée ^^");
			}else{
				System.out.println("erreur, suppression impossible");
			}
		}else{
			System.out.println("erreur, cette pizza n'existe pas");
		}
		return result;
		
	}

}
