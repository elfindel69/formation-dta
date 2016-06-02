package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.pizza.IPizzaDao;

@Transactional
public class PizzaDaoJDBCImplTest extends PizzaDaoTest {
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJDBCImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
