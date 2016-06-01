package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.dao.pizza.PizzaDaoFilesImpl;
import fr.pizzeria.dao.pizza.PizzaDaoJDBCImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PizzaDaoSpringTest.class)
public class PizzaDaoJDBCImplTest {
	@Autowired
	private PizzaDaoJDBCImpl pizzaJDBC;

	@Test
	public void testInsertPizzas() throws DaoException {
		PizzaDaoFilesImpl files = new PizzaDaoFilesImpl();
		List<Pizza> pizzas = new ArrayList<>();
		pizzas = files.findAllPizzas();
		pizzaJDBC.importPizzas(pizzas, 3);
		pizzas = files.findAllPizzas();
		Assert.assertEquals(13, pizzas.size());
	}

	@Test
	public void testFindAllPizzas() throws DaoException {
		List<Pizza> pizzas = pizzaJDBC.findAllPizzas();
		Assert.assertEquals(11, pizzas.size());
	}
}
