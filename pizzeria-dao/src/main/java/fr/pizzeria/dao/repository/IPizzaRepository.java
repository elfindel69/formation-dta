package fr.pizzeria.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Pizza;

public interface IPizzaRepository extends JpaRepository<Pizza, Integer>{

	Pizza findByCode(String codePizza);

}
