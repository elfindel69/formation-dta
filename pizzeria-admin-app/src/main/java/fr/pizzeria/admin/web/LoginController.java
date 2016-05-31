package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final String ADMIN = "admin";
	private static final String ADMIN_PIZZERIA_FR = "admin@pizzeria.fr";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(200);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			response.sendError(400, "nombre de paramètre incorrect");
		} else {
			if (email.equals(ADMIN_PIZZERIA_FR) && password.equals(ADMIN)) {
				/* Création ou récupération de la session */
				HttpSession session = request.getSession();

				/* Mise en session d'une chaîne de caractères */
				session.setAttribute("token", true);
				response.sendRedirect(request.getContextPath()+"/pizzas/list");
			} else {
				response.setStatus(403);
				request.setAttribute("message", "erreur");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");

				dispatcher.forward(request, response);
			}
		}
	}

}
