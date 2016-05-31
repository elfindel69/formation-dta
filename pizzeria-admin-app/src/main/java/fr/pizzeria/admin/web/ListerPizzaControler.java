package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaService;

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
		request.setAttribute("list", pizzaService.findAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp");

		dispatcher.forward(request, response);
	}

}
