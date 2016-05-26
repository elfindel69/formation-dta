package fr.pizzeria.admin.metier;

import javax.enterprise.inject.Produces;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;

public class DaoProducer {

	@Produces
	public IPizzaDao getPizzaDao() {
		return new PizzaDaoImpl();
	}

}
