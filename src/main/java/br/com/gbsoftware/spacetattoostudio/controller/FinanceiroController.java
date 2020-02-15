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
//	private static final String MODAL_DETALHAMENTO_MENSAL = "modal/modal-detalhamento-financeiro-mensal";

//	@Autowired
//	private CaixaService servicoCaixa;
//
//	@Autowired
//	private EntradaCaixaService servicoEntradaSaida;

	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Model model, Cliente cliente) {

		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	// TODO - VERIFICAR A NECESSIDADE DESTE DOIS MÉTODOS ( SE FOREM PERMANECER,
	// DIMINUIR A COMPLEXIDADE DE CADA E MELHROAR PERFORMACE)

//	@PostMapping("credito")
//	public String credito(@Valid Cliente cliente, RedirectAttributes attr) {
//		Cliente clienteLocalizado = servicoCliente.buscarPorId(cliente.getId()).orElse(new Cliente());
//		Long idCliente = cliente.getId();
//		if (clienteLocalizado.getCreditoCliente() == null) {
//			servicoCliente.updateCredito(cliente.getCreditoCliente(), idCliente);
//			attr.addFlashAttribute("creditoAdicionado", true);
//		} else {
//			BigDecimal valorCredito = cliente.getCreditoCliente().add(clienteLocalizado.getCreditoCliente());
//			servicoCliente.updateCredito(valorCredito, idCliente);
//			attr.addFlashAttribute("creditoAdicionado", true);
//		}
//		return ATUALIZAR_PAGINA;
//	}
//
//	@PostMapping("remover-credito")
//	public String removerCredito(@Valid Cliente cliente, RedirectAttributes attr) {
//		Cliente clienteLocalizado = servicoCliente.buscarPorId(cliente.getId()).orElse(new Cliente());
//		Long idCliente = cliente.getId();
//		if (clienteLocalizado.getCreditoCliente() == null) {
//			servicoCliente.updateCredito(new BigDecimal(0).subtract(cliente.getCreditoCliente()), idCliente);
//			attr.addFlashAttribute("creditoRemovido", true);
//		} else {
//			BigDecimal valorCredito = clienteLocalizado.getCreditoCliente().subtract(cliente.getCreditoCliente());
//			servicoCliente.updateCredito(valorCredito, idCliente);
//			attr.addFlashAttribute("creditoRemovido", true);
//		}
//		return ATUALIZAR_PAGINA;
//	}

}
