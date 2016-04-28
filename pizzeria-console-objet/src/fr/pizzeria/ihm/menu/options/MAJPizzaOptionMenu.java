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

	private static final String MAJ_PIZZA_MSG_OK = "Pizza modifi�e ^^";
	private static final String MAJ_PIZZA_MSG_SAISIE_CODE = "Veuillez choisir la pizza � modifier (code)";
	private static final String MAJ_PIZZA_LIBELLE_MENU = "Mettre � jour une pizza";
	private static final String MAJ_PIZZA_MSG = "Mise � jour d'une pizza";

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
			try{
				Pizza newPizza = saisiePizza(sc);
				pizzaDao.updatePizza(code, newPizza);
				System.out.println(MAJ_PIZZA_MSG_OK);
				updatePizza = true;
			}
			catch (UpdatePizzaException e){
				System.out.println(e.getMessage());
				updatePizza = false;
			}catch(InputMismatchException e){
				System.out.println("Erreur de saisie, veuillez entrer un nombre");
				sc.close();
				updatePizza = false;
			}
			
		}
		else{
			System.out.println(MENU_MSG_ERREUR_CODE);
		}
		
		return updatePizza;
	}

}
