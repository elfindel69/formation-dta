package fr.pizzeria.admin.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	@Inject
	private PizzaService pizzaSession;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//implémetation de base
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		init();
	}

	public void init() {
		List<Pizza> pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", BigDecimal.valueOf(14.00), CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("REI", "Reine", BigDecimal.valueOf(11.50), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("FRO", "La 4 fromages", BigDecimal.valueOf(12.00), CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("CAN", "La cannibale", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAV", "La savoyarde", BigDecimal.valueOf(13.00), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("ORI", "L'orientale", BigDecimal.valueOf(13.50), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("IND", "L'indienne", BigDecimal.valueOf(14.00), CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAM", "La saumonetta", BigDecimal.valueOf(14.00), CategoriePizza.POISSON));
		
		pizzas.forEach(t -> {
			try {
				pizzaSession.save(t);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		});
		
	}
}
