package fr.pizzeria.factory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.IPizzaDao;

public interface IDaoFactory {
	IPizzaDao createPizzaDao();

	IClientDao createClientDao();

	ICommandeDao createCommandeDao();
}
