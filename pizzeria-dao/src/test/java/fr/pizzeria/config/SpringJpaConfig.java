package fr.pizzeria.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"fr.pizzeria.dao","fr.pizzeria.aspects"})
@EnableJpaRepositories("fr.pizzeria.dao.repository")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringJpaConfig {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("db-schema.sql")
				.addScript("db-data.sql").build();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	@Bean
	LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean v = new LocalEntityManagerFactoryBean();
		v.setPersistenceUnitName("pizzeria-console");
		return v;

	}
	
	@Bean 
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
