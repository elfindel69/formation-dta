package fr.pizzeria.admin.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@Path("/pizzas")
public class PizzaRessource {
	@Inject
	PizzaService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> findAllPizzas() {
		return service.findAllPizzas();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response savePizza(Pizza newPizza) {
		ResponseBuilder builder = null;
		try {
			service.savePizza(newPizza);
			builder = Response.status(Status.CREATED);
		} catch (DaoException e) {
			builder = Response.status(400);

		}
		return builder.build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePizza(Pizza newPizza) {
		ResponseBuilder builder = null;
		try {
			service.updatePizza(newPizza);
			builder = Response.status(Status.OK);
		} catch (DaoException e) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();
	}

	@DELETE
	@Path("/{code}")
	public Response deletePizza(@PathParam("code") String code) throws DaoException {
		ResponseBuilder builder = null;
		try {
			service.deletePizza(code);
			builder = Response.status(Status.OK);
		} catch (DaoException e) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}
}
