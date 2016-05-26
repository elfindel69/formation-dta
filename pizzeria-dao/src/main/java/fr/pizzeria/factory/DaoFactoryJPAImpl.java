package fr.pizzeria.factory;

import javax.persistence.EntityManagerFactory;

import fr.pizzeria.dao.ClientDaoJPAImpl;
import fr.pizzeria.dao.CommandeDaoJPAImpl;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoJPAImpl;

public class DaoFactoryJPAImpl implements IDaoFactory {

	private static EntityManagerFactory em;
	private static DaoFactoryJPAImpl impl;

	private DaoFactoryJPAImpl(EntityManagerFactory createEntityManagerFactory) {
		em = createEntityManagerFactory;
	}

	@Override
	public IPizzaDao createPizzaDao() {
		return new PizzaDaoJPAImpl(em);
	}

	@Override
	public IClientDao createClientDao() {
		return new ClientDaoJPAImpl(em);
	}

	@Override
	public ICommandeDao createCommandeDao() {
		return new CommandeDaoJPAImpl(em);
	}
	
	public static DaoFactoryJPAImpl getImpl(EntityManagerFactory createEntityManagerFactory) {
		if(impl==null){
			impl= new DaoFactoryJPAImpl(createEntityManagerFactory);
		}
		return impl;
	}

	


}