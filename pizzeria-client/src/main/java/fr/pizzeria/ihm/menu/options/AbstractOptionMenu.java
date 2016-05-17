package fr.pizzeria.ihm.menu.options;

import java.util.Scanner;

import fr.pizzeria.factory.IDaoFactory;

public abstract class AbstractOptionMenu {
	protected final String libelle;
	protected Scanner sc;
	protected IDaoFactory daoFact;
	protected static final String MENU_MSG_SAISIE_EMAIL = "email...";
	protected static final String MENU_MSG_SAISIE_PASSWORD = "mot de passe...";

	public AbstractOptionMenu(String libelle) {
		this.libelle = libelle;
	}

	public AbstractOptionMenu(String libelle, Scanner scanner, IDaoFactory daoFact2) {
		this.libelle = libelle;
		this.daoFact = daoFact2;
		this.sc = scanner;
	}

	public abstract boolean execute();

	public String getLibelle() {
		// String Auto-generated method stub
		return this.libelle;
	}

}
