package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService servicoCliente;
	@Autowired
	private ServicoService servicoServico;
	
	
	@GetMapping("listar-todos")
	public String listaClientes(ModelMap model) {
		model.addAttribute("lista", servicoCliente.buscarTodos());
		model.addAttribute("lista2", servicoServico.buscarTodos());
		return "index";
	}

	
	
}
