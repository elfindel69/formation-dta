package fr.pizzeria.ihm.menu.options;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.Pizza;
/**
 * Menu de MAJ d'une pizza
 * @author Valentin
 *
 */
public class MAJPizzaOptionMenu extends AbstractOptionMenu {

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
	 * @return true
	 */
	@Override
	public boolean execute() {
		System.out.println(MAJ_PIZZA_MSG);
		affichageListe();
		System.out.println(MAJ_PIZZA_MSG_SAISIE_CODE);
		System.out.println(MENU_MSG_CODE_ABANDON);
		String code = sc.next();
		
		if (!code.equals(MENU_CODE_ABANDON)) {
			try{
				Pizza newPizza = saisiePizza(sc);
				pizzaDao.updatePizza(code, newPizza);
				System.out.println(MAJ_PIZZA_MSG_OK);
				
			}
			catch (UpdatePizzaException e){
				System.out.println(e.getMessage());
				
			}catch(InputMismatchException e){
				System.out.println("Erreur de saisie, veuillez entrer un nombre");
			
			}
			
		}
		return true;
	}

}
