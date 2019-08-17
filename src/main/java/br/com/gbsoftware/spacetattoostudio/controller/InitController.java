package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

	@GetMapping("/")
	public String home(ModelMap model) {
		return "home";
	}


}
