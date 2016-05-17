package fr.pizzeria.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commande")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "numero_commande", nullable = false, unique = true)
	private String noCommande;

	@Enumerated
	private StatutCommande statut;
	
	@Column(name = "date_commande")
	private Date dateCommande;
	
	@ManyToOne
	@JoinColumn(name="livreur_id")
	private Livreur livreur;
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="commande_pizza",
	joinColumns=
		@JoinColumn(name="commande_id", referencedColumnName="id"),
	inverseJoinColumns=
		@JoinColumn(name="pizza_id", referencedColumnName="id")
	)
	private Set<Pizza> pizzas = new HashSet<>();
	public Commande() {
		super();
	}

	@Override
	public String toString() {
		return "Commande [noCommande=" + noCommande + ", statut=" + statut + ", dateCommande=" + dateCommande
				+ ", livreur=" + livreur + ", client=" + client + ", pizzas=" + pizzas + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoCommande() {
		return noCommande;
	}

	public void setNoCommande(String noCommande) {
		this.noCommande = noCommande;
	}

	public StatutCommande getStatut() {
		return statut;
	}

	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	
	
}
