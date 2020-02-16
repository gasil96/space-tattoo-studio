package br.com.gbsoftware.spacetattoostudio.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;

@Controller
@RequestMapping("caixa")
public class FluxoCaixaController {

//	@Autowired
//	private UsuarioService servicoUsuario;
//
//	@Autowired
//	private EntradaCaixaService entradaCaixaService;
//	
//	@Autowired
//	private SaidaCaixaService saidaCaixaService;
//	
//	@Autowired
//	private ClienteService servicoCliente;

	private static final String PAGINA_FLUXO_CAIXA = "caixa/fluxo-caixa";
//	private static final String MODAL_CONFIRMAR_EXCLUSAO_ENTRADA_SAIDA = "modal/modal-confimar-exclusao-entrada-saida";
//	private static final String MODAL_CONFIRMAR_FECHAMENTO_CAIXA = "modal/modal-confimar-fechamento-caixa";
//	private static final String MODAL_EDITAR_ENTRADA_SAIDA = "modal/modal-editar-entrada-saida";
//	private static final String MODAL_DETALHAMENTO_FECHAMENTO_CAIXA = "modal/modal-detalhamento-fechamento-caixa";
//	private static final String ATUALIZAR_PAGINA = "redirect:fluxo";

	@GetMapping("fluxo")
	public String caixa(Model model, EntradaCaixa entradaSaida, Cliente cliente) {
		model.addAttribute("classActiveCaixa", "active");

		//TODO - CARREGAR FLUXO CAIXA AQUI
		model.addAttribute("totalEntradaDiario", 300L);
		model.addAttribute("totalSaidaDiario", 300L);
		model.addAttribute("saldoTotalDiario", -900L);
		return PAGINA_FLUXO_CAIXA;
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
