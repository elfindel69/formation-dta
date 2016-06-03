package fr.pizzeria.aspects;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.config.SpringJpaConfig;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.repository.IPerformanceRepository;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Performance;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = SpringJpaConfig.class)
public class ADaoPerformanceTest {
	@Autowired
	@Qualifier("pizzaDaoJPARepoImpl")
	IPizzaDao pizzaDao;

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
