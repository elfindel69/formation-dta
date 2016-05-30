package fr.pizzeria.admin.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fr.pizzeria.admin.web.TokenService;

@Path("/login")
public class LoginResource {
	@Inject
	private TokenService tokenService;
	private static final String ADMIN = "admin";
	private static final String ADMIN_PIZZERIA_FR = "admin@pizzeria.fr";

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("email") String email, @FormParam("password") String password) {
		Response resp;
		if (email.equals(ADMIN_PIZZERIA_FR) && password.equals(ADMIN)) {
			String token = tokenService.generateToken();
			resp = Response.ok(token).build();
		} else {
			resp = Response.status(Status.FORBIDDEN).build();
		}
		return resp;

	}
}
