package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exceptions.DaoException;

public class MaxPizzaOptionMenuTest {

	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private MaxPizzaOptionMenu m;
	private IPizzaDao pizzaDao;

	@Before
	public void setUp() {
		pizzaDao = new PizzaDaoImpl();
		m = new MaxPizzaOptionMenu(pizzaDao);
	}

	@Test
	public void testExecute() throws DaoException, IOException {
		boolean next = m.execute();
		assertTrue(next);

		assertEquals("IND -> L'indienne (14.0€) VIANDE" + System.lineSeparator(), systemOutRule.getLog());
		systemOutRule.clearLog();

	}
}
