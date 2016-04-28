package fr.pizzeria.dao;

import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
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
	 * @throws SavePizzaException 
	 */
	@Override
	public void savePizza(Pizza newPizza) throws SavePizzaException {
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
		}else{
			throw new SavePizzaException("Erreur d'ajout, plus de place disponible");
		}
	}

	/**
	 * MAJ d'une pizza
	 * @param codePizza code de la Pizza à MAJ
	 * @param updatePizza pizza modifiée
	 * @return boolean flag de modification
	 * @throws UpdatePizzaException 
	 */
	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws UpdatePizzaException {
		
		
		int index = rechercheIndexByCode(codePizza, pizzas);
		if (index != -1) {
			pizzas[index] = updatePizza;
		}else{
			throw new UpdatePizzaException("Erreur de mise à jour, la pizza "+codePizza+" n'existe pas");
		}
		
		
	}

	/**
	 * Suppression d'une pizza
	 * @param codePizza code de la Pizza à supprimer
	 * @throws DeletePizzaException 
	 */
	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		int index = rechercheIndexByCode(codePizza, pizzas);
		if (index != -1) {
			pizzas[index] = null;
			--Pizza.nbPizzas;
		}else{
			throw new DeletePizzaException("Erreur de suppression, la pizza "+codePizza+" n'existe pas");
		}
		

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
