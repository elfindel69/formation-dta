package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.stream.Collectors;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Pizza;

public class GrouperPizzaOptionMenu extends AbstractOptionMenu {

	private static final String GROUPER_LIBELLE_MENU = "Grouper par type";

	public GrouperPizzaOptionMenu(IDaoFactory pizzaDao) {
		super(GROUPER_LIBELLE_MENU, pizzaDao);
	}

	@Override
	public boolean execute() {
		IPizzaDao pizzaDaoImpl = pizzaDao.createPizzaDao();
		try {
			pizzaDaoImpl.findAllPizzas().stream().collect(Collectors.groupingBy(Pizza::getCat)).forEach((categ, liste) -> {
				System.out.println(categ);
				liste.stream().sorted(Comparator.comparing(Pizza::getCode)).forEach(System.out::println);
			});
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

}
