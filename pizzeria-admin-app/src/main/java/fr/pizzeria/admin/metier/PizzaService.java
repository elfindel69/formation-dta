package fr.pizzeria.admin.metier;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@SessionScoped
public class PizzaService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IPizzaDao pizzaDao;
	
	

	public PizzaService() {
		super();
	}

	public void savePizza(Pizza newPizza) throws DaoException {
		pizzaDao.savePizza(newPizza);

	}

	public void updatePizza(String code, Pizza newPizza) throws DaoException {
		pizzaDao.updatePizza(code, newPizza);
	}

	public Pizza findPizzaByCode(String code) throws DaoException {
		return pizzaDao.findPizzaByCode(code);
	}

	public void deletePizza(String code) throws DaoException {
		pizzaDao.deletePizza(code);

	}

	public List<Pizza> findAllPizzas() throws DaoException {
		return pizzaDao.findAllPizzas();
	}

}
