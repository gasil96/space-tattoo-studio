package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";

	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Model model, Cliente cliente) {

		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

}
