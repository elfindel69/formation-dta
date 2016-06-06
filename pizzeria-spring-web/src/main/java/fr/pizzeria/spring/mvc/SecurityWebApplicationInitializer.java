package fr.pizzeria.spring.mvc;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
		super(PizzeriaSpringSecurityConfig.class);
	}

}
