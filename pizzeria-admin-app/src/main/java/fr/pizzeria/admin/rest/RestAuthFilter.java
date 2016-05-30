package fr.pizzeria.admin.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import fr.pizzeria.admin.web.TokenService;

@Provider
public class RestAuthFilter implements ContainerRequestFilter{
	
	@Inject TokenService tokenService;

	@Override
	public void filter(ContainerRequestContext request) throws IOException {
		String token = request.getHeaderString("auth");
		if(!tokenService.isTokenValid(token) &&!request.getUriInfo().getPath().contains("/login")){	
				request.abortWith(Response.status(Status.FORBIDDEN).build());
			}
	}

}
