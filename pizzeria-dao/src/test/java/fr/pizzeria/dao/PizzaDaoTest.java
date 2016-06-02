package fr.pizzeria.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PizzaDaoSpringTest.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PizzaDaoTest {

    private static final int NB_INITIAL_PIZZA = 11;

    protected IPizzaDao pizzaDao;

    @Test
    public void testfindAllPizzas() throws DaoException {
        List<Pizza> pizzas = pizzaDao.findAllPizzas();
        assertEquals(NB_INITIAL_PIZZA, pizzas.size());
    }

    @Test
    public void testSavePizza() throws DaoException {
        Pizza pizza = new Pizza("PEP X","Pépéroni X", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE);
        pizzaDao.savePizza(pizza);
        List<Pizza> pizzas = pizzaDao.findAllPizzas();
        assertEquals(NB_INITIAL_PIZZA + 1, pizzas.size());
        Pizza pizzaResult = pizzaDao.findPizzaByCode("PEP X");
        assertEquals("Pépéroni X", pizzaResult.getNom());
    }

    @Test
    public void testUpdatePizza() throws DaoException {
        Pizza onePizza = pizzaDao.findPizzaByCode("PEP");
        onePizza.setNom("Pépéroni 2");
        pizzaDao.updatePizza("PEP", onePizza);

        Pizza pizzaResult = pizzaDao.findPizzaByCode("PEP");
        assertEquals("Pépéroni 2", pizzaResult.getNom());
    }

    @Test
    public void testDeletePizza() throws DaoException {
        pizzaDao.deletePizza("PEP");
        List<Pizza> pizzas = pizzaDao.findAllPizzas();
        assertEquals(NB_INITIAL_PIZZA - 1, pizzas.size());
    }

    @Test
    public void testSaveAllPizza() throws DaoException {
        List<Pizza> pizzas = getListePizzas();
        pizzaDao.importPizzas(pizzas, 3);

        List<Pizza> pizzasBdd = pizzaDao.findAllPizzas();
        assertEquals(NB_INITIAL_PIZZA+pizzas.size(), pizzasBdd.size());
    }

    @Test
    public void testSaveAllPizzaRollback() throws DaoException {
        List<Pizza> pizzas = getListePizzasWithErrors();
        try {
            pizzaDao.importPizzas(pizzas, 3);
            fail("une exception aurait dû être lancée");
        } catch (DataAccessException e) {
        	List<Pizza> pizzasBdd = pizzaDao.findAllPizzas();
        	assertEquals(NB_INITIAL_PIZZA+3, pizzasBdd.size());    
        }

    }

    private List<Pizza> getListePizzas() {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza("PEP 1","PEP 1", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("MAR 1","MAR 1", BigDecimal.valueOf(14.00), CategoriePizza.SANS_VIANDE));
        pizzas.add(new Pizza("REI 1","REI 1", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("FRO 1","FRO 1", BigDecimal.valueOf(12.00), CategoriePizza.SANS_VIANDE));
        pizzas.add(new Pizza("CAN 1","CAN 1", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("SAV 1","SAV 1", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("ORI 1","ORI 1", BigDecimal.valueOf(13.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("IND 1","IND 1", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("SAU 1","SAU 1", BigDecimal.valueOf(14.00), CategoriePizza.POISSON));

        return pizzas;
    }

    private List<Pizza> getListePizzasWithErrors() {
    	List<Pizza> pizzas = new ArrayList<>();
    	pizzas.add(new Pizza("A","A", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("B","B", BigDecimal.valueOf(14.00), CategoriePizza.SANS_VIANDE));
        pizzas.add(new Pizza("C","C", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("D","D", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("D","D", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
        pizzas.add(new Pizza("E","E", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE));

        return pizzas;
    }
}
