package com.bankonet.test;

import com.bankonet.model.CompteStat;

public class TestAutomate {

	public static void main(String[] args) {
		CompteStat[] tab= DonneesTest.construitEchantillonComptes();
	 float moy  = 0;
	 for(CompteStat cs : tab){
		 moy+=cs.getSolde();
	 }
	 moy/=tab.length;
	  System.out.println("moyenne des comptes = "+moy);
	}

}
