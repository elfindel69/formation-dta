package fr.pizzeria.admin.metier;

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

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;

@Path("/clients")
public class ClientRessource {
	@Inject
	ClientService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> findAllClients() {
		return service.findAllClients();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveClient(Client newClient) {
		ResponseBuilder builder = null;
		try {
			service.saveClient(newClient);
			builder = Response.status(Status.CREATED);
		} catch (DaoException e) {
			builder = Response.status(400);

		}
		return builder.build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateClient(Client newClient) {
		ResponseBuilder builder = null;
		try {
			service.updateClient(newClient);
			builder = Response.status(Status.OK);
		} catch (DaoException e) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteClient(@PathParam("id") Integer id) throws DaoException {
		ResponseBuilder builder = null;
		try {
			service.deleteClient(id);
			builder = Response.status(Status.OK);
		} catch (DaoException e) {
			builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		}
		return builder.build();

	}
}
