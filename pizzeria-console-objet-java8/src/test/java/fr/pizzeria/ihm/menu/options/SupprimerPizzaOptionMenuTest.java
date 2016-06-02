package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.IOException;
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
import fr.pizzeria.dao.pizza.PizzaDaoJPAImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.DaoFactoryGenericImpl;
import fr.pizzeria.factory.IDaoFactory;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenuTest {

	@Rule

	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private SupprimerPizzaOptionMenu m;
	private IPizzaDao pizzaDao;
	private IDaoFactory daoFact;

	@Before
	public void setUp() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console");
		PizzaDaoJPAImpl pizza = new PizzaDaoJPAImpl(em);
		daoFact = new DaoFactoryGenericImpl(pizza,null,null);
		pizzaDao = daoFact.createPizzaDao();
		m = new SupprimerPizzaOptionMenu(new Scanner(System.in), daoFact);
	}

	@Test
	public void testExecute() throws DaoException, IOException {
		systemInMock.provideLines("SAM");
		boolean next = m.execute();
		assertTrue(next);
		List<Pizza> list = pizzaDao.findAllPizzas();
		Optional<Pizza> opt = list.stream().filter(pizza -> "SAM".equals(pizza.getCode())).findFirst();
		assertFalse(opt.isPresent());

		String outAttendu = Files.lines(Paths.get("src/test/resources/resultatSupprimerPizzaMenu.txt"))
				.collect(Collectors.joining(System.lineSeparator()));
		outAttendu += System.lineSeparator();
		assertEquals(outAttendu, systemOutRule.getLog());
		systemOutRule.clearLog();

	}

	@Test
	public void testExecuteCodeNonExistant() throws DaoException, IOException {
		systemInMock.provideLines("AAA");
		boolean next = m.execute();
		assertTrue(next);

		String outAttendu = Files.lines(Paths.get("src/test/resources/resultatKOSupprimerPizzaMenu.txt"))
				.collect(Collectors.joining(System.lineSeparator()));
		outAttendu += System.lineSeparator();
		assertEquals(outAttendu, systemOutRule.getLog());
		assertEquals("code non trouvé!" + System.lineSeparator(), systemErrRule.getLog());

	}

}
