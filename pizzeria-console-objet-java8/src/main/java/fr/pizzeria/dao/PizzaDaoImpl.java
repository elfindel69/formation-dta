package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Manipulation d'une Pizza
 * 
 * @author Valentin
 *
 */
public class PizzaDaoImpl implements IPizzaDao {
	Map<String, Pizza> pizzas = new HashMap<String, Pizza>();

	/**
	 * Constructeur initialise le tableau
	 */
	public PizzaDaoImpl() {
		pizzas.put("PEP", new Pizza("PEP", "P�p�roni", 12.50,CategoriePizza.VIANDE));
		pizzas.put("MAR", new Pizza("MAR", "Margherita", 14.00,CategoriePizza.SANS_VIANDE));
		pizzas.put("REI", new Pizza("REI", "Reine", 11.50,CategoriePizza.VIANDE));
		pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", 12.00,CategoriePizza.SANS_VIANDE));
		pizzas.put("CAN", new Pizza("CAN", "La cannibale", 12.50,CategoriePizza.VIANDE));
		pizzas.put("SAV", new Pizza("SAV", "La savoyarde", 13.00,CategoriePizza.VIANDE));
		pizzas.put("ORI", new Pizza("ORI", "L'orientale", 13.50,CategoriePizza.VIANDE));
		pizzas.put("IND", new Pizza("IND", "L'indienne", 14.00,CategoriePizza.VIANDE));
		pizzas.put("SAM", new Pizza("SAM", "La saumonetta", 14.00,CategoriePizza.POISSON));
	}

	/**
	 * Liste des pizzas
	 * 
	 * @return liste des pizzas
	 */
	@Override
	public List<Pizza> findAllPizzas() {

		return new ArrayList<Pizza>(pizzas.values());
	}

	/**
	 * Ajout d'une pizza
	 * 
	 * @param newPizza
	 *            pizza � ajouter
	 * @throws SavePizzaException
	 */
	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		if (pizzas.containsKey(newPizza.getCode())) {
			throw new SavePizzaException("code existant!");
		}
		newPizza.setId(Pizza.nbPizzas);
		++Pizza.nbPizzas;
		pizzas.put(newPizza.getCode(), newPizza);
	}

	/**
	 * MAJ d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza � MAJ
	 * @param updatePizza
	 *            pizza modifi�e
	 * @return boolean flag de modification
	 * @throws UpdatePizzaException
	 */
	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (!pizzas.containsKey(codePizza)) {
			throw new UpdatePizzaException("code non trouv�!");
		}
		pizzas.put(codePizza, updatePizza);

	}

	/**
	 * Suppression d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza � supprimer
	 * @throws DeletePizzaException
	 */
	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (!pizzas.containsKey(codePizza)) {
			throw new DeletePizzaException("code non trouv�!");
		}
		pizzas.remove(codePizza);
		Pizza.nbPizzas--;

	}

}
