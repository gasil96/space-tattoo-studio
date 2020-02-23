package br.com.gbsoftware.spacetattoostudio.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
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
	private static final String MODAL_FLUXO_DIARIO = "modal/modal-visualizar-fluxo-diario";
	private static final String ATUALIZAR_PAGINA = "redirect:fluxo";
	private static final String MODAL_EDITAR_ENTRADA_CAIXA = "modal/modal-editar-entrada-caixa";
	private static final String MODAL_EDITAR_SAIDA_CAIXA = "modal/modal-editar-saida-caixa";
	private static final String MODAL_VISUALIZAR_ENTRADA_CAIXA = "modal/modal-visualizar-entrada-caixa";
	private static final String MODAL_VISUALIZAR_SAIDA_CAIXA = "modal/modal-visualizar-saida-caixa";
	private static final String MODAL_EXCLUIR_SAIDA_CAIXA = "modal/modal-confirmar-exclusao-saida";
	private static final String MODAL_EXCLUIR_ENTRADA_CAIXA = "modal/modal-confirmar-exclusao-entrada";
	private static final String MSG_SUCCESS = "success";
	private static final String MSG_INFO = "info";

	@GetMapping("fluxo")
	public String caixa(Model model, EntradaCaixa entradaCaixa, SaidaCaixa saidaCaixa) {
		BigDecimal totalSaida = saidaCaixaService.sumTotalSaida().isPresent() ? saidaCaixaService.sumTotalSaida().get()
				: new BigDecimal(0);
		BigDecimal totalEntrada = entradaCaixaService.sumTotalEntrada().isPresent()
				? entradaCaixaService.sumTotalEntrada().get()
				: new BigDecimal(0);
		BigDecimal totalDiario = totalEntrada.subtract(totalSaida);

		model.addAttribute("classActiveCaixa", "active");
		model.addAttribute("totalSaidaDiario", totalSaida);
		model.addAttribute("totalEntradaDiario", totalEntrada);
		model.addAttribute("saldoTotalDiario", totalDiario);
		return PAGINA_FLUXO_CAIXA;
	}

	@GetMapping("visualizar")
	public String visualizarFluxoDiario() {
		return MODAL_FLUXO_DIARIO;
	}

	@PostMapping("salvar-entrada")
	public String salvarEntrada(RedirectAttributes attr, EntradaCaixa entradaCaixa) {
		entradaCaixaService.salvar(entradaCaixa);
		attr.addFlashAttribute(MSG_SUCCESS, "Lançamento efetuado!");
		return ATUALIZAR_PAGINA;
	}

	@PostMapping("salvar-saida")
	public String salvarSaida(RedirectAttributes attr, SaidaCaixa saidaCaixa) {
		saidaCaixaService.salvar(saidaCaixa);
		attr.addFlashAttribute(MSG_SUCCESS, "Lançamento efetuado!");
		return ATUALIZAR_PAGINA;
	}

	@GetMapping("editar-entrada/{id}")
	public String preEditarEntradaCaixa(Model model, @PathVariable("id") Long id) {
		model.addAttribute("entradaCaixa", entradaCaixaService.buscarPorId(id));
		return MODAL_EDITAR_ENTRADA_CAIXA;
	}

	@PostMapping("editar-entrada")
	public String editarEntradaCaixa(RedirectAttributes attr, EntradaCaixa entradaCaixa) {
		entradaCaixaService.salvar(entradaCaixa);
		attr.addFlashAttribute(MSG_INFO, "Lançamento alterado!");
		return MODAL_FLUXO_DIARIO;
	}

	@GetMapping("editar-saida/{id}")
	public String preEditarSaidaCaixa(Model model, @PathVariable("id") Long id) {
		model.addAttribute("saidaCaixa", saidaCaixaService.buscarPorId(id));
		return MODAL_EDITAR_SAIDA_CAIXA;
	}

	@PostMapping("editar-saida")
	public String editarSaidaCaixa(SaidaCaixa saidaCaixa, RedirectAttributes attr) {
		saidaCaixaService.salvar(saidaCaixa);
		attr.addFlashAttribute(MSG_INFO, "Lançamento alterado!");
		return MODAL_FLUXO_DIARIO;
	}

	@GetMapping("visualizar-entrada-caixa/{id}")
	public String preVisualizarEntradaCaixa(ModelMap model, @PathVariable("id") Long id) {
		model.addAttribute("entradaCaixa", entradaCaixaService.buscarPorId(id));
		return MODAL_VISUALIZAR_ENTRADA_CAIXA;
	}

	@GetMapping("visualizar-saida-caixa/{id}")
	public String preVisualizarSaidaCaixa(ModelMap model, @PathVariable("id") Long id) {
		model.addAttribute("saidaCaixa", saidaCaixaService.buscarPorId(id));
		return MODAL_VISUALIZAR_SAIDA_CAIXA;
	}

	@GetMapping("excluir-saida/{id}")
	public String preExcluirSaida(@PathVariable("id") Long id, Model model) {
		model.addAttribute("saidaCaixa", saidaCaixaService.buscarPorId(id));
		return MODAL_EXCLUIR_SAIDA_CAIXA;
	}

	@PostMapping("excluir-saida")
	public String excluirSaida(SaidaCaixa saidaCaixa, RedirectAttributes attr) {
		saidaCaixaService.deletar(saidaCaixa.getId());
		attr.addFlashAttribute(MSG_INFO, "Lançamento excluido!");
		return MODAL_FLUXO_DIARIO;
	}

	@GetMapping("excluir-entrada/{id}")
	public String preExcluirEntrada(@PathVariable("id") Long id, Model model) {
		model.addAttribute("entradaCaixa", entradaCaixaService.buscarPorId(id));
		return MODAL_EXCLUIR_ENTRADA_CAIXA;
	}

	@PostMapping("excluir-entrada")
	public String excluirEntrada(SaidaCaixa saidaCaixa, RedirectAttributes attr) {
		entradaCaixaService.deletar(saidaCaixa.getId());
		attr.addFlashAttribute(MSG_INFO, "Lançamento excluido!");
		return MODAL_FLUXO_DIARIO;
	}

	@RequestMapping(value = "saidas-do-dia", method = RequestMethod.GET)
	public @ResponseBody String getSaidasDiaria(String saidasDiaria) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return saidasDiaria = mapper.writeValueAsString(saidaCaixaService.buscarTodosDoDia());
	}

	@RequestMapping(value = "entradas-do-dia", method = RequestMethod.GET)
	public @ResponseBody String getEntradasDiaria(String entradasDiaria) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return entradasDiaria = mapper.writeValueAsString(entradaCaixaService.buscarTodosDoDia());
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
