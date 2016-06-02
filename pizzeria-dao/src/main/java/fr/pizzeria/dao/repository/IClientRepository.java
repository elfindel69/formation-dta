package fr.pizzeria.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Client;

public interface IClientRepository extends JpaRepository<Client,Integer> {
	Client findByEmailAndPassword(String email,String password);

	Client findByNom(String nom);
}
