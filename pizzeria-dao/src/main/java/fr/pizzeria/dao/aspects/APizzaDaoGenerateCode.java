package fr.pizzeria.dao.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Pizza;

@Aspect
@Component
public class APizzaDaoGenerateCode {
	private static final Logger LOG = Logger.getLogger(APizzaDaoGenerateCode.class.toString());

	@Before("execution(* fr.pizzeria.dao.IPizzaDao.savePizza(..))")
	void generateCode(JoinPoint jp) {
		Pizza pizza = (Pizza) jp.getArgs()[0];
		if(pizza.getCode()==null){
			String nom = pizza.getNom();
			pizza.setCode(nom.substring(0, 3));
			LOG.log(Level.INFO, pizza.getCode());
		}
		
	}
}
