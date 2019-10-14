package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.service.CaixaService;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.EntradaSaidaService;
import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@Controller
@RequestMapping("financeiro")
public class Financeiro {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";
	
	@Autowired
	private UsuarioService servicoUsuario;

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private CaixaService servicoCaixa;

	@Autowired
	private EntradaSaidaService servicoEntradaSaida;
	
	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Caixa caixa, Model model) {
		caixa = servicoCaixa.getDiaAtual();
		if(caixa == null) {
			model.addAttribute("caixaAberto", false);
		}else {
			model.addAttribute("caixaAberto", servicoCaixa.getDiaAtual().getAberto());
		}
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}
	
}
