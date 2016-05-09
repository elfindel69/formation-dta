package fr.pizzeria.ihm.menu.options;

import static org.junit.Assert.*;
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

public class NouvellePizzaOptionMenuTest {
	@Rule

	public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

	@Rule

	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	@Rule

	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	private NouvellePizzaOptionMenu m;
	private IPizzaDao pizzaDao;

	@Before
	public void setUp() {
		pizzaDao =new PizzaDaoImpl();
		m = new NouvellePizzaOptionMenu(new Scanner(System.in), pizzaDao);
	}

	@Test
	public void testExecute() throws DaoException, IOException {
		systemInMock.provideLines("NEW","aa","12,5","0");
		boolean next = m.execute();
		assertTrue(next);
		List<Pizza> list =  pizzaDao.findAllPizzas();
		Optional<Pizza> opt =list.stream().filter(pizza->"NEW".equals(pizza.getCode())).findFirst();
		if(opt.isPresent()){
			Pizza pizzaTrouve = opt.get();
			assertEquals("NEW",pizzaTrouve.getCode());
			assertEquals("aa",pizzaTrouve.getNom());
			assertTrue(12.5 == pizzaTrouve.getPrix());
			assertEquals(CategoriePizza.VIANDE,pizzaTrouve.getCat());
			String outAttendu = Files.lines(Paths.get("src/test/ressources/resultatAjouterPizzaMenu.txt")).collect(Collectors.joining(System.lineSeparator()));
			outAttendu+=System.lineSeparator();
			assertEquals(outAttendu,systemOutRule.getLog());
		}
	}
}
