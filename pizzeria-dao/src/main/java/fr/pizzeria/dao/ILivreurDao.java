package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Livreur;

public interface ILivreurDao {
	/**
	 * Liste des pizzas
	 * 
	 * @return liste des pizzas
	 * @throws DaoException 
	 */
	List<Livreur> findAllLivreurs() throws DaoException;

	/**
	 * Ajout d'une pizza
	 * 
	 * @param newPizza
	 *            pizza à ajouter
	 * 
	 */
	void saveLivreur(Livreur newPizza) throws DaoException;

	/**
	 * MAJ d'un Livreur
	 * 
	 * @param idLivreur
	 *          id du Livreur à MAJ
	 * @param updateLivreur
	 *            Livreur modifié
	 */
	void updateLivreur(int idLivreur, Livreur updateLivreur) throws DaoException;

	/**
	 * Suppression d'un Livreur
	 * 
	 * @param codePizza
	 *            id du Livreur à supprimer
	 */
	void deleteLivreur(int idLivreur) throws DaoException;


	Livreur findByNom(String string);

	Livreur findOne(int i);

	
}
