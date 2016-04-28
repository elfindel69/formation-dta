package fr.pizzeria.dao;

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
	 * @return boolean flag d'ajout
	 */
	boolean savePizza(Pizza newPizza);
	
	/**
	 * MAJ d'une pizza
	 * @param codePizza code de la Pizza à MAJ
	 * @param updatePizza pizza modifiée
	 * @return boolean flag de modification
	 */
	boolean updatePizza(String codePizza, Pizza updatePizza);
	
	/**
	 * Suppression d'une pizza
	 * @param codePizza code de la Pizza à supprimer
	 * @return boolean flag de suppression
	 */
	boolean deletePizza(String codePizza);
}
