package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;

public interface IClientDao {
	/**
	 * Liste des pizzas
	 * 
	 * @return liste des pizzas
	 * @throws DaoException 
	 */
	List<Client> findAllClients() throws DaoException;

	/**
	 * Ajout d'une pizza
	 * 
	 * @param newPizza
	 *            pizza à ajouter
	 * 
	 */
	void saveClient(Client newPizza) throws DaoException;

	/**
	 * MAJ d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza à MAJ
	 * @param updatePizza
	 *            pizza modifiée
	 */
	void updateClient(String codeClient, Client updateClient) throws DaoException;

	/**
	 * Suppression d'une pizza
	 * 
	 * @param codePizza
	 *            code de la Pizza à supprimer
	 */
	void deleteClient(String codeClient) throws DaoException;

	Client connect(Client newClient);
}
