package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {
	private static Scanner sc;
	private static Object[][] pizzas;

	public static void affichage(){
		for(int i=0;i<pizzas.length;i++){
			if(pizzas[i][0] != null){
				System.out.println(pizzas[i][0]+" -> "+pizzas[i][1]+"("+pizzas[i][2]+"€)");
			}
		}
	}
	
	private static int rechercheIndexByCode(String codeP) {
		int index=-1;
		for(int i=0;i<pizzas.length;i++){
			if(pizzas[i][0].equals(codeP)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static Object[] creerPizza(){
		System.out.println("Veuillez saisir le code...");
		String code = sc.next();
		 System.out.println("Veuillez saisir le nom (sans espace)...");
		 String nom = sc.next();
		 System.out.println("Veuillez saisir le prix...");
		 Double prix = sc.nextDouble();
		 return new Object[]{code,nom,prix};
	}
	
	public static void main(String[] args) {
		int menu1 = 0;
		sc = new Scanner(System.in);
		pizzas = new Object[100][3];
		pizzas[0] = new Object[]{"PEP","Pépéroni",12.50};
		pizzas[1] = new Object[]{"MAR","Margherita",14.00};
		pizzas[2] = new Object[]{"REI","Reine",11.50};
		pizzas[3] = new Object[]{"FRO","La 4 fromages",12.00};
		pizzas[4] = new Object[]{"CAN","La cannibale",12.50};
		pizzas[5] = new Object[]{"SAV","La savoyarde",13.00};
		pizzas[6] = new Object[]{"ORI","L'orientale",13.50};
		pizzas[7] = new Object[]{"IND","L'indienne",14.00};
		
		do{ 
			System.out.println("***** Pizzeria Administration *****");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("99. Sortir");
			 menu1 = sc.nextInt();
			switch(menu1){
				case 1 : //Liste
					System.out.println("Liste des pizzas");
					affichage();
					break; 
				case 2 :
					System.out.println("Ajout d'une nouvelle pizza");//ajout
					int pizzToAdd = -1;
					for(int i=0;i<pizzas.length;i++){
						if(pizzas[i][0] == null){
							pizzToAdd = i;
							break;
						}
					}
					 pizzas[pizzToAdd]=creerPizza();
					
					break;
				case 3 :
					System.out.println("Mise à jour d'une pizza");
					System.out.println("Liste des pizzas");
					affichage();
					System.out.println("Veuillez choisir la pizza à modifier (code)");
					System.out.println("99 pour abandonner");
					String codeP = sc.next();
					if (codeP != "99"){
						int index = -1;
						//recherche code pizza à MAJ
						index=rechercheIndexByCode(codeP);
						//MAJ
						 pizzas[index]=creerPizza();
					}
					break;
				case 4 :
					System.out.println("Supprimer une pizza");
					System.out.println("Liste des pizzas");
					affichage();
					System.out.println("Veuillez choisir la pizza à supprimer (code)");
					System.out.println("99 pour abandonner");
					String codeP2 = sc.next();
					if (codeP2 != "99"){
						int index = -1;
						//recherche code pizza à MAJ
						index=rechercheIndexByCode(codeP2);
						//MAJ
						pizzas[index]=new Object[3];
					}
					break;
				default:
					System.out.println("Aurevoir :(");
					break;
			}
	
		}while(menu1 != 99);
		sc.close();
	}

}
