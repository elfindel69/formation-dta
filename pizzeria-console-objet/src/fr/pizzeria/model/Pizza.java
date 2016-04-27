package fr.pizzeria.model;

/**
 * Classe Pizza - g�re une pizza
 * @author Valentin
 *
 */
public class Pizza {
	private int id;
	private String code;
	private String nom;
	private double prix;
	public static int nbPizzas;
	
	/**
	 *  Constructeur cr�e une Pizza
	 * @param code code ('AAA') de la pizza
	 * @param nom nom de la pizza
	 * @param prix prix de la pizza
	 */
	public Pizza(String code, String nom, double prix) {
		
		this.id = Pizza.nbPizzas;
		Pizza.nbPizzas++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}
	
	
	/**
	 * Constructeur par d�faut
	 */
	public Pizza() {
	}

	/**
	 * Getter ID
	 * @return int ID de le pizza
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter ID
	 * @param id ID � modifier
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter code
	 * @return String code de le pizza
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Setter code
	 * @param code code � modifier
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Getter nom
	 * @return String nom de le pizza
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Setter nom
	 * @param nom nom � modifier
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter prix
	 * @return double prix de le pizza
	 */
	public double getPrix() {
		return prix;
	}
	
	/**
	 * Setter prix
	 * @param prix prix � modifier
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	
	
}
