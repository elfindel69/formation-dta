package fr.pizzeria.dao.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.AbstractPizzaDaoTest;
import fr.pizzeria.dao.IPizzaDao;

@ContextConfiguration(classes=SpringJpaConfig.class)
public class PizzaDaoJPASpringImplTest extends AbstractPizzaDaoTest{
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJPASpringImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
