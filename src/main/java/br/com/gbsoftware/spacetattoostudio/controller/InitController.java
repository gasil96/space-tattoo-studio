package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.service.ClienteService;

@Controller
@RequestMapping("/")
public class InitController {

	@Autowired
	private ClienteService servico;

	@GetMapping("/lista")
	public String listaClientes(ModelMap model) {
		model.addAttribute("lista", servico.buscarTodos());
		return "index";
	}

}
