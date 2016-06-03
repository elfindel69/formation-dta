package fr.pizzeria.dao.pizza;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.AbstractPizzaDaoTest;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.repository.IPerformanceRepository;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Performance;
import fr.pizzeria.model.Pizza;

@ContextConfiguration(classes = SpringJpaConfig.class)
public class APizzaDaoJPASpringTest extends AbstractPizzaDaoTest {
	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJPARepoImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}

	@Autowired
	IPerformanceRepository perfRepo;

	@Test
	public void testFindAllPerfs() throws DaoException {
		pizzaDao.findAllPizzas();
		Pizza onePizza = pizzaDao.findPizzaByCode("PEP");
		onePizza.setNom("Pépéroni 2");
		pizzaDao.updatePizza("PEP", onePizza);
		List<Performance> perf = perfRepo.findAll();
		assertEquals(3, perf.size());
	}

}
