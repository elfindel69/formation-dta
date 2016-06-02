package fr.pizzeria.dao.pizza;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.pizza.batch.BatchInsertPizzaJpa;
import fr.pizzeria.dao.repository.IPizzaRepository;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoJPARepoImpl implements IPizzaDao {

	private static final Logger LOG = Logger.getLogger(PizzaDaoJPAImpl.class.toString());
	
	@Autowired
	IPizzaRepository pizzaRepo;

	@Autowired
	private BatchInsertPizzaJpa batchInsertPizza;

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {

		return pizzaRepo.findAll();
	}

	@Override
	@Transactional
	public void savePizza(Pizza newPizza) throws DaoException {
		pizzaRepo.save(newPizza);
	}

	@Override
	@Transactional
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		Pizza oldPizza = pizzaRepo.findByCode(codePizza);
		oldPizza.setCode(updatePizza.getCode());
		oldPizza.setNom(updatePizza.getNom());
		oldPizza.setPrix(updatePizza.getPrix());
		oldPizza.setCat(updatePizza.getCat());
	}

	@Override
	@Transactional
	public void deletePizza(String codePizza) throws DaoException {
		Pizza pizza = pizzaRepo.findByCode(codePizza);
		pizzaRepo.delete(pizza);

	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		List<List<Pizza>> partition = ListUtils.partition(pizzas, i);
		partition.forEach(t -> {
			try {
				batchInsertPizza.insertPizzas(t);
			} catch (DataAccessException e) {
				LOG.log(Level.SEVERE, e.getMessage(), e);
				throw e;
			}
		});

	}

	@Override
	@Transactional
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		Set<Pizza> pizzas = new HashSet<>();
		for (String code : codes) {
			Pizza p = findPizzaByCode(code);
			pizzas.add(p);
		}
		return pizzas;
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws DaoException {
		return pizzaRepo.findByCode(codePizza);
	}

}
