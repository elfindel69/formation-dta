package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJpaConfig;

@ContextConfiguration(classes=SpringJpaConfig.class)
public class PizzaDaoJPASpringImplTest extends AbstractPizzaDaoTest{
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJPASpringImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
