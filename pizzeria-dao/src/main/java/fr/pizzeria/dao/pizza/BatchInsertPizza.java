package fr.pizzeria.dao.pizza;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class BatchInsertPizza {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BatchInsertPizza(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertPizzas(List<Pizza> list) {
		list.forEach(this::insertPizza);
	}

	private void insertPizza(Pizza p) {
		String sql = "INSERT INTO pizza (nom,code,prix,categorie) VALUES (?,?,?,?)";
		this.jdbcTemplate.update(sql, p.getNom(), p.getCode(), p.getPrix().doubleValue(), p.getCat().name());
	}
}
