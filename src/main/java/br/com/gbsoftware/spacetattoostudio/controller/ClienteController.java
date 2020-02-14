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

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoServico;

	@GetMapping("agendar/{id}")
	public String preAgendar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoServico.buscarPorId(id));
		model.addAttribute("id_cliente_referente", id);
		model.addAttribute("clienteNome", servicoCliente.buscarPorId(id).get().getNome());
		return MODAL_NOVO_AGENDAMENTO_CLIENTE;
	}

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

		model.addAttribute("totalAtivos", clientesTotal.stream()
				.filter(x -> StatusClienteEnum.ATIVO.equals(x.getStatusCliente())).collect(Collectors.toList()).size());

		model.addAttribute("totalInativos",
				clientesTotal.stream().filter(x -> StatusClienteEnum.INATIVO.equals(x.getStatusCliente()))
						.collect(Collectors.toList()).size());

		return PAGINA_CLIENTE_DETALHADO;
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

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public @ResponseBody String getCalendario(String clientes) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return clientes = mapper.writeValueAsString(servicoCliente.buscarTodos());
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

	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}

}
