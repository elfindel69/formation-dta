package com.bankonet.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.bankonet.exceptions.CompteNonTrouveException;

/**
 * Modelise un client de bankonet.
 *
 * <p>Un client est caracterise par :
 * <ul>
 * <li> son identifiant unique
 * <li> son nom
 * <li> son prenom
 * <li> la liste de ses comptes
 * </ul>
 *
 * @author fguibert
 */
public class Client {
	private int identifiant;
	private String nom;
	private String prenom;
	
	private Collection<Compte> comptes;
	/**
	 * @param nom
	 * @param prenom
	 * @param identifiant
	 */
	public Client(int identifiant, String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
	}
	
	public Client(int identifiant, String nom, String prenom, Collection<Compte> comptes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.comptes = comptes;
	}
	
	public String toString() {
		 return " ID  : "+this.getIdentifiant() +" - "+
		    	" Nom : "+this.getNom()+" - "+
		    	" Prénom : "+this.getPrenom();
		    		
		
	}
	
	public void creerCompte(Compte compte){
		comptes.add(compte);
	}
	
	public void supprimerCompte(Compte compte){
		comptes.remove(compte);
	}
	
	public void supprimerCompte(String numero) throws CompteNonTrouveException{
		Compte compte = retournerCompte(numero);
		comptes.remove(compte);
	}
	
	public Compte retournerCompte(String numero) throws CompteNonTrouveException{
		Compte compte = null;
		boolean trouve = false;
		for (Compte c: comptes){
			if(c.getIdentifiant() == Integer.parseInt(numero)){
				compte = c;
				trouve = true;
				break;
			}
		}
		if(!trouve){
			throw new CompteNonTrouveException("Erreur ce compte n'existe pas");
		}
		return compte;
	}
	public float calculerAvoirGLobal()
	{
		
		
		List<Compte> tousLesComptes = new ArrayList(this.getComptes());
		float soldeTotal = 0;
		for(Compte myC : tousLesComptes) {
			soldeTotal += myC.getSolde();
		}
		return soldeTotal;
		
	}
	
	public List<Compte> getComptes() {
	    ArrayList<Compte> compteList = new ArrayList<>();
	    compteList.addAll(comptes);
	    return Collections.unmodifiableList(compteList);

	}

	public Compte getCompte(int compteId) {
	    List<Compte> compteList = this.getComptes();
	    Iterator<Compte> compteIte = compteList.iterator();
	    while (compteIte.hasNext()) {
            Compte compte = (Compte) compteIte.next();
            if (compteId == compte.getIdentifiant())
                return compte;
        }
	    return null; 
	}
	
	public int getIdentifiant() {
		return identifiant;
	}
	/**
	 * Retourne le nom du client.
	 *
	 * @return java.lang.String
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Retourne le prenom du client.
	 * 
	 * @return java.lang.String
	 */
	public String getPrenom() {
		return prenom;
	}

}
