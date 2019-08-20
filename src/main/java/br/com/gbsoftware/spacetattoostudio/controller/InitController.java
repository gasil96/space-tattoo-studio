package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

@Controller
@RequestMapping
public class InitController {

	@GetMapping("/")
	public String home(ModelMap model, Cliente cliente) {
		model.addAttribute("classActivePrincipal","active");
		return "home";
	}
	
}
