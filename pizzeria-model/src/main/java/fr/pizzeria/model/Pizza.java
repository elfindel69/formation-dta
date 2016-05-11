package fr.pizzeria.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	private static int nbPizzas;

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

		this.id = Pizza.getNbPizzas();
		Pizza.setNbPizzas(Pizza.getNbPizzas() + 1);
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
						System.out.println(e.getMessage());
						e.printStackTrace();
					}

					if ("code".equals(field.getName())) {
						sb = String.format("%s ->", sb);
					} else if ("prix".equals(field.getName())) {
						sb = String.format("(%s€)", sb);
					}
					return sb;
				}).collect(Collectors.joining(" "));

	}

	
	
	@Override
	public int hashCode() {
		  return new HashCodeBuilder(17, 37).
			       append(code).
			       append(nom).
			       append(prix).
			       append(cat).
			       toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj==this){
			return true;
		}
		if(obj.getClass()!=getClass()){
			return false;
		}
		 Pizza rhs = (Pizza) obj;
		   return new EqualsBuilder()
		                 .appendSuper(super.equals(obj))
		                 .append(code, rhs.code)
		                 .append(nom, rhs.nom)
		                 .append(prix, rhs.prix)
		                 .append(cat, rhs.cat)
		                 .isEquals();
	}

	/**
	 * Constructeur par défaut
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

	public static int getNbPizzas() {
		return nbPizzas;
	}

	public static void setNbPizzas(int nbPizzas) {
		Pizza.nbPizzas = nbPizzas;
	}

}
