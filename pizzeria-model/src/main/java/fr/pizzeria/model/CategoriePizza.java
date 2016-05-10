package fr.pizzeria.model;

/**
 * Catégorie de pizza
 * 
 * @author Valentin
 *
 */
public enum CategoriePizza {

	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans viande");
	private String libelle;

	/**
	 * Getter libelle
	 * 
	 * @return String - libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Constructeur
	 * @param libelle - libelle
	 */
	private CategoriePizza(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * affichage
	 */
	@Override
	public String toString() {
		return libelle;

	}

}
