package fr.pizzeria.spring.mvc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.pizzeria.dao.IPerformanceDao;
import fr.pizzeria.exceptions.DaoException;
import fr.pizzeria.model.Performance;

@Controller
@RequestMapping(path = "/performance")
public class PerformanceController {

	private static final Logger LOG = Logger.getLogger(PerformanceController.class.toString());
	@Autowired
	@Qualifier("performanceDaoJPARepoImpl")
	IPerformanceDao perfRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView perfomance(HttpServletResponse response) {

		List<Performance> perfs = new ArrayList<>();
		try {
			perfs = perfRepo.findAllPerformances();
		} catch (DaoException e) {
			response.setStatus(400);
			LOG.log(Level.SEVERE, "problème de sauvegarde", e);
		}

		ModelAndView mav = new ModelAndView();

		mav.setViewName("performance");

		mav.addObject("performance", perfs);

		return mav;

	}
}
