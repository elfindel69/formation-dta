package fr.pizzeria.dao;

import java.io.IOException;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFilesImpl implements IPizzaDao {
	private static final String DATA = "data";
	private Map<String, Pizza> mapPizzas = new HashMap<>();

	public PizzaDaoFilesImpl() {
		try {
			List<Pizza> pizzas = findAllPizzas();
			pizzas.forEach(p -> mapPizzas.put(p.getCode(), p));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					String line = Files.readAllLines(path, Charset.forName("UTF-8")).get(0);
					String[] tab = line.split(";");
					p.setNom(tab[0]);
					p.setPrix(Double.valueOf(tab[1]));
					p.setCat(CategoriePizza.valueOf(tab[2]));

				} catch (IOException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				return p;
			}).collect(Collectors.toList());

		} catch (IOException e) {
			throw new DaoException("Erreur de fichiers", e);
		}

		return pizzas;
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
			throw new UpdatePizzaException("code non trouvé!");
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
			throw new UpdatePizzaException("code non trouvé!");
		}
		try {
			Files.delete(fichier);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		mapPizzas.remove(codePizza);
	}

	@Override
	public void importPizzas() throws DaoException {
		throw new DaoException("méthode non supportée");
		
	}
}
