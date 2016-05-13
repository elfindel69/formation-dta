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
	Map<String, Pizza> pizzas = new HashMap<>();

	/**
	 * Constructeur initialise le tableau
	 */
	public PizzaDaoImpl() {
		pizzas.put("PEP", new Pizza("PEP", "Pépéroni", 12.50,CategoriePizza.VIANDE));
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

		return new ArrayList<>(pizzas.values());
	}

	/**
	 * Ajout d'une pizza
	 * 
	 * @param newPizza
	 *            pizza à ajouter
	 * @throws SavePizzaException
	 */
	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		if (pizzas.containsKey(newPizza.getCode())) {
			throw new SavePizzaException("code existant!");
		}
		newPizza.setId(Pizza.getNbPizzas());
		Pizza.setNbPizzas(Pizza.getNbPizzas() + 1);
		pizzas.put(newPizza.getCode(), newPizza);
	}

	/**
	 * MAJ d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza à MAJ
	 * @param updatePizza
	 *            pizza modifiée
	 * @return boolean flag de modification
	 * @throws UpdatePizzaException
	 */
	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (!pizzas.containsKey(codePizza)) {
			throw new UpdatePizzaException("code non trouvé!");
		}
		pizzas.put(codePizza, updatePizza);

	}

	/**
	 * Suppression d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza à supprimer
	 * @throws DeletePizzaException
	 */
	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (!pizzas.containsKey(codePizza)) {
			throw new DeletePizzaException("code non trouvé!");
		}
		pizzas.remove(codePizza);
		Pizza.setNbPizzas(Pizza.getNbPizzas() - 1);

	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	

}
