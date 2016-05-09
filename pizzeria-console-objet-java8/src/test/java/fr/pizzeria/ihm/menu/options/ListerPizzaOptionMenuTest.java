package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;

public class ListerPizzaOptionMenuTest {
	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	private ListerPizzaOptionMenu m;
	private IPizzaDao pizzaDao;

	@Before
	public void setUp() {
		pizzaDao = new PizzaDaoImpl();
		m = new ListerPizzaOptionMenu(pizzaDao);
	}

	@Test
	public void testExecute() throws IOException {
		boolean next = m.execute();
		assertTrue(next);
		String outAttendu = Files.lines(Paths.get("src/test/ressources/resultatListerPizzaMenu.txt"))
				.collect(Collectors.joining(System.lineSeparator()));
		outAttendu += System.lineSeparator();
		assertEquals(outAttendu, systemOutRule.getLog());
	}

}
