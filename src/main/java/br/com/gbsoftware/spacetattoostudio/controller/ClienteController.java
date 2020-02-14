package br.com.gbsoftware.spacetattoostudio.controller;

import java.time.LocalDate;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping("cliente")
public class ClienteController {

	private static final String PAGINA_CLIENTE_DETALHADO = "detalhamento/cliente-detalhado";
	private static final String ATUALIZAR_PAGINA = "redirect:detalhamento";
	private static final String MODAL_EDITAR_CLIENTE = "modal/modal-editar-cliente";
	private static final String MODAL_NOVO_AGENDAMENTO_CLIENTE = "modal/modal-novo-agendamento-cliente";
	private static final String MODAL_VISUALIZAR_CLIENTE = "modal/modal-visualizar-cliente";

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoServico;

	@GetMapping("detalhamento")
	public String cliente(Cliente cliente, Servico service, Model model) {
		model.addAttribute("classActiveSubCliente", "active");

		List<Cliente> clientesTotal = servicoCliente.buscarTodos();

		model.addAttribute("totalClientes", clientesTotal.size());

		model.addAttribute("totalCadastroMesAtual",
				"+" + clientesTotal.stream()
						.filter(x -> x.getDataCadastro().getMonthValue() == LocalDate.now().getMonthValue())
						.collect(Collectors.toList()).size());

		model.addAttribute("totalCadastroMesAnterio", "+" + clientesTotal.stream()
				.filter(x -> x.getDataCadastro().getMonthValue() == LocalDate.now().plusMonths(-1).getMonthValue())
				.collect(Collectors.toList()).size());

		model.addAttribute("totalAtivos", ativoInativo(clientesTotal, StatusClienteEnum.ATIVO).size());
		model.addAttribute("totalInativos", ativoInativo(clientesTotal, StatusClienteEnum.INATIVO).size());

		return PAGINA_CLIENTE_DETALHADO;
	}

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public @ResponseBody String getCalendario(String clientes) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return clientes = mapper.writeValueAsString(servicoCliente.buscarTodos());
	}

	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (cliente.getInstagram().isEmpty()) {
			servicoCliente.salvar(cliente);
			attr.addFlashAttribute("salvou", true);
		} else {
			String insta = "@" + cliente.getInstagram();
			if (servicoCliente.buscarPorInstagram(insta).isEmpty()) {
				servicoCliente.salvar(cliente);
				attr.addFlashAttribute("salvou", true);
			} else {
				attr.addFlashAttribute("instaJaExiste", true);
			}
		}
		return ATUALIZAR_PAGINA;
	}

	@GetMapping("visualizar/{id}")
	public String visualizar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cliente", servicoCliente.buscarPorId(id));
		return MODAL_VISUALIZAR_CLIENTE;
	}

	@GetMapping("agendar/{id}")
	public String agendar(@PathVariable("id") Long id, Model model) {
		Servico servico = servicoServico.buscarPorId(id).orElse(null);
		model.addAttribute("servico", servico);
		model.addAttribute("id_cliente_referente", id);
		model.addAttribute("clienteNome", servico.getCliente().getNome());
		return MODAL_NOVO_AGENDAMENTO_CLIENTE;
	}

	@GetMapping("editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cliente", servicoCliente.buscarPorId(id));
		return MODAL_EDITAR_CLIENTE;
	}

	@PostMapping("editar")
	public String editar(@Valid Cliente cliente, RedirectAttributes attr) {
		servicoCliente.editar(cliente);
		attr.addFlashAttribute("editou", true);
		return ATUALIZAR_PAGINA;
	}

	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}

	private List<Cliente> ativoInativo(List<Cliente> listaClientes, StatusClienteEnum statusCliente) {
		return listaClientes.stream().filter(x -> x.getStatusCliente().equals(statusCliente))
				.collect(Collectors.toList());
	}

}
