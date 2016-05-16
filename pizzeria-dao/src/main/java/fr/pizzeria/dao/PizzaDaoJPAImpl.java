package fr.pizzeria.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPAImpl implements IPizzaDao {

	private EntityManagerFactory entityFacto;
	private Map<String, Pizza> mapPizzas = new HashMap<>();

	public PizzaDaoJPAImpl(EntityManagerFactory entityFacto) throws DaoException {
		this.entityFacto = entityFacto;
		try {
			List<Pizza> pizzas = findAllPizzas();
			pizzas.forEach(p -> mapPizzas.put(p.getCode(), p));
		} catch (DaoException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		EntityManager em = entityFacto.createEntityManager();
		TypedQuery<Pizza> query = em.createNamedQuery("pizza.listPizzas", Pizza.class);
		List<Pizza> pizzas = query.getResultList();
		em.clear();
		em.close();
		return pizzas;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		if (!mapPizzas.containsKey(newPizza.getCode())) {
			EntityManager em = entityFacto.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			insertPizza(em, newPizza);
			et.commit();
			em.close();
		}else {
			throw new SavePizzaException("ce code existe déjà");
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {
			EntityManager em = entityFacto.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			Pizza oldPizza = mapPizzas.get(codePizza);
			updatePizza.setId(oldPizza.getId());
			em.merge(updatePizza);
			et.commit();
			em.close();
		} else {
			throw new UpdatePizzaException("code non trouvé!");
		}

	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {
			EntityManager em = entityFacto.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			Pizza oldPizza = mapPizzas.get(codePizza);
			em.remove(oldPizza);
			et.commit();
			em.close();
		} else {
			throw new DeletePizzaException("code non trouvé!");
		}
	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		List<List<Pizza>> partition = ListUtils.partition(pizzas, i);
		EntityManager em = entityFacto.createEntityManager();
		insertPizzas(partition, em, em.getTransaction());
		em.close();

	}

	private void insertPizzas(List<List<Pizza>> partition, EntityManager em, EntityTransaction et) {

		for (List<Pizza> list : partition) {
			et.begin();
			for (Pizza p : list) {
				if (!mapPizzas.containsKey(p.getCode())) {
					try {
						insertPizza(em, p);
					} catch (DaoException e) {
						System.out.println("pizza existante");
						et.rollback();
					}
					System.out.println("pizza créée");
				} else {
					System.out.println("pizza existante");
					et.rollback();
					et.begin();
				}
			}
			et.commit();
		}

	}

	private void insertPizza(EntityManager em, Pizza p) throws DaoException {
		try {
			em.persist(p);
		} catch (EntityExistsException e) {
			throw new DaoException(e);
		}
		mapPizzas.put(p.getCode(), p);
	}

}
