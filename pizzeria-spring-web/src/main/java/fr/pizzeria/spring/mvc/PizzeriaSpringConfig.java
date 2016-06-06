package fr.pizzeria.spring.mvc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan({ "fr.pizzeria.spring.mvc.controllers","fr.pizzeria.aspects"})
@EnableJpaRepositories("fr.pizzeria.dao.repository")
@EnableAspectJAutoProxy
public class PizzeriaSpringConfig {

	public PizzeriaSpringConfig() {
		super();
		System.out.println("hhhhh hhhhh hhhh");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean 
	public IPizzaDao pizzaDao(){
		return new PizzaDaoImpl();
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean v = new LocalContainerEntityManagerFactoryBean();
		v.setPersistenceUnitName("pizzeria-db");
		return v;

	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("db-schema.sql")
				.addScript("db-data.sql").build();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
