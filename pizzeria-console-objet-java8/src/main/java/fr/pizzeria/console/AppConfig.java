package fr.pizzeria.console;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.factory.DaoFactoryGenericImpl;
import fr.pizzeria.factory.IDaoFactory;

@Configuration
@ComponentScan("fr.pizzeria")
@EnableTransactionManagement
public class AppConfig {

	private String url;
	private String user;
	private String password;

	@Bean
	Scanner sc() {
		return new Scanner(System.in);
	}

	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource(url, user, password));
	}

	@Bean
	DriverManagerDataSource dataSource(@Value("${jdbc.url}") String url, @Value("${jdbc.user}") String user,
			@Value("${jdbc.password}") String password) {

		return new DriverManagerDataSource(url, user, password);

	}

	
	
	@Bean
	public PropertyPlaceholderConfigurer props() {
		PropertyPlaceholderConfigurer co = new PropertyPlaceholderConfigurer();
		co.setLocation(new ClassPathResource("jdbc.properties"));
		return co;
	}

	@Bean
	EntityManagerFactory entityFacto() {
		return Persistence.createEntityManagerFactory("pizzeria-console");
	}

	@Bean
	IDaoFactory daoFactory(@Qualifier("pizzaDaoJDBCImpl") IPizzaDao pizzaDao) {
		return new DaoFactoryGenericImpl(pizzaDao,null,null);
	}

}
