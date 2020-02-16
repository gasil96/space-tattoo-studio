package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;
import br.com.gbsoftware.spacetattoostudio.service.EntradaCaixaService;
import br.com.gbsoftware.spacetattoostudio.service.SaidaCaixaService;

@Controller
@RequestMapping("caixa")
public class FluxoCaixaController {

	@Autowired
	private EntradaCaixaService entradaCaixaService;
	
	@Autowired
	private SaidaCaixaService saidaCaixaService;

	private static final String PAGINA_FLUXO_CAIXA = "caixa/fluxo-caixa";
	private static final String ATUALIZAR_PAGINA = "redirect:fluxo";

	@GetMapping("fluxo")
	public String caixa(Model model, EntradaCaixa entradaSaida, Cliente cliente) {
		model.addAttribute("classActiveCaixa", "active");

		model.addAttribute("totalEntradaDiario", 300L); // TODO CALCULO DE TODA ENTRADA DO DIA
		model.addAttribute("totalSaidaDiario", 300L); // TODO - CALCULO DE TODA SAIDA DO DIA
		model.addAttribute("saldoTotalDiario", -900L); //TODO - CALCULO DO VALOR QUE SE DEVE TER EM CAIXA NO DIA
		return PAGINA_FLUXO_CAIXA;
	}
	
	@PostMapping("salvar-entrada")
	public String salvarEntrada(Model model, EntradaCaixa entradaCaixa) {
		System.err.println(entradaCaixa.toString());		
		entradaCaixaService.salvar(entradaCaixa);
		return ATUALIZAR_PAGINA;
	}
	
	@PostMapping("salvar-saida")
	public String salvarSaida(Model model, SaidaCaixa saidaCaixa) {
		saidaCaixaService.salvar(saidaCaixa);
		return ATUALIZAR_PAGINA;
	}
	
	@ModelAttribute("formapagamento")
	public FormaPagamentoEnum[] getFormaPagamento() {
		return FormaPagamentoEnum.values();
	}

	@ModelAttribute("tiposervico")
	public TipoServicoEnum[] getServicos() {
		return TipoServicoEnum.values();
	}

	@ModelAttribute("tipooperacao")
	public TipoOperacaoEnum[] getOperacao() {
		return TipoOperacaoEnum.values();
	}
	
}
