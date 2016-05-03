package com.bankonet.test;

import com.bankonet.exceptions.CompteException;
import com.bankonet.model.CompteCourant;

public class TestDebitCompte {

	public static void main(String[] args) {

		CompteCourant c1 = new CompteCourant(4, "compte courant 1", 0, 1000);
		try {
			if (c1.debitAutorise(500)) {

				c1.debiter(500);

			}
		} catch (CompteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
