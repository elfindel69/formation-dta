package fr.pizzeria.admin.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoImpl;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class PizzaServletWebApi
 */
public class PizzaServletWebApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPizzaDao pizzaDao = new PizzaDaoImpl();
	private static final Logger LOG = Logger.getLogger(PizzaServletWebApi.class.toString());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServletWebApi() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<Pizza> pizzas = new ArrayList<>();
		try {
			pizzas = pizzaDao.findAllPizzas();
			response.getWriter().append(pizzas.toString());
		} catch (DaoException e) {
			response.sendError(500, "Désolé :(");
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("j'ai bien reçu le post");
		if(StringUtils.isBlank(request.getParameter("code"))||StringUtils.isBlank(request.getParameter("nom"))||StringUtils.isBlank(request.getParameter("prix"))||StringUtils.isBlank(request.getParameter("categorie"))){
			response.sendError(400, "Non non non ! Donnes moi toutes les valeurs !");
		}
		Pizza newPizza=new Pizza();
		String code = request.getParameter("code");
		newPizza.setNom(request.getParameter("nom"));
		newPizza.setCode(code);
		try{
			newPizza.setPrix(new BigDecimal(request.getParameter("prix")));
		}catch(NumberFormatException e){
			response.sendError(500, "Désolé :(");
		}
		newPizza.setUrlImage(request.getParameter("urlImage"));
		
		newPizza.setCat(CategoriePizza.valueOf(request.getParameter("categorie")));
		try {
			pizzaDao.savePizza(newPizza);
			response.setStatus(201);
		} catch (DaoException e) {
			response.sendError(500, "Désolé :(");
		}
		
	}

}
