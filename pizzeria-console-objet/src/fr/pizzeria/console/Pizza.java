package fr.pizzeria.console;

public class Pizza {
	public int id;
	public String code;
	public String nom;
	public double prix;
	public static int nbPizzas;
	
	public Pizza(String code,String nom,double prix){
		id = nbPizzas;
		nbPizzas++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}
}
