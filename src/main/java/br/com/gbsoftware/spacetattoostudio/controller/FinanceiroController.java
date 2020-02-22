package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.gbsoftware.spacetattoostudio.service.VwArrecadacaoAnualTipoService;

@Controller
@RequestMapping("financeiro")
public class FinanceiroController {

	private static final String PAGINA_DETALHAMENTO_FINANCEIRO = "detalhamento/financeiro-detalhado";

	@Autowired
	private VwArrecadacaoAnualTipoService vwArrecadacaoService;
	
	@GetMapping("detalhamento")
	public String detalhamentoFinanceiro(Model model) {
		return PAGINA_DETALHAMENTO_FINANCEIRO;
	}

	
	@RequestMapping(value = "/vw-arrecadacao-anual", method = RequestMethod.GET)
	public @ResponseBody String arrecadacaoAnual(String arrecadacaoAnual) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return arrecadacaoAnual = mapper.writeValueAsString(vwArrecadacaoService.buscarTodos());
	}
	
	
}
