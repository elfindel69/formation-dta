package com.bankonet.model;

import com.bankonet.exceptions.CompteException;
import com.bankonet.exceptions.DebitException;

/**
 * @author fguibert
 */
public abstract class Compte implements CompteStat {
	private String libelle;
	private int identifiant;
	protected float solde;

	Compte() { }
	Compte(int id, String libelle, float solde) {
		this.identifiant = id;
		this.libelle = libelle;
		this.solde = solde;
	}
	
	public String toString() {
	        return  " ID  : "+this.getIdentifiant() +" - "+
		    		" Lib : "+this.getLibelle()+" - "+
		    		" Solde : "+this.getSolde()+"€";
	}

	public void crediter(float montant) {
		this.setSolde( this.getSolde() + montant);
	}
	
	public void debiter(float montant) throws DebitException {
		this.setSolde( this.getSolde() - montant);
	}
	
	public void effectuerVirement(Compte compte,float montant) throws CompteException{
		this.debiter(montant);
		compte.crediter(montant);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public abstract boolean creditAutorise(float montant) throws CompteException;

	public abstract boolean debitAutorise(float montant) throws CompteException;

	
	
	
	
	public String getLibelle() {
		return libelle;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public float getSolde() {
		return solde;
	}

	private void setSolde(float newSolde) {
		this.solde = newSolde;
	}
}
