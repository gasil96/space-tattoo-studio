package br.com.gbsoftware.spacetattoostudio.controller;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.gbsoftware.spacetattoostudio.domain.enums.CategoriaEntradaEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@Controller
@RequestMapping("caixa")
public class FluxoCaixaController {

	@Autowired
	private UsuarioService servicoUsuario;
	
	@Autowired 
	private ClienteService servicoCliente;
	
	private static final String PAGINA_FLUXO_CAIXA = "caixa/fluxo-caixa";
	private static final String ATUALIZAR_PAGINA = "redirect:fluxo";
	@GetMapping("fluxo")
	public String caixa(Model model, Caixa caixa, EntradaSaida entradaSaida) {
		model.addAttribute("classActiveCaixa", "active");
//		model.addAttribute("cliente", servicoCliente.getClienteIdInstaNome());
		return PAGINA_FLUXO_CAIXA;
	}
	
	@PostMapping("/adicionar")
	public String salvarEntradaOuSaida(EntradaSaida entradaSaida, Caixa caixa, Cliente cliente) {
		entradaSaida.setHorarioOperacao(LocalDateTime.now());
		System.err.println(entradaSaida.toString()); // TODO - REMOVER TOSTRING
		return ATUALIZAR_PAGINA;
	}
	
	@PostMapping("/abrir-caixa")
	public String abriCaixa(EntradaSaida entradaSaida, Caixa caixa, Cliente cliente) {
		
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
		
		caixa.setDataHoraAbertura(LocalDateTime.now());
		caixa.setOperadorAbertura(usuarioLogadoNome);
		
		
		
		entradaSaida.setHorarioOperacao(LocalDateTime.now());
		entradaSaida.setDescricao("VALOR_INICIAL_CAIXA");
		entradaSaida.setDesconto(null);
		entradaSaida.setFormaPagamento(FormaPagamentoEnum.AVISTA);
		entradaSaida.setTipoOperacao(TipoOperacaoEnum.ENTRADA);
		entradaSaida.setCategoriaEntrada(CategoriaEntradaEnum.DIVERSOS);
		System.err.println(entradaSaida.toString()); // TODO - REMOVER TOSTRING
		System.err.println(caixa.toString()); // TODO - REMOVER TOSTRING
		return ATUALIZAR_PAGINA;
	}
	
	@PostMapping("/fechar-caixa")
	public String fecharCaixa(EntradaSaida entradaSaida, Caixa caixa, Cliente cliente) {
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
		
		BigDecimal big1 = new BigDecimal("0.1"); // TODO - REMOVER APOS CONCLUSAO METODO

			// TODO - LOCALIZAR CAIXA ABERTO; 
		
			caixa.setDataHoraFechamento(LocalDateTime.now());
			caixa.setOperadorFechamento(usuarioLogadoNome);
			
			// TODO - CALCULADO ENTRADA E SAIDA
			// TODO - CALCULO DEBITO/CREDITO/AVISTA
			
			caixa.setTotal(big1); // TODO - AQUI VAI O TOTAL CALCULADO AO FECHAR CAIXA
			System.err.println(caixa.toString()); // TODO - REMOVER TOSTRING
		return ATUALIZAR_PAGINA;
	}

	@RequestMapping(value = "/input-clientes")
	public @ResponseBody String getInputClientes(HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		List<Cliente> listaClientesInput = servicoCliente.buscarTodos();
		String listaClientesInputJson = mapper.writeValueAsString(listaClientesInput);
		return listaClientesInputJson;
	}
	
	@ModelAttribute("formapagamento")
	public FormaPagamentoEnum[] getFormaPagamento() {
		return FormaPagamentoEnum.values();
	}
	
	@ModelAttribute("categoriaentrada")
	public CategoriaEntradaEnum[] getCategoriaEntrada() {
		return CategoriaEntradaEnum.values();
	}
	
	@ModelAttribute("tipooperacao")
	public TipoOperacaoEnum[] getOperacao() {
		return TipoOperacaoEnum.values();
	}
	
	
}
