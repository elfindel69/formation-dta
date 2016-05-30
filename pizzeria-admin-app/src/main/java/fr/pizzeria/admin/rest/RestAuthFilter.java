package fr.pizzeria.admin.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.pizzeria.admin.web.TokenService;

public class RestAuthFilter implements ContainerRequestFilter{
	
	@Inject TokenService tokenService;

	@Override
	public void filter(ContainerRequestContext arg0) throws IOException {
		String token = arg0.getHeaderString("auth");
		if(!tokenService.isTokenValid(token) &&!arg0.getUriInfo().getPath().contains("/login")){	
				arg0.abortWith(Response.status(Status.FORBIDDEN).build());
			}
	}

}
