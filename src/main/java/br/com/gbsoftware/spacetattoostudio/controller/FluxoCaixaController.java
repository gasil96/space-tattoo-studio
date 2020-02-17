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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private static final String ATUALIZAR_PAGINA = "redirect:fluxo";

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

	@PostMapping("salvar-entrada")
	public String salvarEntrada(Model model, EntradaCaixa entradaCaixa) {
		entradaCaixaService.salvar(entradaCaixa);
		return ATUALIZAR_PAGINA;
	}

	@PostMapping("salvar-saida")
	public String salvarSaida(Model model, SaidaCaixa saidaCaixa) {
		saidaCaixaService.salvar(saidaCaixa);
		return ATUALIZAR_PAGINA;
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
