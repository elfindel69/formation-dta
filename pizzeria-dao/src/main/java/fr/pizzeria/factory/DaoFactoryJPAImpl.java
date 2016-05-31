package fr.pizzeria.factory;

import fr.pizzeria.dao.ClientDaoJPAImpl;
import fr.pizzeria.dao.CommandeDaoJPAImpl;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJPAImpl;

public class DaoFactoryJPAImpl implements IDaoFactory {

	private static DaoFactoryJPAImpl impl;
	private PizzaDaoJPAImpl pizza;
	private ClientDaoJPAImpl client;
	private CommandeDaoJPAImpl commande;

	private DaoFactoryJPAImpl(PizzaDaoJPAImpl pizza) {
		this.pizza = pizza;
	}
	
	private DaoFactoryJPAImpl(PizzaDaoJPAImpl pizza,ClientDaoJPAImpl client,CommandeDaoJPAImpl commande) {
		
		this.pizza = pizza;
		this.client = client;
		this.commande = commande;
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
	
	public static DaoFactoryJPAImpl getImpl(PizzaDaoJPAImpl pizza) {
		if(impl==null){
			impl= new DaoFactoryJPAImpl(pizza);
		}
		return impl;
	}

	public static DaoFactoryJPAImpl getImpl(PizzaDaoJPAImpl pizza,ClientDaoJPAImpl client,CommandeDaoJPAImpl commande) {
		if(impl==null){
			impl= new DaoFactoryJPAImpl(pizza,client,commande);
		}
		return impl;
	}


}
