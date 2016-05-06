package fr.pizzeria.dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoFilesImpl implements IPizzaDao {
	public PizzaDaoFilesImpl() {

	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		List<Pizza> pizzas = new ArrayList<>();
		try {
			pizzas = Files.list(Paths.get("data")).map(path -> {
				Pizza p = new Pizza();

				p.setCode(path.getFileName().toString().replaceAll(".txt", ""));
				try {
					String line = Files.readAllLines(path, Charset.forName("UTF-8")).get(0);
					String[] tab = line.split(";");
					p.setNom(tab[0]);
					p.setPrix(Double.valueOf(tab[1]));
					p.setCat(CategoriePizza.valueOf(tab[2]));

				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				Path fichier = Paths.get("data/"+newPizza.getCode()+".txt");
				
				Files.write(fichier, Arrays.asList(pizzaToString(newPizza)), StandardOpenOption.CREATE_NEW);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		

	}

	private String pizzaToString(Pizza newPizza) {
		String cat=newPizza.getCat().toString().toUpperCase().replaceAll(" ", "_");
		String line = newPizza.getNom()+";"+newPizza.getPrix()+";"+cat;
		return line;
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		// TODO Auto-generated method stub

	}
}
