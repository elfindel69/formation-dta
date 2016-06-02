package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "livreur")
public class Livreur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="prenom",nullable = false)
	private String prenom;
	
	@Column(name="nom",nullable = false)
	private String nom;


	public Livreur() {
		super();
	}
	
	

	public Livreur(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}



	@Override
	public String toString() {
		return "Livreur [prenom=" + prenom + ", nom=" + nom + "]";
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
