package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.logging.Level;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.factory.DaoFactoryJPAImpl;
import fr.pizzeria.factory.IDaoFactory;

public class MaxPizzaOptionMenuTest {

	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private MaxPizzaOptionMenu m;
	private IDaoFactory daoFact;

	@Before
	public void setUp() {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
		EntityManagerFactory em = Persistence.createEntityManagerFactory("pizzeria-console");
		daoFact = DaoFactoryJPAImpl.getImpl(em);
		m = new MaxPizzaOptionMenu(daoFact);
	}

	@Test
	public void testExecute() throws DaoException, IOException {
		boolean next = m.execute();
		assertTrue(next);

		assertEquals("IND -> L'indienne (14.0€) VIANDE" + System.lineSeparator(), systemOutRule.getLog());
		systemOutRule.clearLog();

	}
}
