package fr.pizzeria.spring.mvc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.pizzeria.dao.repository.IPerformanceRepository;
import fr.pizzeria.model.Performance;

@Controller
@RequestMapping(path = "/performance")
public class PerformanceController {

	@Autowired
	IPerformanceRepository perfRepo;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView perfomance(HttpServletResponse response) {

		List<Performance> perfs = perfRepo.findAll();

		ModelAndView mav = new ModelAndView();

		mav.setViewName("performance");

		mav.addObject("performance", perfs);

		return mav;

	}
}
