package fr.pizzeria.ihm.menu.options;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoFilesImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

public class ImportPizzaOptionMenu extends AbstractOptionMenu {
	public ImportPizzaOptionMenu(IPizzaDao pizzaDao) {
		super("Importer les pizzas", pizzaDao);
	}

	@Override
	public boolean execute() {
		PizzaDaoFilesImpl files = new PizzaDaoFilesImpl();
		List<Pizza> pizzas = new ArrayList<>();
		try {
			pizzas = files.findAllPizzas();
			pizzaDao.importPizzas(pizzas, 3);
		} catch (DaoException e) {
			System.err.println("erreur d'importation ");
			e.printStackTrace();
		}
		System.out.println("importation réussie ^^");
		return true;
	}

}
