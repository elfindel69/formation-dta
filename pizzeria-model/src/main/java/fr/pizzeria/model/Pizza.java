package fr.pizzeria.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Classe Pizza - gère une pizza
 * 
 * @author Valentin
 *
 */
@Entity
@NamedQuery(name = "pizza.listPizzas", query = "Select p from Pizza p")
@Table(name = "pizza")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ToString
	@Column(name = "code", length = 3, nullable = false, unique = true)
	private String code;

	@ToString
	@Column(name = "nom", nullable = false)
	private String nom;

	@ToString
	private BigDecimal prix ;

	@ToString(toUpperCase = true)
	@Column(name = "categorie")
	@Enumerated(EnumType.STRING)
	private CategoriePizza cat;
	
	@Column(name = "url_image")
	private String urlImage;
	
	private static int nbPizzas;

	/**
	 * Constructeur par défaut
	 */
	public Pizza() {
		super();
	}

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
	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza cat) {
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
						sb = field.getAnnotation(ToString.class).toUpperCase()
								? field.get(this).toString().toUpperCase() : field.get(this).toString();
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
		return new HashCodeBuilder(17, 37).append(code).append(nom).append(prix).append(cat).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Pizza rhs = (Pizza) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj)).append(code, rhs.code).append(nom, rhs.nom)
				.append(prix, rhs.prix).append(cat, rhs.cat).isEquals();
	}

	/**
	 * Getter ID
	 * 
	 * @return int ID de le pizza
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter ID
	 * 
	 * @param id
	 *            ID à modifier
	 */
	public void setId(Integer id) {
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
	public BigDecimal getPrix() {
		return prix;
	}

	/**
	 * Setter prix
	 * 
	 * @param prix
	 *            prix à modifier
	 */
	public void setPrix(BigDecimal prix) {
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

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

}
