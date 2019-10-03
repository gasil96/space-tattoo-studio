package br.com.gbsoftware.spacetattoostudio.controller;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import br.com.gbsoftware.spacetattoostudio.service.CaixaService;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.EntradaSaidaService;
import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@Controller
@RequestMapping("caixa")
public class FluxoCaixaController {

	@Autowired
	private UsuarioService servicoUsuario;
	
	@Autowired 
	private ClienteService servicoCliente;
	
	@Autowired
	private CaixaService servicoCaixa;
	
	@Autowired
	private EntradaSaidaService servicoEntradaSaida;
	
	private static final String PAGINA_FLUXO_CAIXA = "caixa/fluxo-caixa";
	private static final String ATUALIZAR_PAGINA = "redirect:fluxo";
	@GetMapping("fluxo")
	public String caixa(Model model, Caixa caixa, EntradaSaida entradaSaida, Long iDCaixaFK) {
		caixa = servicoCaixa.getDiaAtual();
		model.addAttribute("classActiveCaixa", "active");
		if(caixa == null) {
			model.addAttribute("caixaAberto", false);
		}else {
			model.addAttribute("caixaAberto", servicoCaixa.getDiaAtual().getAberto());
		}
		iDCaixaFK = (long) 2;
		
		model.addAttribute("teste", servicoEntradaSaida.busarTodosDoDia(servicoCaixa.getDiaAtual().getId()));
		return PAGINA_FLUXO_CAIXA;
	}
	
	@PostMapping("/adicionar")
	public String salvarEntradaOuSaida(EntradaSaida entradaSaida, Caixa caixa, Cliente cliente) {
		entradaSaida.setHorarioOperacao(LocalDateTime.now());
		System.err.println(entradaSaida.toString()); // TODO - REMOVER TOSTRING
		return ATUALIZAR_PAGINA;
	}
	
	@PostMapping("/abrir-caixa")
	public String abriCaixa(Caixa caixa, Cliente cliente, RedirectAttributes attr) {
		caixa = servicoCaixa.getDiaAtual();
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
		if(caixa == null) {
			Caixa caixaNovo = new Caixa();
			caixaNovo.setDataHoraAbertura(LocalDateTime.now());
			caixaNovo.setOperadorAbertura(usuarioLogadoNome);
			caixaNovo.setAberto(true);
			servicoCaixa.salvar(caixaNovo);
		}else {
			caixa.setAberto(true);
			caixa.setDataHoraFechamento(null);
			caixa.setOperadorFechamento(null);
			servicoCaixa.salvar(caixa);
		}
		attr.addFlashAttribute("caixaAbertoSuccesso", true);
		return ATUALIZAR_PAGINA;
	}
	
	@PostMapping("/fechar-caixa")
	public String fecharCaixa(EntradaSaida entradaSaida, Caixa caixa, Cliente cliente, RedirectAttributes attr) {
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();

		BigDecimal big1 = new BigDecimal("1000.0"); // TODO - REMOVER APOS CONCLUSAO METODO
		
		caixa = servicoCaixa.getDiaAtual();
		if(caixa == null) {
			attr.addFlashAttribute("erroFecharCaixa", true);
		}else {
			caixa.setDataHoraFechamento(LocalDateTime.now());
			caixa.setOperadorFechamento(usuarioLogadoNome);
			caixa.setAberto(false);
			
			/**
			 * Cálculo do total aqui
			 * */
			caixa.setTotal(big1); // TODO - AQUI VAI O TOTAL CALCULADO AO FECHAR CAIXA
			servicoCaixa.salvar(caixa);
		}
			// TODO - CALCULO DEBITO/CREDITO/AVISTA
			// TODO - CHAMA MODAL COM DADOS DO FECHAMENTO DO CAIXA
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
