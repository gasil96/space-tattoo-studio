package br.com.gbsoftware.spacetattoostudio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;
/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */
@Controller
@RequestMapping("/servico")
public class ServicoController {

	@Autowired
	private ServicoService servicoSevice;
	
	@Autowired
	private ClienteService servicoCliente;
	
	
	@GetMapping("cadastrar-servico")
	public String Cadastrar(Servico servico) {
		return "cliente/cliente";
	}
	
	
	@PostMapping("salvar-servico")
	public String salvar(@Valid Servico servico, BindingResult result, RedirectAttributes attr) {
	
		servicoSevice.salvar(servico);
		return "home";
		
	}
	
	@ModelAttribute("cliente")
	public List<Cliente> getCliente(){
		return servicoCliente.buscarTodos();
	}
	
	@ModelAttribute("tipoagendamento")
	public TipoServicoEnum[] getTipoServico() {
		return TipoServicoEnum.values();
	}
	
	@ModelAttribute("statusagendamento")
	public StatusServicoEnum[] getStatusAgendametno() {
		return StatusServicoEnum.values();
	}
	

	
}
