package fr.pizzeria.ihm.menu;

import java.util.Scanner;

import fr.pizzeria.ihm.menu.options.AbstractOptionMenu;
import fr.pizzeria.ihm.menu.options.ListerPizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.options.QuitterOptionMenu;

public class Menu {
	private static final String MENU_LIBELLE = "Pizzeria Administration";
	private AbstractOptionMenu[] listeMenus;
	private Scanner sc;
	
	public Menu(Scanner sc) {
		super();
		initialiserOptions(sc);
		this.sc = sc;
	}
	
	private void initialiserOptions(Scanner scanner) {
		listeMenus = new AbstractOptionMenu[]{
			new ListerPizzaOptionMenu(scanner),
			new NouvellePizzaOptionMenu(scanner),
			new QuitterOptionMenu(scanner)
		};
		
	}

	public void afficher(){
		System.out.println("*****"+MENU_LIBELLE+"*****");
		
		boolean continuer=true;
		int saisie;
		while(continuer){
			for(int i=0;i<listeMenus.length;i++){
				System.out.println(i+". "+listeMenus[i].getLibelle());
			}
			saisie = sc.nextInt();
			continuer=listeMenus[saisie].execute();
		} 
	}
}
