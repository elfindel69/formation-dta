package fr.pizzeria.dao.pizza;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
@Transactional(rollbackFor = DaoException.class)
public class PizzaDaoJDBCImpl implements IPizzaDao {

	private JdbcTemplate jdbcTemplate;
	private BatchInsertPizza batchInsertPizza;

	@Autowired
	public PizzaDaoJDBCImpl(DataSource datasource, BatchInsertPizza batchInsertPizza) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.batchInsertPizza = batchInsertPizza;
	}

	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		String SQL = "select * from Pizza";
		List<Pizza> pizzas = jdbcTemplate.query(SQL, new PizzaMapper());
		return pizzas;
	}

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		String sql = "INSERT INTO pizza (nom,code,prix,categorie) VALUES (?,?,?,?)";
		this.jdbcTemplate.update(sql, newPizza.getNom(), newPizza.getCode(), newPizza.getPrix().doubleValue(), newPizza.getCat().name());
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		String sql = "UPDATE pizza SET nom =?,code=?,prix=?,categorie=? WHERE code=?";
		this.jdbcTemplate.update(sql, updatePizza.getNom(), updatePizza.getCode(), updatePizza.getPrix().doubleValue(),
				updatePizza.getCat().name(), codePizza);
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		String sql = "DELETE FROM pizza WHERE code = ?";
		this.jdbcTemplate.update(sql, codePizza);
	}

	@Override
	@Transactional
	public void importPizzas(List<Pizza> pizzas, int i) throws DaoException {
		List<List<Pizza>> partition = ListUtils.partition(pizzas, i);
		partition.forEach(batchInsertPizza::insertPizzas);
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
