package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * interface de manipulation des Pizzas
 * 
 * @author Valentin
 *
 */
public interface IPizzaDao {
	/**
	 * Liste des pizzas
	 * 
	 * @return liste des pizzas
	 * @throws DaoException 
	 */
	List<Pizza> findAllPizzas() throws DaoException;

	/**
	 * Ajout d'une pizza
	 * 
	 * @param newPizza
	 *            pizza � ajouter
	 * 
	 */
	void savePizza(Pizza newPizza) throws DaoException;

	/**
	 * MAJ d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza � MAJ
	 * @param updatePizza
	 *            pizza modifi�e
	 */
	void updatePizza(String codePizza, Pizza updatePizza) throws DaoException;

	/**
	 * Suppression d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza � supprimer
	 */
	void deletePizza(String codePizza) throws DaoException;
}
