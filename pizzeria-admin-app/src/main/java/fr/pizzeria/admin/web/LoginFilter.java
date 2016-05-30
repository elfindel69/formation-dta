package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter(urlPatterns={"/*"},description="filtre de login")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("token")==null &&!req.getRequestURI().contains("/api")&& !req.getRequestURI().contains("/login")) {
			((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/login");
			
		} else {
			
			if(req.getRequestURI().contains("/login") && session.getAttribute("token")!=null) {
				((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/pizzas/list");
			} else{
				chain.doFilter(req, response);
			}
			
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
