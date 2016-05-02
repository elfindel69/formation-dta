package com.bankonet.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public class Client implements Comparable{
	private int identifiant;
	private String nom;
	private String prenom;
	
	private List<CompteEpargne> compteEpargneList;
	private List<CompteCourant> compteCourantList;
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
	
	public Client(int identifiant, String nom, String prenom, List<CompteCourant>cc, List<CompteEpargne>ce) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.compteEpargneList = ce;
		this.compteCourantList = cc;
	}
	
	public String toString() {
		 return " ID  : "+this.getIdentifiant() +" - "+
		    	" Nom : "+this.getNom()+" - "+
		    	" Prénom : "+this.getPrenom()+ " - "+
		    	"comptes courants: "+this.getNombreCompteCourant()+" - "+
		    	"comptes epargne: "+this.getNombreCompteEpargne();
		    		
		
	}
	
	public void creerCompte(Compte compte){
		if(compte instanceof CompteEpargne){
			compteEpargneList.add((CompteEpargne) compte);
		}
		if(compte instanceof CompteCourant){
			compteCourantList.add((CompteCourant) compte);
		}
		
	}
	
	public void supprimerCompte(Compte compte){
		if(compte instanceof CompteEpargne){
			compteEpargneList.remove((CompteEpargne) compte);
		}
		if(compte instanceof CompteCourant){
			compteCourantList.remove((CompteCourant) compte);
		}
		
	}
	
	public void supprimerCompte(String numero) throws CompteNonTrouveException{
		Compte compte = retournerCompte(numero);
		if(compte instanceof CompteEpargne){
			compteEpargneList.remove((CompteEpargne) compte);
		}
		if(compte instanceof CompteCourant){
			compteCourantList.remove((CompteCourant) compte);
		}
	}
	
	public Compte retournerCompte(String numero) throws CompteNonTrouveException{
		Compte compte = null;
		boolean trouve = false;
		for (Compte c: getComptes()){
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
		List<Compte> tousLesComptes = new ArrayList<>(this.getComptes());
		float soldeTotal = 0;
		for(Compte myC : tousLesComptes) {
			soldeTotal += myC.getSolde();
		}
		return soldeTotal;
		
	}
	
	public List<Compte> getComptes() {
	    ArrayList<Compte> compteList = new ArrayList<>();
	    compteList.addAll(compteCourantList);
	    compteList.addAll(compteEpargneList);
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
	
	
	
	public int getNombreCompteCourant(){
		return compteCourantList.size();
	}
	
	public int getNombreCompteEpargne(){
		return compteEpargneList.size();
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

	@Override
	public int compareTo(Object o) {
		int compare;
		if(!(o instanceof Client)){
			throw new ClassCastException();
		}
		Client cl = (Client) o;
		if (this.identifiant > cl.identifiant){
			compare = 1;
		}else if(this.identifiant < cl.identifiant){
			compare= -1;
		}else{
			compare= 0;
		}
		
		return compare;
	}

}
