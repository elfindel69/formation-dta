package fr.pizzeria.spring.mvc.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter("/mvc/*")
public class EncodingFilter extends CharacterEncodingFilter {

    public EncodingFilter() {
        super("UTF-8", true);
    }
}
