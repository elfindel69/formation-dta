package fr.pizzeria.spring.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@RestController
@RequestMapping(path = "/pizzas")
public class PizzaRest {
	@Autowired
	IPizzaDao pizzaDao;

	private static final Logger LOG = Logger.getLogger(PizzaRest.class.toString());

	@RequestMapping(method = RequestMethod.GET)
	public List<Pizza> findAll(HttpServletResponse response) {
		List<Pizza> pizzas = new ArrayList<>();
		try {
			pizzas = pizzaDao.findAllPizzas();
		} catch (DaoException e) {
			response.setStatus(400);
			LOG.log(Level.SEVERE, "problème de sauvegarde", e);
		}
		return pizzas;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Pizza save(@RequestBody Pizza newPizza, HttpServletResponse response) {
		try {
			pizzaDao.savePizza(newPizza);
			response.setStatus(201);
		} catch (DaoException e) {
			response.setStatus(400);
			LOG.log(Level.SEVERE, "problème de sauvegarde", e);
		}
		return newPizza;
	}
}