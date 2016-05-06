package fr.pizzeria.ihm.menu.options;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class GrouperPizzaOptionMenu extends AbstractOptionMenu {

	private static final String GROUPER_LIBELLE_MENU = "Grouper par type";
	public GrouperPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(GROUPER_LIBELLE_MENU,pizzaDao);
	}

	@Override
	public boolean execute() {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		Map<CategoriePizza, List<Pizza>> map = pizzas.stream().collect(Collectors.groupingBy(Pizza::getCat));
		map.forEach((categ,liste) -> {
		System.out.println(categ);
			liste.stream().sorted(Comparator.comparing(Pizza::getCode)).forEach(System.out::println);
		});
		
		return true;
	}

}
