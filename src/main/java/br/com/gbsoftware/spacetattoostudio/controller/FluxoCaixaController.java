package br.com.gbsoftware.spacetattoostudio.controller;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("caixa")
public class FluxoCaixaController {

	private static final String PAGINA_FLUXO_CAIXA = "caixa/fluxo-caixa";
	private static final String PAGINA_FINANCEIRO_DETALHADO = "detalhamento/financeiro-detalhado";
	
	@GetMapping("fluxo")
	public String caixa(Model model) {
		model.addAttribute("classActiveCaixa", "active");
		return PAGINA_FLUXO_CAIXA;
	}
	
	@GetMapping("detalhamento")
	public String detalhamento(Model model) {
		model.addAttribute("classActiveSubFinanceiro", "active");
		return PAGINA_FINANCEIRO_DETALHADO;
	}
}
