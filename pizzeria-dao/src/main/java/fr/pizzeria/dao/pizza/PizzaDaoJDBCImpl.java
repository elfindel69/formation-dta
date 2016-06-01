package fr.pizzeria.dao.pizza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
@Transactional(rollbackFor = DaoException.class)
public class PizzaDaoJDBCImpl implements IPizzaDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PizzaDaoJDBCImpl(DataSource datasource, PlatformTransactionManager txManager) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	

	private Map<String, Pizza> mapPizzas = new HashMap<>();

	@PostConstruct
	void init() {
		try {
			List<Pizza> pizzas = findAllPizzas();
			pizzas.forEach(p -> mapPizzas.put(p.getCode(), p));
		} catch (DaoException e) {
			System.err.println(e);
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		String SQL = "select * from Pizza";
		List<Pizza> pizzas = jdbcTemplate.query(SQL, new PizzaMapper());
		return pizzas;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		if (!mapPizzas.containsKey(newPizza.getCode())) {
			insertPizza(newPizza);
		} else {
			throw new SavePizzaException("ce code existe déjà");
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {

			String sql = "UPDATE pizza SET nom =?,code=?,prix=?,categorie=? WHERE code=?";
			this.jdbcTemplate.update(sql, updatePizza.getNom(), updatePizza.getCode(),
					updatePizza.getPrix().doubleValue(), updatePizza.getCat().name(), codePizza);
			mapPizzas.put(codePizza, updatePizza);
		} else {
			throw new UpdatePizzaException("code non trouvé!");
		}

	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (mapPizzas.containsKey(codePizza)) {

			String sql = "DELETE FROM pizza WHERE code = ?";
			this.jdbcTemplate.update(sql, codePizza);
			mapPizzas.remove(codePizza);
		} else {
			throw new DeletePizzaException("code non trouvé!");
		}

	}

	@Override
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {

		List<List<Pizza>> partition = ListUtils.partition(pizzas, i);

		insertPizzas(partition);

	}

	private void insertPizzas(List<List<Pizza>> partition) throws UpdatePizzaException {
		for (List<Pizza> list : partition) {

			for (Pizza p : list) {
				if (!mapPizzas.containsKey(p.getCode())) {
					insertPizza(p);
					System.out.println("pizza créée");
				} else {
					throw new UpdatePizzaException("pizza existante!");
				}
			}
		}

	}

	private void insertPizza(Pizza p) {
		String sql = "INSERT INTO pizza (nom,code,prix,categorie) VALUES (?,?,?,?)";
		this.jdbcTemplate.update(sql, p.getNom(), p.getCode(), p.getPrix().doubleValue(), p.getCat().name());
		mapPizzas.put(p.getCode(), p);
	}

	@Override
	public Set<Pizza> findPizzasByCode(List<String> codes) throws DaoException {
		throw new DaoException("méthode non implémentée");
	}

	@Override
	public Pizza findPizzaByCode(String code) {
		String sql = "SELECT * FROM PIZZA WHERE code=?";

		return this.jdbcTemplate.queryForObject(sql, new PizzaMapper(), code);
	}

}
