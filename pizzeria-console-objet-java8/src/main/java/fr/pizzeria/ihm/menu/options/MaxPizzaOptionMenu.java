package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Pizza;

public class MaxPizzaOptionMenu extends AbstractOptionMenu {

	private static final String MAX_PIZZA_LIBELLE_MENU = "Afficher la pizza la plus chere";

	public MaxPizzaOptionMenu(IDaoFactory pizzaDao) {
		super(MAX_PIZZA_LIBELLE_MENU, pizzaDao);
		
	}

	@Override
	public boolean execute() {
		IPizzaDao pizzaDaoImpl = pizzaDao.createPizzaDao();
		Optional<Pizza> collect = null;
		try {
			collect = pizzaDaoImpl.findAllPizzas().stream().collect(Collectors.maxBy(Comparator.comparing(Pizza::getPrix)));
		} catch (DaoException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(collect!= null && collect.isPresent()){
			System.out.println(collect.get());
			
		}
		return true;
	}

}
