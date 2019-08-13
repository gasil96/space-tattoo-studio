package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Testes {

	@GetMapping("/form")
	public String pages() {
		return "formulario";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
