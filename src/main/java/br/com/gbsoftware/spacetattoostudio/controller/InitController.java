package br.com.gbsoftware.spacetattoostudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;

@Controller
@RequestMapping
public class InitController {

	private static final String PAGINA_INICIAL = "home";
	private static final String __ADMINISTRACAO = "admin/admin";

	@Autowired
	private ClienteService servicoCliente;

	@GetMapping("/")
	public String home(ModelMap model, Cliente cliente) {
		model.addAttribute("classActivePrincipal", "active");
		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		model.addAttribute("totalClientes", totalClientes.size());

		return PAGINA_INICIAL;
	}

	@GetMapping("/home")
	public String dashboard(ModelMap model, Cliente cliente) {
		model.addAttribute("classActivePrincipal", "active");
		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		model.addAttribute("totalClientes", totalClientes.size());
		return PAGINA_INICIAL;
	}

	@GetMapping("admin")
	public String admin(ModelMap model, Cliente cliente, Servico servico) {
		model.addAttribute("AdminAtivo", "active");
		return __ADMINISTRACAO;
	}

}
