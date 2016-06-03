package fr.pizzeria.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.factory.DaoFactoryGenericImpl;
import fr.pizzeria.factory.IDaoFactory;

@Configuration
@ComponentScan({"fr.pizzeria.ihm","fr.pizzeria.dao"})
@EnableJpaRepositories("fr.pizzeria.dao.repository")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppSpringConfig {
	@Bean
	Scanner sc() {
		return new Scanner(System.in);
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
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
	LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean v = new LocalEntityManagerFactoryBean();
		v.setPersistenceUnitName("pizzeria-console");
		return v;

	}

	@Bean
	IDaoFactory daoFactory(@Qualifier("pizzaDaoJPARepoImpl") IPizzaDao pizzaDao) {
		return new DaoFactoryGenericImpl(pizzaDao, null, null);
	}
}
