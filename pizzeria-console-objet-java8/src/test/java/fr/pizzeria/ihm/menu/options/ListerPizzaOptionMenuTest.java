package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.pizza.PizzaDaoJPAImpl;
import fr.pizzeria.factory.DaoFactoryGenericImpl;
import fr.pizzeria.factory.IDaoFactory;

public class ListerPizzaOptionMenuTest {
	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private ListerPizzaOptionMenu m;
	private IDaoFactory daoFact;

	@Before
	public void setUp() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console");
		PizzaDaoJPAImpl pizza = new PizzaDaoJPAImpl(em);
		daoFact = new DaoFactoryGenericImpl(pizza,null,null);
		m = new ListerPizzaOptionMenu(daoFact);
	}

	@Test
	@Ignore
	public void testExecute() throws IOException {
		boolean next = m.execute();
		assertTrue(next);
		String outAttendu = Files.lines(Paths.get("src/test/resources/resultatListerPizzaMenu.txt"))
				.collect(Collectors.joining(System.lineSeparator()));
		outAttendu += System.lineSeparator();
		assertEquals(outAttendu, systemOutRule.getLog());
	}

}
