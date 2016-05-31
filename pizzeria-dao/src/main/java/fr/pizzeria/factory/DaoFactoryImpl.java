package fr.pizzeria.factory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoImpl;

public class DaoFactoryImpl implements IDaoFactory {

	private PizzaDaoImpl pizza;

	public DaoFactoryImpl(PizzaDaoImpl pizza) {
		super();
		this.pizza = pizza;
	}

	@Override
	public IPizzaDao createPizzaDao() {
		return pizza;
	}

	@Override
	public IClientDao createClientDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICommandeDao createCommandeDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
