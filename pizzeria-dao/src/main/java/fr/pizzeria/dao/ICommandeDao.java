package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public interface ICommandeDao {

	void saveCommande(Commande cmd) throws DaoException;

	List<Commande> findAllCommandes(Client client);

}
