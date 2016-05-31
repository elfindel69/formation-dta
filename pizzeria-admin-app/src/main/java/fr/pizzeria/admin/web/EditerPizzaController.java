package fr.pizzeria.admin.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class EditerPizzaController
 */
@WebServlet("/pizzas/edit")
public class EditerPizzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(EditerPizzaController.class.toString());
	@Inject
	private PizzaService pizzaService;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		if (StringUtils.isBlank(code)) {
			response.sendRedirect("/pizzas/list/?msgErreur=OOps code non fourni");
		} else {
			Pizza pizza = new Pizza();
			try {
				pizza = pizzaService.findObjectByCode(code);
				response.setStatus(200);
			} catch (DaoException e) {
				response.sendRedirect("/pizzas/list/?msgErreur=OOps pizza non trouvée");
			}
			request.setAttribute("pizza", pizza);
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/pizzas/editerPizza.jsp");

			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (StringUtils.isBlank(request.getParameter("id")) ||StringUtils.isBlank(request.getParameter("oldCode"))|| StringUtils.isBlank(request.getParameter("newCode"))
				|| StringUtils.isBlank(request.getParameter("nom")) || StringUtils.isBlank(request.getParameter("prix"))
				|| StringUtils.isBlank(request.getParameter("cat"))) {
			response.sendError(400, "Non non non ! Donnes moi toutes les valeurs !");
		}
		Pizza newPizza = new Pizza();
		String code = request.getParameter("oldCode");
		newPizza.setId(Integer.parseInt(request.getParameter("id")));
		newPizza.setNom(request.getParameter("nom"));
		newPizza.setCode(request.getParameter("newCode"));
		try {
			newPizza.setPrix(new BigDecimal(request.getParameter("prix")));
		} catch (NumberFormatException e) {
			response.sendError(500, "Désolé :(");
		}

		newPizza.setCat(CategoriePizza.valueOf(request.getParameter("cat")));

		try {
			pizzaService.update(code, newPizza);
			System.out.println(newPizza);

		} catch (DaoException e) {
			response.sendError(500, "Désolé :(");
		}
		response.setStatus(201);
		response.sendRedirect(request.getContextPath() + "/pizzas/list");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		LOG.info(code);
		if (StringUtils.isBlank(code)) {
			resp.sendError(400, "nombre de paramètre incorrect");
		} else {
			try {
				pizzaService.delete(code);
			} catch (DaoException e) {
				resp.sendError(500, "Problème lors de la création de la pizza : pizza inexistante");
			} catch (NumberFormatException e) {
				resp.sendError(400, "Format des paramètres incorect");
			}

		}
	}

}
