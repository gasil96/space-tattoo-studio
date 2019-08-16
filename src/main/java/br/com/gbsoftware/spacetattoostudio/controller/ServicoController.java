package br.com.gbsoftware.spacetattoostudio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping("servico")
public class ServicoController {

	@Autowired
	private ServicoService servicoSevice;
	
	
	@GetMapping("cadastrar")
	public String Cadastrar(Servico servico) {
		return "servico";
	}
	
	
	@PostMapping("salvar")
	public String salvar(@Valid Servico servico, BindingResult result, RedirectAttributes attr) {
	
		servicoSevice.salvar(servico);
		return "index";
		
	}
	
}
