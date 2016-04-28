package fr.pizzeria.ihm.menu.options;

/**
 * Affichage message de sortie
 * @author Valentin
 *
 */
public class QuitterOptionMenu extends AbstractOptionMenu {

	private static final String QUITTER_MSG = "Aurevoir :(";
	private static final String QUITTER_LIBELLE_MENU = "Quitter";

	/**
	 * Constructeur
	 */
	public QuitterOptionMenu() {
		super(QUITTER_LIBELLE_MENU);
	}

	
	/**
	 * execution du menu
	 * @return flag d'execution (false)
	 */
	@Override
	public boolean execute() {
		System.out.println(QUITTER_MSG);
		return false;
	}

}
