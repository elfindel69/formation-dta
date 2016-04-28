package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public abstract class AbstractOptionMenu {
	protected final String libelle;
	protected Scanner sc;
	protected Pizza[] tabPizza;
	protected IPizzaDao pizzaDao;
	
	public AbstractOptionMenu(String libelle) {
		this.libelle = libelle;
	}
	
	public AbstractOptionMenu(String libelle,IPizzaDao pizzaDao) {
		this.libelle = libelle;
		this.pizzaDao = pizzaDao;
	}
	
	public abstract boolean execute();
	

	public String getLibelle(){
		return this.libelle;
		
	}
	
}
