package fr.pizzeria.factory;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.client.IClientDao;
import fr.pizzeria.dao.pizza.IPizzaDao;

public interface IDaoFactory {
	IPizzaDao createPizzaDao();

	IClientDao createClientDao();

	ICommandeDao createCommandeDao();
}
