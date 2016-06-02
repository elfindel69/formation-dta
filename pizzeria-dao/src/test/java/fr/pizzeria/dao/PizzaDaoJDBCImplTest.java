package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PizzaDaoJDBCImplTest extends AbstractPizzaDaoTest {
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJDBCImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
