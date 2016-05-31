package fr.pizzeria.admin.metier;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@Stateless
public class PizzaService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "pizzeria-db")
	private EntityManager em;

	public PizzaService() {
		super();
	}

	public void save(Pizza newPizza) throws DaoException {
		try {
			em.persist(newPizza);
		} catch (PersistenceException e) {
			throw new DaoException(e);
		}

	}

	public void update(String code, Pizza newPizza) throws DaoException {
		Pizza oldPizza = findObjectByCode(code);
		oldPizza.setCode(newPizza.getCode());
		oldPizza.setNom(newPizza.getNom());
		oldPizza.setPrix(newPizza.getPrix());
		oldPizza.setCat(newPizza.getCat());
	}

	public Pizza findObjectByCode(String code) throws DaoException {
		try {
			return em.createQuery("select p from Pizza p where p.code = :code", Pizza.class).setParameter("code", code)
					.getSingleResult();
		} catch (NoResultException e) {
			throw new DaoException("code non trouvé!");
		}

	}

	public void delete(String code) throws DaoException {
		Pizza oldPizza = findObjectByCode(code);
		em.remove(oldPizza);

	}

	public List<Pizza> findAll(){
		TypedQuery<Pizza> query = em.createQuery("Select p from Pizza p", Pizza.class);
		return query.getResultList();
	}

	public void update(Pizza newPizza) throws DaoException {
		update(newPizza.getCode(), newPizza);
	}

}
