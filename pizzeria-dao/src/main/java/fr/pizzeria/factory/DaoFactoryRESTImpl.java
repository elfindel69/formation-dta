package fr.pizzeria.factory;

import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoRESTImpl;

public class DaoFactoryRESTImpl implements IDaoFactory {
	private static DaoFactoryRESTImpl impl;
	
	private DaoFactoryRESTImpl(){
		super();
	}
	public static IDaoFactory getImpl() {
		if(impl==null){
			impl= new DaoFactoryRESTImpl();
		}
		return impl;
	}
	@Override
	public IPizzaDao createPizzaDao() {
		return new PizzaDaoRESTImpl();
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
