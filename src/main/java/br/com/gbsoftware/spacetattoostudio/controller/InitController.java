package br.com.gbsoftware.spacetattoostudio.controller;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping
public class InitController {

	private static final String PAGINA_INICIAL = "home";

	@Autowired
	private ClienteService servicoCliente;
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/")
	public String home(ModelMap model, Cliente cliente, Servico servico) {
		model.addAttribute("classActivePrincipal", "active");
		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		List<Servico> totalServico = servicoService.buscarTodos();
		model.addAttribute("totalClientes", totalClientes.size());
		model.addAttribute("totalAgendamentos", totalServico.size());
		return PAGINA_INICIAL;
	}
	
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	
	@GetMapping("/home")
	public String dashboard(ModelMap model, Cliente cliente) {
		model.addAttribute("classActivePrincipal", "active");
		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		model.addAttribute("totalClientes", totalClientes.size());
		return PAGINA_INICIAL;
	}
	
	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}
}
