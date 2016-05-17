package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Livreur;

public interface ILivreurDao {

	List<Livreur> findAddLivreurs();

	Livreur getLivreurByNom(String nom) throws DaoException;

}
