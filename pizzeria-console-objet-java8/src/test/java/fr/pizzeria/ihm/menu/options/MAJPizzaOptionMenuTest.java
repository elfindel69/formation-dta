package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.DaoFactoryJPAImpl;
import fr.pizzeria.factory.IDaoFactory;
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
	private IDaoFactory daoFact;
	private IPizzaDao pizzaDao;

	@Before
	public void setUp() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console");
		daoFact = DaoFactoryJPAImpl.getImpl(em);
		m = new MAJPizzaOptionMenu(new Scanner(System.in), daoFact);
		pizzaDao = daoFact.createPizzaDao();
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
			assertTrue(pizzaTrouve.getPrix().compareTo(BigDecimal.valueOf(12.5)) == 0);
			assertEquals(CategoriePizza.VIANDE, pizzaTrouve.getCat());
			String outAttendu = Files.lines(Paths.get("src/test/resources/resultatMAJPizzaMenu.txt"))
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
			assertTrue(pizzaTrouve.getPrix().compareTo(BigDecimal.valueOf(12.5)) == 0);
			assertEquals(CategoriePizza.VIANDE, pizzaTrouve.getCat());
			String outAttendu = Files.lines(Paths.get("src/test/resources/resultatMAJPizzaMenu.txt"))
					.collect(Collectors.joining(System.lineSeparator()));
			outAttendu += System.lineSeparator();
			assertEquals(outAttendu, systemOutRule.getLog());
			assertEquals("code non trouvé!", systemErrRule.getLog());
		}
	}

}
