package fr.pizzeria.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Livreur;

public interface ILivreurRepository extends JpaRepository<Livreur,Integer> {

	Livreur findByNom(String nom);

}
