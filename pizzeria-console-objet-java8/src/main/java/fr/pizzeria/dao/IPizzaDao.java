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
	 *            pizza à ajouter
	 * 
	 */
	void savePizza(Pizza newPizza) throws DaoException;

	/**
	 * MAJ d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza à MAJ
	 * @param updatePizza
	 *            pizza modifiée
	 */
	void updatePizza(String codePizza, Pizza updatePizza) throws DaoException;

	/**
	 * Suppression d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza à supprimer
	 */
	void deletePizza(String codePizza) throws DaoException;
}
