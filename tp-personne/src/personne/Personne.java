package personne;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 */
public class Personne extends Observable {

	/**
	 * Default constructor
	 */
	public Personne() {
	}

	/**
	 * 
	 */
	private String nom;

	/**
	 * 
	 */
	private String prenom;

	private EtatPersonne etat;

	private List<Observer> observers = new ArrayList<>();

	public EtatPersonne getEtat() {
		return etat;
	}

	public void setEtat(EtatPersonne etat) {
		this.etat = etat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void addObserver(Observer o){
		this.observers.add(o);
	}

	public void notifyObservers(Object arg){
		for(Observer obs : this.observers){
			obs.update(this, this);
		}
	}
}