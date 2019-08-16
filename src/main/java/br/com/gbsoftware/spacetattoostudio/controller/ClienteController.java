package br.com.gbsoftware.spacetattoostudio.controller;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteService servicoCliente;
	@Autowired
	private ServicoService servicoServico;


	@GetMapping("cadastrar")
	public String Cadastrar(Cliente cliente) {
		return "cliente";
	}
	
	@GetMapping("excluir-cliente/{id}")
	public String excluir(@PathVariable("id")Long id, RedirectAttributes attr) {
		servicoCliente.excluir(id);
		return "index";
	}
	
	@GetMapping("listar-todos")
	public String listaClientes(ModelMap model,Long id) {
		id = (long) 3;
		model.addAttribute("listaCliente", servicoCliente.buscarTodos());
		model.addAttribute("listaServico", servicoServico.buscarTodos());
		model.addAttribute("lista", servicoCliente.buscarPorId(id).orElseThrow(()-> new EntityNotFoundException())); // TODO - Faltando ajustar
		
		return "index";
	}
	
	
	
	
	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
	
		servicoCliente.salvar(cliente);
		return "index";
		
	}
	
	
}
