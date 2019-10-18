package br.com.gbsoftware.spacetattoostudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.service.CaixaService;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";

	@Autowired
	private CaixaService servicoCaixa;

	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Caixa caixa, Model model) {
		caixa = servicoCaixa.getDiaAtual();
		if (caixa == null) {
			model.addAttribute("caixaAberto", false);
		} else {
			model.addAttribute("caixaAberto", servicoCaixa.getDiaAtual().getAberto());
		}
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	@RequestMapping("pesquisar-caixa")
	public String pesquisarCaixaTeste(@RequestParam(value = "dataInicial", required = true) String dataInicial,
			@RequestParam(value = "dataFinal", required = true) String dataFinal, Model model) {

		List<Caixa> caixas = servicoCaixa.buscarPorIntervalo(dataInicial, dataFinal);

		if (caixas.isEmpty()) {
			model.addAttribute("vazio", true);
		}

		model.addAttribute("caixas", caixas);
		model.addAttribute("consultado", caixas.isEmpty() ? false : true);

		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	// TODO - MÉTODO EM ANDAMENTO
	@RequestMapping("relatorio-geral-mensal")
	public String pesquisarRelatorioGeralMes(
			@RequestParam(value = "relGeralMensal", required = true) String relGeralMensal) {

		List<Caixa> listaRelatorioGeralMensal = servicoCaixa.buscarTodosMes(relGeralMensal);

		if (!listaRelatorioGeralMensal.isEmpty()) {
			System.err.println("Quantidade de elementos na lista (DEVE SER 3)@ ----> " + listaRelatorioGeralMensal.size());
		} else {
			System.err.println("Lista Vazia ----> " + listaRelatorioGeralMensal.size());
		}

		System.err.println("testar lista" + servicoCaixa.relatorio(relGeralMensal));
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

}
