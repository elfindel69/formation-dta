package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

/**
 * Manipulation d'une Pizza 
 * @author Valentin
 *
 */
public class PizzaDaoImpl implements IPizzaDao {
	Pizza[] pizzas = new Pizza[100];

	/**
	 * Constructeur initialise le tableau
	 */
	public PizzaDaoImpl() {
		pizzas[0] = new Pizza("PEP", "Pépéroni", 12.50);
		pizzas[1] = new Pizza("MAR", "Margherita", 14.00);
		pizzas[2] = new Pizza("REI", "Reine", 11.50);
		pizzas[3] = new Pizza("FRO", "La 4 fromages", 12.00);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.50);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13.00);
		pizzas[6] = new Pizza("ORI", "L'orientale", 13.50);
		pizzas[7] = new Pizza("IND", "L'indienne", 14.00);
	}

	/**
	 * Liste des pizzas 
	 * @return liste des pizzas
	 */
	@Override
	public Pizza[] findAllPizzas() {
		Pizza[] result = new Pizza[100];
		System.arraycopy(pizzas, 0, result, 0, result.length);
		return result;
	}

	/**
	 * Ajout d'une pizza
	 * @param newPizza pizza à ajouter
	 * @return boolean flag d'ajout
	 */
	@Override
	public boolean savePizza(Pizza newPizza) {
		int pizzToAdd = -1;
		boolean placeTrouvee = false;
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i] == null) {
				pizzToAdd = i;
				placeTrouvee = true;
				break;
			}
		}
		if (placeTrouvee) {
			pizzas[pizzToAdd] = newPizza;
		}
		return placeTrouvee;
	}

	/**
	 * MAJ d'une pizza
	 * @param codePizza code de la Pizza à MAJ
	 * @param updatePizza pizza modifiée
	 * @return boolean flag de modification
	 */
	@Override
	public boolean updatePizza(String codePizza, Pizza updatePizza) {
		boolean result = false;
		if (codePizza != "99") {
			int index = rechercheIndexByCode(codePizza, pizzas);
			if (index != -1) {
				pizzas[index] = updatePizza;
				result = true;
			}
		}
		return result;
	}

	/**
	 * Suppression d'une pizza
	 * @param codePizza code de la Pizza à supprimer
	 * @return boolean flag de suppression
	 */
	@Override
	public boolean deletePizza(String codePizza) {
		boolean result = false;

		int index = rechercheIndexByCode(codePizza, pizzas);
		if (index != -1) {
			pizzas[index] = null;
			--Pizza.nbPizzas;
			result = true;
		}
		return result;

	}

	/**
	 * Recherche l'index de la Pizza à partir de son code
	 * 
	 * @param code
	 *            code à rechercher
	 * @param pizzas
	 *            liste cible
	 * @return int index de la pizza, -1 si erreur
	 */
	private int rechercheIndexByCode(String code, Pizza[] pizzas) {
		int index = -1;
		for (int i = 0; i < pizzas.length; i++) {
			if (pizzas[i] != null && pizzas[i].getCode().equals(code)) {
				index = i;
				break;
			}
		}
		return index;
	}
}
