package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class ListerPizzaControler
 */
@WebServlet("/pizzas/list")
public class ListerPizzaControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private PizzaService pizzaService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Pizza> pizzas = new ArrayList<>();
		try {
			pizzas = pizzaService.findAllPizzas();
		} catch (DaoException e) {
			response.sendError(500, "Désolé :(");
			e.printStackTrace();
		}
		request.setAttribute("list", pizzas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp");

		dispatcher.forward(request, response);
	}

}
