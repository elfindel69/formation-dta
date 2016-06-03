package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Performance;

public interface IPerformanceDao {
	/**
	 * Liste des pizzas
	 * 
	 * @return liste des pizzas
	 * @throws DaoException 
	 */
	List<Performance> findAllPerformances() throws DaoException;

	/**
	 * Ajout d'une pizza
	 * 
	 * @param newPizza
	 *            pizza à ajouter
	 * 
	 */
	void savePerformance(Performance newPizza) throws DaoException;

	/**
	 * MAJ d'un client
	 * 
	 * @param idPerformance
	 *          id du Performance à MAJ
	 * @param updatePerformance
	 *            client modifié
	 */
	void updatePerformance(int idPerformance, Performance updatePerformance) throws DaoException;

	/**
	 * Suppression d'un client
	 * 
	 * @param codePizza
	 *            id du Performance à supprimer
	 */
	void deletePerformance(int idPerformance) throws DaoException;

}
