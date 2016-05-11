package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

public class MaxPizzaOptionMenu extends AbstractOptionMenu {

	private static final String MAX_PIZZA_LIBELLE_MENU = "Afficher la pizza la plus chere";

	public MaxPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(MAX_PIZZA_LIBELLE_MENU, pizzaDao);
		
	}

	@Override
	public boolean execute() {
		Optional<Pizza> collect = null;
		try {
			collect = pizzaDao.findAllPizzas().stream().collect(Collectors.maxBy(Comparator.comparing(Pizza::getPrix)));
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
