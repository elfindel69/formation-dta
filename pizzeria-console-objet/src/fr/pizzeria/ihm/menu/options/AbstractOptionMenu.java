package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public abstract class AbstractOptionMenu {
	protected final String libelle;
	protected Scanner sc;
	protected Pizza[] tabPizza;
	
	public AbstractOptionMenu(String libelle,Scanner sc) {
		this.libelle = libelle;
		this.sc = sc;
	}
	
	public String getLibelle(){
		return this.libelle;
		
	}
	
	public abstract boolean execute();
	
}
