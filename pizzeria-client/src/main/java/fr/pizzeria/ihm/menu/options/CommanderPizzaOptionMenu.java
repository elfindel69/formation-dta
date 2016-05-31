
package fr.pizzeria.ihm.menu.options;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;

public class CommanderPizzaOptionMenu extends AbstractOptionMenu {

	private static final String MENU_CODE_ABANDON = "99";

	private static final String COMMANDER_UNE_PIZZA = "Commander une pizza";
	
	protected static final String MENU_MSG_CODE_ABANDON = "99 pour abandonner";
	private Client client;
	private IPizzaDao createPizzaDao;
	public CommanderPizzaOptionMenu(Scanner scanner, Client newClient, IDaoFactory daoFact) {
		super(COMMANDER_UNE_PIZZA, scanner, daoFact);
		this.client = newClient;

	}

	@Override
	public boolean execute() {

		System.out.println(COMMANDER_UNE_PIZZA);
		createPizzaDao = daoFact.createPizzaDao();
		affichageListePizzas();
		String saisie;
		List<String> codes = new ArrayList<>();
		boolean continuer =true;
		do {
			System.out.println("Saisir un code");
			System.out.println(MENU_MSG_CODE_ABANDON);
			saisie = sc.next();
			if (!saisie.equals(MENU_CODE_ABANDON)) {
				codes.add(saisie);
			}else{
				continuer=false;
			}
		} while (continuer);
		Set<Pizza> pizzas = null;
		try {
			pizzas = createPizzaDao.findPizzasByCode(codes);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		Commande cmd = new Commande();
		cmd.setClient(client);
		cmd.setPizzas(pizzas);
		java.util.Date utilDate = new java.util.Date();
		cmd.setDateCommande(new Date(utilDate.getTime()));
		cmd.setStatut(StatutCommande.NON_TRAITE);
		cmd.setNoCommande("CMD" + new Timestamp(utilDate.getTime()));
		ICommandeDao cmdDao = daoFact.createCommandeDao();
		try {
			cmdDao.saveCommande(cmd);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return true;
	}



	/**
	 * Affichage de la liste
	 */
	void affichageListePizzas() {
		List<Pizza> pizzas = null;

		try {
			pizzas = createPizzaDao.findAllPizzas();
		} catch (DaoException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if (pizzas != null) {
			pizzas.stream().sorted(Comparator.comparing(Pizza::getCode)).forEach(System.out::println);
		}

	}

}
