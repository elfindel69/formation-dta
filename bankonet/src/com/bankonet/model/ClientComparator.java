package com.bankonet.model;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {

	public ClientComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Client o1, Client o2) {
		int compare= o1.getPrenom().compareTo(o2.getPrenom());
		if(compare == 0){
			 compare = o1.getNom().compareTo(o2.getNom());
		}
		return compare;
	}

}
