package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;
@Repository
@Lazy
public class PizzaDaoRESTImpl implements IPizzaDao {
	private static final String APPLICATION_JSON = "application/json";
	private static final String URL_PIZZAS = "http://localhost:8080/pizzeria-admin-app/api/rest/pizzas";

	public PizzaDaoRESTImpl() {
		
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource webResourceGet = client.resource(URL_PIZZAS);
		ClientResponse response = webResourceGet.type(APPLICATION_JSON).get(ClientResponse.class);
		return response.getEntity(new GenericType<List<Pizza>>() {
		});
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {

		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		WebResource webResourcePost = client.resource(URL_PIZZAS);
		ClientResponse post = webResourcePost.type(APPLICATION_JSON).post(ClientResponse.class, newPizza);
		if (post.getStatus() == 400) {
			throw new DaoException("ce code existe déjà");
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		WebResource webResourcePost = client.resource(URL_PIZZAS);
		ClientResponse post = webResourcePost.type(APPLICATION_JSON).put(ClientResponse.class, updatePizza);
		if (post.getStatus() == 500) {
			throw new DaoException("ce code existe déjà");
		}

	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		// Create Jersey client
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		String postURL = URL_PIZZAS +"/"+ codePizza;
		WebResource webResourcePost = client.resource(postURL);
		webResourcePost.delete(ClientResponse.class);

	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		throw new DaoException("méthode non implémentée");

	}

	@Override
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		throw new DaoException("méthode non implémentée");
	}

	@Override
	public Pizza findPizzaByCode(String parameter) throws DaoException {
		throw new DaoException("méthode non implémentée");
	}

}
