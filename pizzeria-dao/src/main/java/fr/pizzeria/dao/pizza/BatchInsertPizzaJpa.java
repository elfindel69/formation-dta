package fr.pizzeria.dao.pizza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.model.Pizza;

public class BatchInsertPizzaJpa {
	@PersistenceContext(unitName = "pizzeria-console")
	private EntityManager em;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertPizzas(List<Pizza> list) {
		list.forEach(em::persist);
	}
}
