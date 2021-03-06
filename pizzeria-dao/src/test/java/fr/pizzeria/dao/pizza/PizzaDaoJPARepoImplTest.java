package fr.pizzeria.dao.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.AbstractPizzaDaoTest;
import fr.pizzeria.dao.IPizzaDao;

@ContextConfiguration(classes=SpringJpaConfig.class)
public class PizzaDaoJPARepoImplTest extends AbstractPizzaDaoTest {
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJPARepoImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
