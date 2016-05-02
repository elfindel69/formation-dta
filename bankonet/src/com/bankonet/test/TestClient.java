package com.bankonet.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bankonet.exceptions.CompteException;
import com.bankonet.exceptions.CompteNonTrouveException;
import com.bankonet.model.Client;
import com.bankonet.model.Compte;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;


public class TestClient {

	 public static void main(String[] args) {
		 
		
		// Utilisation d'une collection : création de la liste des comptes courants/epargne
		List<Compte> listCompteCourant1 = new ArrayList<>();
	
		List<Compte> listCompteCourant2 = new ArrayList<>();
		
		List<Compte> listCompteCourant3 = new ArrayList<>();
	
		listCompteCourant1.add(new CompteCourant(1, "compte courant 1", 0, 1000));
		listCompteCourant1.add(new CompteEpargne(2, "compte epargne 1", 10, 2.54F, 20000));
		
		listCompteCourant2.add(new CompteCourant(3, "compte courant 2", 6000, 200));
		listCompteCourant2.add(new CompteEpargne(4, "compte epargne 2", 10500, 1.67F, 30000));
		
		listCompteCourant3.add(new CompteCourant(5, "compte courant 3", -200, 300));
	
		// creation des clients
		List<Client> listClient =  new ArrayList<>();
		listClient.add(new Client(1,"GUIBERT", "Fabien", listCompteCourant1));
		listClient.add(new Client(2,"TOTO", "Titi", listCompteCourant2));
		listClient.add(new Client(3,"DURAND", "Jacques", listCompteCourant3));
		
		 for(Client myClient : listClient) {
			 	System.out.println();
			    System.out.println(myClient.toString());
			    System.out.println("Avoir global : "+myClient.calculerAvoirGLobal()+" €");
			    for(Object myCompte : myClient.getComptes()) {
			    	System.out.println(myCompte.toString());
			    }
			    
		 }
		 Client cl1 = listClient.get(0);
		 Client cl2 = listClient.get(1);
		 try {
			((Compte) cl1.getComptes().get(0)).effectuerVirement( ((Compte) cl2.getComptes().get(0)),1000);
		} catch (CompteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for(Client myClient : listClient) {
			 	System.out.println();
			    System.out.println(myClient.toString());
			    System.out.println("Avoir global : "+myClient.calculerAvoirGLobal()+" €");
			    for(Object myCompte : myClient.getComptes()) {
			    	System.out.println(myCompte.toString());
			    }
			    
		 }

		 Compte compte = new CompteCourant(6,"compte courant 4",200,1000);
		 cl1.creerCompte(compte);
		 for(Client myClient : listClient) {
			 	System.out.println();
			    System.out.println(myClient.toString());
			    System.out.println("Avoir global : "+myClient.calculerAvoirGLobal()+" €");
			    for(Object myCompte : myClient.getComptes()) {
			    	System.out.println(myCompte.toString());
			    }
			    
		 }

		 try {
			Compte cpt= cl1.retournerCompte("1");
			System.out.println(cpt);
		} catch (CompteNonTrouveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 try {
				Compte cpt= cl1.retournerCompte("10");
				System.out.println(cpt);
			} catch (CompteNonTrouveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 try {
				cl1.supprimerCompte("6");
				
			} catch (CompteNonTrouveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 try {
					cl1.supprimerCompte("15");

				} catch (CompteNonTrouveException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 for(Client myClient : listClient) {
				 	System.out.println();
				    System.out.println(myClient.toString());
				    System.out.println("Avoir global : "+myClient.calculerAvoirGLobal()+" €");
				    for(Object myCompte : myClient.getComptes()) {
				    	System.out.println(myCompte.toString());
				    }
				    
			 }

			
	 }
	 
	 
	 
}
