package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.stream.Collectors;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class GrouperPizzaOptionMenu extends AbstractOptionMenu {

	private static final String GROUPER_LIBELLE_MENU = "Grouper par type";

	public GrouperPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(GROUPER_LIBELLE_MENU, pizzaDao);
	}

	@Override
	public boolean execute() {
		pizzaDao.findAllPizzas().stream().collect(Collectors.groupingBy(Pizza::getCat)).forEach((categ, liste) -> {
			System.out.println(categ);
			liste.stream().sorted(Comparator.comparing(Pizza::getCode)).forEach(System.out::println);
		});

		return true;
	}

}