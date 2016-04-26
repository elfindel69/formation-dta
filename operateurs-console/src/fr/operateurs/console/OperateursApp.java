package fr.operateurs.console;

import java.util.Scanner;

public class OperateursApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("******** Application Operateurs ********");
		System.out.println("Veuillez saisir le premier nombre");
		double nb1 = sc.nextDouble();
		System.out.println("Veuillez saisir le second nombre");
		double nb2 = sc.nextDouble();
		System.out.println(nb1+" + "+nb2+" = "+(nb1+nb2));
		System.out.println(nb1+" - "+nb2+" = "+(nb1-nb2));
		System.out.println(nb1+" * "+nb2+" = "+(nb1*nb2));
		System.out.println(nb1+" / "+nb2+" = "+(nb1/nb2));
		System.out.println(nb1+" % "+nb2+" = "+(nb1%nb2));
		sc.close();
	}

}
