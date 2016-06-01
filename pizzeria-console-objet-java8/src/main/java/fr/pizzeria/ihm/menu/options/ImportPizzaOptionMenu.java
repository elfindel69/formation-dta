package fr.pizzeria.ihm.menu.options;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DataAccessException;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoFilesImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Pizza;

public class ImportPizzaOptionMenu extends AbstractOptionMenu {
	private static final Logger LOG = Logger.getLogger(ImportPizzaOptionMenu.class.toString());

	public ImportPizzaOptionMenu(IDaoFactory pizzaDao) {
		super("Importer les pizzas", pizzaDao);
	}

	@Override
	public boolean execute() {
		IPizzaDao pizzaDaoImpl = pizzaDao.createPizzaDao();
		PizzaDaoFilesImpl files = new PizzaDaoFilesImpl();
		List<Pizza> pizzas = new ArrayList<>();
		try {
			pizzas = files.findAllPizzas();
			pizzaDaoImpl.importPizzas(pizzas, 3);
			LOG.log(Level.INFO, "importation réussie ^^");
		} catch (DaoException|DataAccessException e) {
			LOG.log(Level.SEVERE, "erreur d'importation ", e);
		}
		
		return true;
	}

}
