package fr.pizzeria.dao.client;

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
	 * MAJ d'un client
	 * 
	 * @param idClient
	 *          id du Client à MAJ
	 * @param updateClient
	 *            client modifié
	 */
	void updateClient(int idClient, Client updateClient) throws DaoException;

	/**
	 * Suppression d'un client
	 * 
	 * @param codePizza
	 *            id du Client à supprimer
	 */
	void deleteClient(int idClient) throws DaoException;

	Client connect(String email, String password);

	fr.pizzeria.model.Client findByNom(String string);

	Client findOne(int i);

	
}
