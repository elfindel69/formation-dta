package fr.pizzeria.dao.pizza;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoJPASpringImpl implements IPizzaDao {

	private static final String PIZZA_LIST_PIZZAS = "pizza.listPizzas";
	private static final String PIZZA_BY_CODE = "pizza.byCode";
	private static final Logger LOG = Logger.getLogger(PizzaDaoJPAImpl.class.toString());

	@PersistenceContext(unitName = "pizzeria-console")
	private EntityManager em;

	@Autowired
	private BatchInsertPizzaJpa batchInsertPizza;

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		TypedQuery<Pizza> query = em.createNamedQuery(PIZZA_LIST_PIZZAS, Pizza.class);
		List<Pizza> pizzas = query.getResultList();
		return pizzas;
	}

	@Override
	@Transactional
	public void savePizza(Pizza newPizza) throws DaoException {
		try {
			em.persist(newPizza);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}

	}

	@Override
	@Transactional
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		Pizza oldPizza = findPizzaByCode(codePizza);
		oldPizza.setCode(updatePizza.getCode());
		oldPizza.setNom(updatePizza.getNom());
		oldPizza.setPrix(updatePizza.getPrix());
		oldPizza.setCat(updatePizza.getCat());
	}

	@Override
	@Transactional
	public void deletePizza(String codePizza) throws DaoException {
		Pizza oldPizza = findPizzaByCode(codePizza);
		em.remove(oldPizza);
	}

	@Override
	@Transactional
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
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		Set<Pizza> pizzas = new HashSet<>();
		for (String code : codes) {
			Pizza p = findPizzaByCode(code);
			pizzas.add(p);
		}
		return pizzas;
	}

	@Override
	public Pizza findPizzaByCode(String code) throws DaoException {

		return em.createNamedQuery(PIZZA_BY_CODE, Pizza.class).setParameter("code", code).getSingleResult();
	}

}
