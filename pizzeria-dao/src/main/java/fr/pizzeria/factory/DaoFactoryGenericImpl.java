package fr.pizzeria.factory;

import org.springframework.beans.factory.annotation.Autowired;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.pizza.IPizzaDao;

public class DaoFactoryGenericImpl implements IDaoFactory {

	private IPizzaDao pizza;
	private ICommandeDao commande;
	private IClientDao client;

	@Autowired
	public DaoFactoryGenericImpl(IPizzaDao pizza,ICommandeDao commande,IClientDao client) {
		super();
		this.pizza = pizza;
		this.commande = commande;
		this.client = client;
	}

	@Override
	public IPizzaDao createPizzaDao() {
		return pizza;
	}

	@Override
	public IClientDao createClientDao() {
		return client;
	}

	@Override
	public ICommandeDao createCommandeDao() {
		return commande;
	}

}
