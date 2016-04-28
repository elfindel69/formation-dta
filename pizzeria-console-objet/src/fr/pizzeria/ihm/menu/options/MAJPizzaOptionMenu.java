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

	
	private static final String MAJ_PIZZA_MSG_ERREUR = "Erreur, modification impossible";
	private static final String MAJ_PIZZA_MSG_OK = "Pizza modifiée ^^";
	private static final String MAJ_PIZZA_MSG_SAISIE_CODE = "Veuillez choisir la pizza à modifier (code)";
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
		affichageListe();
		System.out.println(MAJ_PIZZA_MSG_SAISIE_CODE);
		System.out.println(MENU_MSG_CODE_ABANDON);
		String code = sc.next();
		boolean updatePizza = false;
		if (code != MENU_CODE_ABANDON) {
			Pizza newPizza = saisiePizza(sc);
			updatePizza = pizzaDao.updatePizza(code, newPizza);
			if(updatePizza){
				System.out.println(MAJ_PIZZA_MSG_OK);
			}
			else{
				System.out.println(MAJ_PIZZA_MSG_ERREUR);
			}
		}
		else{
			System.out.println(MENU_MSG_ERREUR_CODE);
		}
		
		return updatePizza;
	}

}
