package fr.pizzeria.aspects;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class AGenerateCodeTest {
	@Autowired
	@Qualifier("pizzaDaoJPARepoImpl")
	IPizzaDao pizzaDao;

	@Test
	public void testGenerateCode() throws DaoException {
		pizzaDao.findAllPizzas();
		Pizza onePizza = new Pizza(null, "TEST", new BigDecimal(10), CategoriePizza.SANS_VIANDE);
		pizzaDao.savePizza(onePizza);
		Pizza pizzaGenCode = pizzaDao.findPizzaByCode("TES");
		assertEquals("TES", pizzaGenCode.getCode());
	}

}
