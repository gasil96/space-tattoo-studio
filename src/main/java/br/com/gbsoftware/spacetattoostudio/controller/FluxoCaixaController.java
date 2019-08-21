package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("caixa")
public class FluxoCaixaController {

	private static final String PAGINA_INICIAL = "home";
	private static final String PAGINA_FLUXO_CAIXA = "caixa/fluxo-caixa";
	private static final String PAGINA_FINANCEIRO_DETALHADO = "detalhamento/financeiro-detalhado";
	
	@GetMapping("fluxo")
	public String caixa(Model model) {
		model.addAttribute("classActiveCaixa", "active");
		return PAGINA_FLUXO_CAIXA;
	}
	
	@GetMapping("detalhamento")
	public String detalhamento(Model model) {
		model.addAttribute("");
		return PAGINA_FINANCEIRO_DETALHADO;
	}
	
	
}
