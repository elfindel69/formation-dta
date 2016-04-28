package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Classe Abstraite - options des menus
 * @author Valentin
 *
 */
public abstract class AbstractOptionMenu {
	protected final String libelle;
	protected Scanner sc;
	protected Pizza[] tabPizza;
	protected IPizzaDao pizzaDao;
	
	/**
	 * Constructeur par défault
	 * @param libelle - libellé du menu
	 */
	public AbstractOptionMenu(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Constructeur par défault
	 * @param libelle - libellé du menu
	 * @param pizzaDao - lien vers la DAO
	 */
	public AbstractOptionMenu(String libelle,IPizzaDao pizzaDao) {
		this.libelle = libelle;
		this.pizzaDao = pizzaDao;
	}
	
	/**
	 * Constructeur complet
	 * @param libelle - libellé du menu
	 * @param scanner - scanner du menu
	 * @param pizzaDao - lien vers la DAO
	 */
	public AbstractOptionMenu(String libelle, Scanner scanner, IPizzaDao pizzaDao) {
		this.libelle = libelle;
		this.pizzaDao = pizzaDao;
		this.sc = scanner;
	}

	/**
	 * execution du menu
	 * @return flag d'execution
	 */
	public abstract boolean execute();
	

	/**
	 *  Getter libellé
	 * @return String libellé
	 */
	public String getLibelle(){
		return this.libelle;
		
	}
	
}
