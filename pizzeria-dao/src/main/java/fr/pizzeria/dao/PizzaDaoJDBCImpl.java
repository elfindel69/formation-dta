package fr.pizzeria.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.ListUtils;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCImpl implements IPizzaDao {
	private String url;
	private String password;
	private String user;
	private Map<String, Pizza> mapPizzas = new HashMap<>();

	public PizzaDaoJDBCImpl(String driver, String url2, String user2, String password2){
		try {
			Class.forName(driver);
			this.url = url2;
			this.user = user2;
			this.password = password2;
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		try {
			List<Pizza> pizzas = findAllPizzas();
			pizzas.forEach(p -> mapPizzas.put(p.getCode(), p));
		} catch (DaoException e) {
			System.err.println(e);
		}

	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);

	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {

		List<Pizza> pizzas = new ArrayList<>();
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet querySelect = statement.executeQuery("SELECT * from pizza");) {

			while (querySelect.next()) {
				pizzas.add(new Pizza(querySelect.getString("code"), querySelect.getString("nom"),
						BigDecimal.valueOf(querySelect.getDouble("prix")), CategoriePizza.valueOf(querySelect.getString("categorie"))));
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return pizzas;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		if (!mapPizzas.containsKey(newPizza.getCode())) {
			try (Connection connection = getConnection();) {
				insertPizza(connection, newPizza);

			} catch (SQLException e) {
				throw new DaoException(e);
			}
		} else {
			throw new SavePizzaException("ce code existe déjà");
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {
			try (Connection connection = getConnection();
					PreparedStatement statement = connection
							.prepareStatement("UPDATE pizza SET nom =?,code=?,prix=?,categorie=? WHERE code=?");) {
				statement.setString(1, updatePizza.getNom());
				statement.setString(2, updatePizza.getCode());
				statement.setDouble(3, updatePizza.getPrix().doubleValue());
				statement.setString(4, updatePizza.getCat().name());
				statement.setString(5, codePizza);

				int executeUpdate = statement.executeUpdate();
				if (executeUpdate != 1) {
					statement.close();
					throw new DaoException("problème de MAJ");

				} else {
					mapPizzas.put(codePizza, updatePizza);
				}

			} catch (SQLException e) {
				throw new DaoException(e);
			}
		} else {
			throw new UpdatePizzaException("code non trouvé!");
		}

	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement("DELETE FROM pizza WHERE code = ?");) {
				statement.setString(1, codePizza);
				int executeUpdate = statement.executeUpdate();
				if (executeUpdate != 1) {
					statement.close();
					throw new DaoException("problème de suppression");

				} else {
					mapPizzas.remove(codePizza);
				}
				statement.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		} else {
			throw new DeletePizzaException("code non trouvé!");
		}

	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		
		List<List<Pizza>> partition = ListUtils.partition(pizzas, i);
		try (Connection connection = getConnection();) {
			connection.setAutoCommit(false);
			insertPizzas(partition, connection);
		} catch (SQLException e1) {
			throw new SavePizzaException(e1);
		}

	}

	private void insertPizzas(List<List<Pizza>> partition, Connection connection) throws SQLException, DaoException {
		try{
			for (List<Pizza> list : partition) {
				
				for (Pizza p : list) {
					if (!mapPizzas.containsKey(p.getCode())) {
						insertPizza(connection, p);
						System.out.println("pizza créée");
					} else {
						System.out.println("pizza existante");
						connection.rollback();
					}
				}
				connection.commit();
			}
		}catch(DaoException e){
			connection.rollback();
		}
		
	}

	private void insertPizza(Connection connection, Pizza p) throws SQLException, DaoException {
		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO pizza (nom,code,prix,categorie) VALUES (?,?,?,?)");) {
			statement.setString(1, p.getNom());
			statement.setString(2, p.getCode());
			statement.setDouble(3, p.getPrix().doubleValue());
			statement.setString(4, p.getCat().name());
			int queryUpdate = statement.executeUpdate();
			if (queryUpdate != 1) {

				throw new SavePizzaException("problème d'insertion");

			} else {
				mapPizzas.put(p.getCode(), p);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	@Override
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		throw new DaoException("méthode non implémentée");
	}
}
