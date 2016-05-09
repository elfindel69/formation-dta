package fr.pizzeria.ihm.menu.options;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Classe Abstraite - options des menus
 * 
 * @author Valentin
 *
 */
public abstract class AbstractOptionMenu {
	private static final String MENU_MSG_SAISIE_PRIX = "Veuillez saisir le prix...";
	private static final String MENU_MSG_SAISIE_NOM = "Veuillez saisir le nom (sans espace)...";
	private static final String MENU_MSG_SAISIE_CODE = "Veuillez saisir le code...";
	protected static final String MENU_CODE_ABANDON = "99";
	protected static final String MENU_MSG_CODE_ABANDON = "99 pour abandonner";
	private static final String MENU_MSG_SAISIE_CATEGORIE = "Veuillez choisir la catégorie...";
	protected final String libelle;
	protected Scanner sc;
	protected Pizza[] tabPizza;
	protected IPizzaDao pizzaDao;

	/**
	 * Constructeur par défault
	 * 
	 * @param libelle
	 *            - libellé du menu
	 */
	public AbstractOptionMenu(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Constructeur par défault
	 * 
	 * @param libelle
	 *            - libellé du menu
	 * @param pizzaDao
	 *            - lien vers la DAO
	 */
	public AbstractOptionMenu(String libelle, IPizzaDao pizzaDao) {
		this.libelle = libelle;
		this.pizzaDao = pizzaDao;
	}

	/**
	 * Constructeur complet
	 * 
	 * @param libelle
	 *            - libellé du menu
	 * @param scanner
	 *            - scanner du menu
	 * @param pizzaDao
	 *            - lien vers la DAO
	 */
	public AbstractOptionMenu(String libelle, Scanner scanner, IPizzaDao pizzaDao) {
		this.libelle = libelle;
		this.pizzaDao = pizzaDao;
		this.sc = scanner;
	}

	/**
	 * execution du menu
	 * 
	 * @return flag d'execution
	 */
	public abstract boolean execute();

	/**
	 * Saisie d'une nouvelle pizza
	 * 
	 * @param sc
	 *            - Scanner de saisie
	 * @return Pizza - pizza créée
	 */
	Pizza saisiePizza(Scanner sc) {
		Pizza newPizza = new Pizza();
		System.out.println(MENU_MSG_SAISIE_CODE);
		newPizza.setCode(sc.next());
		System.out.println(MENU_MSG_SAISIE_NOM);
		newPizza.setNom(sc.next());
		System.out.println(MENU_MSG_SAISIE_PRIX);
		newPizza.setPrix(sc.nextDouble());
		CategoriePizza[] cat = CategoriePizza.values();
		System.out.println(MENU_MSG_SAISIE_CATEGORIE);
		Arrays.stream(cat).forEach(el -> System.out.println(el.ordinal() + " -> " + el.getLibelle()));
		int index = sc.nextInt();
		newPizza.setCat(cat[index]);
		return newPizza;
	}

	/**
	 * Affichage de la liste
	 */
	void affichageListe() {
		List<Pizza> pizzas = null;
		try {
			pizzas = pizzaDao.findAllPizzas();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pizzas.stream().sorted(Comparator.comparing(Pizza::getCode)).forEach(System.out::println);
	}

	/**
	 * Getter libellé
	 * 
	 * @return String libellé
	 */
	public String getLibelle() {
		return this.libelle;

	}

}
