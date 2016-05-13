package fr.pizzeria.ihm.menu.options;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoBDDImpl;
import fr.pizzeria.exceptions.DaoException;

public class ImportPizzaOptionMenu extends AbstractOptionMenu {
	public ImportPizzaOptionMenu(IPizzaDao pizzaDao) {
		super("Importer les pizzas");
		this.pizzaDao = (PizzaDaoBDDImpl) pizzaDao;
	}

	@Override
	public boolean execute() {

		try {
			pizzaDao.importPizzas();
		} catch (DaoException e) {
			System.err.println("erreur d'importation dans la base");
			e.printStackTrace();
		}
		System.out.println("importation réussie ^^");
		return true;
	}

}
