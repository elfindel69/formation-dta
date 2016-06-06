package fr.pizzeria.spring.mvc.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.pizzeria.dao.repository.IPerformanceRepository;
import fr.pizzeria.model.Performance;

@Controller
@RequestMapping(path = "/performance")
public class PerformanceController {

	@Autowired
	IPerformanceRepository perfRepo;

	@RequestMapping(method = RequestMethod.GET)
	public String perfomance(Model model) {
		List<Performance> perfs = perfRepo.findAll();
		model.addAttribute("performance", perfs);
		return "performance";

	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String delete(@RequestParam("id") Integer performanceId, Model model) {
		perfRepo.delete(performanceId);
		model.addAttribute("msg", "Performance supprimée");
		return "redirect:/mvc/performance";
	}

	@RequestMapping(path = "/deleteall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String delete(Model model) throws UnsupportedEncodingException {
		perfRepo.deleteAll();
		model.addAttribute("msg", "Toutes les données sont supprimées");
		return "redirect:/mvc/performance";
	}
}
