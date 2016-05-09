package fr.pizzeria.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Classe Pizza - gère une pizza
 * 
 * @author Valentin
 *
 */
public class Pizza {

	private int id;
	@ToString
	private String code;
	@ToString
	private String nom;
	@ToString
	private double prix;
	@ToString(toUpperCase = true)
	private CategoriePizza cat;
	public static int nbPizzas;

	/**
	 * Constructeur crée une Pizza
	 * 
	 * @param code
	 *            code ('AAA') de la pizza
	 * @param nom
	 *            nom de la pizza
	 * @param prix
	 *            prix de la pizza
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza cat) {

		this.id = Pizza.nbPizzas;
		++Pizza.nbPizzas;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.cat = cat;
	}

	/**
	 * affichage
	 */
	@Override
	public String toString() {

		return Arrays.asList(this.getClass().getDeclaredFields()).stream()
				.filter(field -> field.getAnnotation(ToString.class) != null).map(field -> {
					String sb = null;
					try {
						sb = field.getAnnotation(ToString.class).toUpperCase() ? field.get(this).toString().toUpperCase() : field.get(this).toString();
					} catch (IllegalArgumentException | IllegalAccessException e) {
						sb = "";
						e.printStackTrace();
					}

					if (field.getName().equals("code")) {
						sb = String.format("%s ->", sb);
					} else if (field.getName().equals("prix")) {
						sb = String.format("(%s€)", sb);
					}
					return sb;
				}).collect(Collectors.joining(" "));

	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cat == null) ? 0 : cat.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (cat != other.cat)
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}

	/**
	 * Constructeur par d�faut
	 */
	public Pizza() {
	}

	/**
	 * Getter ID
	 * 
	 * @return int ID de le pizza
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter ID
	 * 
	 * @param id
	 *            ID à modifier
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter code
	 * 
	 * @return String code de le pizza
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter code
	 * 
	 * @param code
	 *            code à modifier
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter nom
	 * 
	 * @return String nom de le pizza
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter nom
	 * 
	 * @param nom
	 *            nom à modifier
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter prix
	 * 
	 * @return double prix de le pizza
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Setter prix
	 * 
	 * @param prix
	 *            prix à modifier
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Getter catégorie
	 * 
	 * @return CatégoriePizza categorie de la pizza
	 */
	public CategoriePizza getCat() {
		return cat;
	}

	/**
	 * Setter catégorie
	 * 
	 * @param cat
	 *            catégorie à modifier
	 */
	public void setCat(CategoriePizza cat) {
		this.cat = cat;
	}

}
