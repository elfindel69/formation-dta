package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class MAJPizzaOptionMenuTest {
	@Rule

	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private MAJPizzaOptionMenu m;
	private IPizzaDao pizzaDao;

	@Before
	public void setUp() {
		pizzaDao = new PizzaDaoImpl();
		m = new MAJPizzaOptionMenu(new Scanner(System.in), pizzaDao);
	}

	@Test
	public void testExecute() throws DaoException, IOException {
		systemInMock.provideLines("SAM", "NEW", "aa", "12,5", "0");
		boolean next = m.execute();
		assertTrue(next);
		List<Pizza> list = pizzaDao.findAllPizzas();
		Optional<Pizza> opt = list.stream().filter(pizza -> "NEW".equals(pizza.getCode())).findFirst();
		if (opt.isPresent()) {
			Pizza pizzaTrouve = opt.get();
			assertEquals("NEW", pizzaTrouve.getCode());
			assertEquals("aa", pizzaTrouve.getNom());
			assertTrue(12.5 == pizzaTrouve.getPrix());
			assertEquals(CategoriePizza.VIANDE, pizzaTrouve.getCat());
			String outAttendu = Files.lines(Paths.get("src/test/ressources/resultatMAJPizzaMenu.txt"))
					.collect(Collectors.joining(System.lineSeparator()));
			outAttendu += System.lineSeparator();
			assertEquals(outAttendu, systemOutRule.getLog());
			systemOutRule.clearLog();
		}
	}
	
	@Test
	public void testExecuteCodeNonExistant() throws DaoException, IOException {
		systemOutRule.clearLog();
		systemInMock.provideLines("AAA", "NEW", "aa", "12,5", "0");
		boolean next = m.execute();
		assertTrue(next);
		List<Pizza> list = pizzaDao.findAllPizzas();
		Optional<Pizza> opt = list.stream().filter(pizza -> "NEW".equals(pizza.getCode())).findFirst();
		if (opt.isPresent()) {
			Pizza pizzaTrouve = opt.get();
			assertEquals("NEW", pizzaTrouve.getCode());
			assertEquals("aa", pizzaTrouve.getNom());
			assertTrue(12.5 == pizzaTrouve.getPrix());
			assertEquals(CategoriePizza.VIANDE, pizzaTrouve.getCat());
			String outAttendu = Files.lines(Paths.get("src/test/ressources/resultatMAJPizzaMenu.txt"))
					.collect(Collectors.joining(System.lineSeparator()));
			outAttendu += System.lineSeparator();
			assertEquals(outAttendu, systemOutRule.getLog());
			assertEquals("code non trouvé!", systemErrRule.getLog());
		}
	}

}
