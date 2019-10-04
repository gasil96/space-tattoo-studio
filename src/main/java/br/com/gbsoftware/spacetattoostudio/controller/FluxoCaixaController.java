package br.com.gbsoftware.spacetattoostudio.controller;

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
	public String caixa(Model model, Caixa caixa, EntradaSaida entradaSaida, Cliente cliente) {
		caixa = servicoCaixa.getDiaAtual();
		model.addAttribute("classActiveCaixa", "active");
		if (caixa == null) {
			model.addAttribute("caixaAberto", false);
		} else {
			model.addAttribute("teste", servicoEntradaSaida.busarTodosDoDia(servicoCaixa.getDiaAtual().getId()));
			model.addAttribute("caixaAberto", servicoCaixa.getDiaAtual().getAberto());
		}
		return PAGINA_FLUXO_CAIXA;
	}

	@PostMapping("/adicionar")
	public String salvarEntradaOuSaida(EntradaSaida entradaSaida, RedirectAttributes attr) {
		entradaSaida.setHorarioOperacao(LocalDateTime.now());
		if (servicoCliente.buscarPorId(entradaSaida.getCliente().getId()).isPresent()
				&& servicoCaixa.buscarPorId(entradaSaida.getCaixa().getId()).isPresent()) {
			servicoEntradaSaida.salvar(entradaSaida);
			attr.addFlashAttribute("adicionou", true); // TODO - FALTA IMPLEMENTAR ESSSE HUBSPOT MENSSEGER
		} else {
			attr.addFlashAttribute("erroAdicionar", true); // TODO - FALTA IMPLEMENTAR ESSSE HUBSPOT MENSSEGER
		}
		return ATUALIZAR_PAGINA;
	}

	@PostMapping("/abrir-caixa")
	public String abriCaixa(Caixa caixa, Cliente cliente, RedirectAttributes attr) {
		caixa = servicoCaixa.getDiaAtual();
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
		if (caixa == null) {
			Caixa caixaNovo = new Caixa();
			caixaNovo.setDataHoraAbertura(LocalDateTime.now());
			caixaNovo.setOperadorAbertura(usuarioLogadoNome);
			caixaNovo.setAberto(true);
			servicoCaixa.salvar(caixaNovo);
			attr.addFlashAttribute("caixaAbertoSucesso", true); // TODO - FALTA IMPLEMENTAR ESSSE HUBSPOT MENSSEGER
		} else {
			caixa.setAberto(true);
			caixa.setDataHoraFechamento(null);
			caixa.setOperadorFechamento(null);
			servicoCaixa.salvar(caixa);
			attr.addFlashAttribute("caixaReaberto", true); // TODO - FALTA IMPLEMENTAR ESSSE HUBSPOT MENSSEGER
		}
		return ATUALIZAR_PAGINA;
	}

	@PostMapping("/fechar-caixa")
	public String fecharCaixa(EntradaSaida entradaSaida, Caixa caixa, Cliente cliente, RedirectAttributes attr) {
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
		caixa = servicoCaixa.getDiaAtual();
		if (caixa == null) {
			attr.addFlashAttribute("erroFecharCaixa", true); // TODO - FALTA IMPLEMENTAR ESSSE HUBSPOT MENSSEGER
		} else {
			caixa.setDataHoraFechamento(LocalDateTime.now());
			caixa.setOperadorFechamento(usuarioLogadoNome);
			caixa.setAberto(false);

			attr.addFlashAttribute("caixaFechadoSucesso", true);// TODO - FALTA IMPLEMENTAR ESSSE HUBSPOT MENSSEGER
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

	@ModelAttribute("idcaixadia")
	public Long getIdCaixaDia() {
		Caixa caixa = servicoCaixa.getDiaAtual();
		if (caixa == null) {
			long idCaixaDia = 1;
			return idCaixaDia;
		} else {
			Long idCaixaDia = servicoCaixa.getDiaAtual().getId();
			return idCaixaDia;
		}
	}

}
