package fr.pizzeria.dao.pizza;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoFilesImpl implements IPizzaDao {
	private static final String METHODE_NON_IMPLEMENTEE = "méthode non implémentée";
	private static final String CODE_NON_TROUVE = "code non trouvé!";
	private static final String DATA = "data";
	private Map<String, Pizza> mapPizzas = new HashMap<>();
	private static final Logger LOG = Logger.getLogger(PizzaDaoFilesImpl.class.toString());
	
	@PostConstruct
	void init() {
		try {
			List<Pizza> pizzas = findAllPizzas();
			pizzas.forEach(p -> mapPizzas.put(p.getCode(), p));
		} catch (DaoException e) {
			LOG.log(Level.SEVERE, "erreur d'importation", e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		List<Pizza> pizzas = new ArrayList<>();
		try (Stream<Path> datastream = Files.list(Paths.get(DATA))) {
			pizzas = datastream.map(path -> {
				Pizza p = new Pizza();

				p.setCode(path.getFileName().toString().replaceAll(".txt", ""));
				try {
					p = setPizza(path, p);

				} catch (IOException e) {
					LOG.log(Level.SEVERE, e.getMessage(), e);
				}
				return p;
			}).collect(Collectors.toList());

		} catch (IOException e) {
			throw new DaoException("Erreur de fichiers", e);
		}

		return pizzas;
	}

	private Pizza setPizza(Path path, Pizza p) throws IOException {
		String line = Files.readAllLines(path, Charset.forName("UTF-8")).get(0);
		String[] tab = line.split(";");
		p.setNom(tab[0]);
		p.setPrix(new BigDecimal(tab[1]));
		p.setCat(CategoriePizza.valueOf(tab[2]));
		return p;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		try {
			Path fichier = Paths.get(DATA + "/" + newPizza.getCode() + ".txt");

			Files.write(fichier, Arrays.asList(pizzaToString(newPizza)), StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		mapPizzas.put(newPizza.getCode(), newPizza);
	}

	private String pizzaToString(Pizza newPizza) {

		return newPizza.getNom() + ";" + newPizza.getPrix() + ";" + newPizza.getCat().name();
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		Path fichier = Paths.get(DATA + "/" + codePizza + ".txt");
		if (!mapPizzas.containsKey(codePizza)) {
			throw new UpdatePizzaException(CODE_NON_TROUVE);
		}
		try {

			Files.write(fichier, Arrays.asList(pizzaToString(updatePizza)), StandardOpenOption.WRITE);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		mapPizzas.put(codePizza, updatePizza);
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		Path fichier = Paths.get(DATA + "/" + codePizza + ".txt");
		if (!mapPizzas.containsKey(codePizza)) {
			throw new UpdatePizzaException(CODE_NON_TROUVE);
		}
		try {
			Files.delete(fichier);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		mapPizzas.remove(codePizza);
	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		throw new DaoException(METHODE_NON_IMPLEMENTEE);
	}

	@Override
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		throw new DaoException(METHODE_NON_IMPLEMENTEE);
	}

	@Override
	public Pizza findPizzaByCode(String parameter) throws DaoException {
		throw new DaoException(METHODE_NON_IMPLEMENTEE);
	}
}
