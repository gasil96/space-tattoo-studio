package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";
//	private static final String MODAL_DETALHAMENTO_MENSAL = "modal/modal-detalhamento-financeiro-mensal";

//	@Autowired
//	private CaixaService servicoCaixa;
//
//	@Autowired
//	private EntradaCaixaService servicoEntradaSaida;

	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Caixa caixa, Model model) {

		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}


}
