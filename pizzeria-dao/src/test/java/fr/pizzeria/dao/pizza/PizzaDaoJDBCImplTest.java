package fr.pizzeria.dao.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.AbstractPizzaDaoTest;
import fr.pizzeria.dao.IPizzaDao;

@Transactional
public class PizzaDaoJDBCImplTest extends AbstractPizzaDaoTest {
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJDBCImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
