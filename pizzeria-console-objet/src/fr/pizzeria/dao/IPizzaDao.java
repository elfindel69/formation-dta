package fr.pizzeria.dao;

import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * interface de manipulation des Pizzas
 * @author Valentin
 *
 */
public interface IPizzaDao {
	/**
	 * Liste des pizzas 
	 * @return liste des pizzas
	 */
	Pizza[] findAllPizzas();
	
	/**
	 * Ajout d'une pizza
	 * @param newPizza pizza à ajouter
	 * 
	 */
	void savePizza(Pizza newPizza) throws SavePizzaException;
	
	/**
	 * MAJ d'une pizza
	 * @param codePizza code de la Pizza à MAJ
	 * @param updatePizza pizza modifiée
	 */
	void updatePizza(String codePizza, Pizza updatePizza) throws UpdatePizzaException;
	
	/**
	 * Suppression d'une pizza
	 * @param codePizza code de la Pizza à supprimer
	 */
	void deletePizza(String codePizza) throws DeletePizzaException;
}
