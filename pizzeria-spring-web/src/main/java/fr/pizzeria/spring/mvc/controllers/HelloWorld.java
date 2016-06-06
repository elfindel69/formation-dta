package fr.pizzeria.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/helloWorld")
public class HelloWorld {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView helloWorld() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("helloWorld");

		mav.addObject("message", "Hello World!");

		return mav;

	}
}
