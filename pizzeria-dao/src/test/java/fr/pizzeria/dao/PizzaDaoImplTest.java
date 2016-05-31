package fr.pizzeria.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImplTest {
	
	
	@Test
	@Ignore
	public void testFindAllPizza() throws DaoException {
		IPizzaDao pizza = new PizzaDaoImpl();
		List<Pizza> list =  pizza.findAllPizzas();
		list.sort(Comparator.comparing(Pizza::getCode));
		List<Pizza> assertList = new ArrayList<>();
		assertList.add(new Pizza("PEP", "Pépéroni", BigDecimal.valueOf(12.50),CategoriePizza.VIANDE));
		assertList.add(new Pizza("MAR", "Margherita", BigDecimal.valueOf(14.00),CategoriePizza.SANS_VIANDE));
		assertList.add(new Pizza("REI", "Reine", BigDecimal.valueOf(11.50),CategoriePizza.VIANDE));
		assertList.add(new Pizza("FRO", "La 4 fromages", BigDecimal.valueOf(12.00),CategoriePizza.SANS_VIANDE));
		assertList.add(new Pizza("CAN", "La cannibale", BigDecimal.valueOf(12.50),CategoriePizza.VIANDE));
		assertList.add(new Pizza("SAV", "La savoyarde", BigDecimal.valueOf(13.00),CategoriePizza.VIANDE));
		assertList.add(new Pizza("ORI", "L'orientale", BigDecimal.valueOf(13.50),CategoriePizza.VIANDE));
		assertList.add(new Pizza("IND", "L'indienne", BigDecimal.valueOf(14.00),CategoriePizza.VIANDE));
		assertList.add(new Pizza("SAM", "La saumonetta", BigDecimal.valueOf(14.00),CategoriePizza.POISSON));
		assertList.sort(Comparator.comparing(Pizza::getCode));
		Assert.assertArrayEquals(assertList.toArray(),list.toArray());
	}
	
	@Test
	public void testFindAllPizzaNotNull() throws DaoException {
		IPizzaDao pizza = new PizzaDaoImpl();
		List<Pizza> list = pizza.findAllPizzas();
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testFindAllPizzaSize() throws DaoException {
		IPizzaDao pizza = new PizzaDaoImpl();
		List<Pizza> list = pizza.findAllPizzas();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void testSavePizza() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();
		Pizza p = new Pizza("AAA","aaaa",BigDecimal.valueOf(10),CategoriePizza.POISSON);
		try {
			pizza.savePizza(p);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		List<Pizza> list =  pizza.findAllPizzas();
		Assert.assertTrue(list.contains(p));
	}
	
	@Test(expected = DaoException.class)
	public void testSavePizzaExistante() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();
		Pizza p = new Pizza("SAM", "La saumonetta", BigDecimal.valueOf(14.00),CategoriePizza.POISSON);
		
			pizza.savePizza(p);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testSavePizzaNull() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();

		
			pizza.savePizza(null);
		
	}
	

	@Test
	public void testUpdatePizza() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();
		Pizza p = new Pizza("SAM", "La saumonetta", BigDecimal.valueOf(15.00),CategoriePizza.POISSON);
		try {
			pizza.updatePizza("SAM",p);
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		List<Pizza> list =  pizza.findAllPizzas();
		Optional<Pizza> opt =list.stream().filter(pizza2->"SAM".equals(pizza2.getCode())).findFirst();
		if(opt.isPresent()){
			Pizza pizzaTrouve = opt.get();
			Assert.assertEquals("SAM",pizzaTrouve.getCode());
			Assert.assertEquals("La saumonetta",pizzaTrouve.getNom());
			Assert.assertTrue(pizzaTrouve.getPrix().compareTo(BigDecimal.valueOf(15.00))==0);
			Assert.assertEquals(CategoriePizza.POISSON,pizzaTrouve.getCat());
		}
		
	}
	
	@Test(expected = DaoException.class)
	public void testUpdatePizzaNonExistante() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();
		Pizza p = new Pizza("AAA","aaaa",BigDecimal.valueOf(10),CategoriePizza.POISSON);
		
			pizza.updatePizza("AAA",p);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testUpdatePizzaNull() {
		IPizzaDao pizza = new PizzaDaoImpl();

			String s = null;
			Pizza p = null;
			try{
				pizza.updatePizza(s,p);
			}
			catch(DaoException e){
				throw new NullPointerException();
			}
		
	}
	
	
	@Test(expected = DaoException.class)
	public void testUpdatePizzaCodeNull() throws DaoException {
		IPizzaDao pizza = new PizzaDaoImpl();

			String s = null;
			Pizza p =  new Pizza("SAM", "La saumonetta", BigDecimal.valueOf(15.00),CategoriePizza.POISSON);
			try{
				pizza.updatePizza(s,p);
			}
			catch(NullPointerException e){
				
			}
		
	}
	
	
	public void testUpdatePizzaPizzaNull()  {
		IPizzaDao pizza = new PizzaDaoImpl();

			String s = "SAM";
			Pizza p =  null;
			try{
				pizza.updatePizza(s,p);
			}catch(DaoException e){
				
			}
			
		
	}
	@Test
	public void testDeletePizza() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();
		Pizza p =  new Pizza("SAM", "La saumonetta", BigDecimal.valueOf(15.00),CategoriePizza.POISSON);
		try {
			pizza.deletePizza(p.getCode());
		} catch (DaoException e) {
			fail(e.getMessage());
		}
		List<Pizza> list = pizza.findAllPizzas();
		Assert.assertFalse(list.contains(p));
	}
	
	@Test(expected = DaoException.class)
	public void testDeletePizzaNonExistante() throws DaoException{
		IPizzaDao pizza = new PizzaDaoImpl();
		String s = new String("AAA");
		
			pizza.deletePizza(s);
		
	}
	
	@Test(expected = DaoException.class)
	public void testDeletePizzaNull() throws DaoException {
		IPizzaDao pizza = new PizzaDaoImpl();

			String s = null;
			
				pizza.deletePizza(s);
			
		
	}
}
