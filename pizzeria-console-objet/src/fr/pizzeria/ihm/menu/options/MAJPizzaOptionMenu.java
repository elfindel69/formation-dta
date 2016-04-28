package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;
/**
 * Menu de MAJ d'une pizza
 * @author Valentin
 *
 */
public class MAJPizzaOptionMenu extends AbstractOptionMenu {

	private static final String MAJ_PIZZA_LIBELLE_MENU = "Mettre à jour une pizza";
	private static final String MAJ_PIZZA_MSG = "Mise à jour d'une pizza";

	/**
	 * Constructeur
	 * @param sc - scanner
	 * @param dao - lien vers la DAO
	 */
	public MAJPizzaOptionMenu(Scanner sc,IPizzaDao dao) {
		super(MAJ_PIZZA_LIBELLE_MENU,sc,dao);
		
	}

	/**
	 * execution du menu
	 * @return flag d'execution - MAJ effectuee
	 */
	@Override
	public boolean execute() {
		System.out.println(MAJ_PIZZA_MSG);
		Pizza[] pizzas = pizzaDao.findAllPizzas();
		for (Pizza p : pizzas) {
			if (p != null) {
				System.out.println(p);
			}
		}
		System.out.println("Veuillez choisir la pizza à modifier (code)");
		System.out.println("99 pour abandonner");
		String code = sc.next();
		boolean updatePizza = false;
		if (code != "99") {
			Pizza newPizza = new Pizza();
			System.out.println("Veuillez saisir le code...");
			newPizza.setCode(sc.next());
			System.out.println("Veuillez saisir le nom (sans espace)...");
			newPizza.setNom(sc.next());
			System.out.println("Veuillez saisir le prix...");
			newPizza.setPrix(sc.nextDouble());
			updatePizza = pizzaDao.updatePizza(code, newPizza);
			if(updatePizza){
				System.out.println("Pizza modifiée ^^");
			}
			else{
				System.out.println("Erreur, modification impossible");
			}
		}
		else{
			System.out.println("Erreur, ce code n'existe pas");
		}
		
		return updatePizza;
	}

}
