package fr.pizzeria.dao.pizza;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.AbstractPizzaDaoTest;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@ContextConfiguration(classes = SpringJpaConfig.class)
public class AGenerateCodeTest extends AbstractPizzaDaoTest {
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJPARepoImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}

	@Test
	public void testGenerateCode() throws DaoException {
		pizzaDao.findAllPizzas();
		Pizza onePizza = new Pizza(null, "TEST", new BigDecimal(10), CategoriePizza.SANS_VIANDE);
		pizzaDao.savePizza(onePizza);
		Pizza pizzaGenCode = pizzaDao.findPizzaByCode("TES");
		assertEquals("TES", pizzaGenCode.getCode());
	}

}
