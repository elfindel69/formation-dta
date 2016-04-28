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
	 * Constructeur par d�fault
	 * @param libelle - libell� du menu
	 */
	public AbstractOptionMenu(String libelle) {
		this.libelle = libelle;
	}
	
	/**
	 * Constructeur par d�fault
	 * @param libelle - libell� du menu
	 * @param pizzaDao - lien vers la DAO
	 */
	public AbstractOptionMenu(String libelle,IPizzaDao pizzaDao) {
		this.libelle = libelle;
		this.pizzaDao = pizzaDao;
	}
	
	/**
	 * Constructeur complet
	 * @param libelle - libell� du menu
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
	 *  Getter libell�
	 * @return String libell�
	 */
	public String getLibelle(){
		return this.libelle;
		
	}
	
}
