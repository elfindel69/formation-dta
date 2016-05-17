package fr.pizzeria.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJPAImpl implements IPizzaDao {

	private Map<String, Pizza> mapPizzas = new HashMap<>();
	private EntityManagerFactory entityFacto;

	public PizzaDaoJPAImpl(EntityManagerFactory entityFacto) {
		this.entityFacto = entityFacto;
		try {
			List<Pizza> pizzas = findAllPizzas();
			pizzas.forEach(p -> mapPizzas.put(p.getCode(), p));
		} catch (DaoException e) {
			System.err.println(e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		EntityManager em = entityFacto.createEntityManager();
		TypedQuery<Pizza> query = em.createNamedQuery("pizza.listPizzas", Pizza.class);
		List<Pizza> pizzas = query.getResultList();
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
		} else {
			throw new SavePizzaException("ce code existe déjà");
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {
			EntityManager em = entityFacto.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			Pizza oldPizza = findPizzaByCode(codePizza, em);
			oldPizza.setCode(updatePizza.getCode());
			oldPizza.setNom(updatePizza.getNom());
			oldPizza.setPrix(updatePizza.getPrix());
			oldPizza.setCat(updatePizza.getCat());
			mapPizzas.put(codePizza, oldPizza);
			et.commit();
			em.close();
		} else {
			throw new UpdatePizzaException("code non trouvé!");
		}

	}

	private Pizza findPizzaByCode(String codePizza, EntityManager em) {
		return em.createQuery("select p from Pizza p where p.code = :code", Pizza.class).setParameter("code", codePizza)
				.getSingleResult();
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {
			EntityManager em = entityFacto.createEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			Pizza oldPizza = findPizzaByCode(codePizza, em);
			em.remove(oldPizza);
			mapPizzas.remove(codePizza);
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
				try {
					insertPizza(em, p);
				} catch (DaoException e) {
					System.out.println("pizza existante");
					et.rollback();
					et.begin();
				}
				System.out.println("pizza créée");
			}
			et.commit();
		}

	}

	private void insertPizza(EntityManager em, Pizza p) throws DaoException {
		try {
			em.persist(p);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}
		mapPizzas.put(p.getCode(), p);
	}

	@Override
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		Set<Pizza> pizzas = new HashSet<>();
		for (String code : codes) {
			EntityManager em = entityFacto.createEntityManager();
			Pizza p = em.createQuery("select p from Pizza p where p.code = :code", Pizza.class)
					.setParameter("code", code).getSingleResult();
			em.close();
			pizzas.add(p);
		}
		return pizzas;
	}

}
