package fr.pizzeria.aspects;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.pizzeria.dao.repository.IPerformanceRepository;
import fr.pizzeria.model.Performance;

@Aspect
@Component
public class ADaoPerformance {

	private static final Logger LOG = Logger.getLogger(ADaoPerformance.class.toString());
	@Autowired
	IPerformanceRepository perfRepo;

	@Around("execution(* fr.pizzeria.dao.IPizzaDao.*(..))")
	@Transactional
	public Object profilerCreate(ProceedingJoinPoint pjp) throws Throwable {
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp dateBefore = new java.sql.Timestamp(date.getTime());
		long startTime = System.currentTimeMillis();
		LOG.log(Level.INFO, dateBefore.toString() + " " + pjp.getSignature().toString());
		
		Object result = pjp.proceed();
		long stopTime = System.currentTimeMillis();
		
		java.sql.Timestamp dateAfter = new java.sql.Timestamp(date.getTime());
		
		Performance saved = perfRepo.save(new Performance(pjp.getSignature().toString(), dateBefore, stopTime - startTime));
		LOG.log(Level.INFO, "date: "+dateAfter.toString() + " service: " + saved.getService()+" execution: "+saved.getExecution());
		return result;
	}
}
