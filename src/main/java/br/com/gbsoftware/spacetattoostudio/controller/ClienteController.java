package br.com.gbsoftware.spacetattoostudio.controller;

/**
 * <b>GB Software</b>
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("listaCliente", servicoCliente.buscarTodos());
		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		model.addAttribute("totalClientes", totalClientes.size());
		List<Cliente> totalClientesCM = servicoCliente.getPorCadastroMes();
		List<Cliente> totalClientesCMA = servicoCliente.getPorCadastroMesAnterio();
		model.addAttribute("totalCadastroMesAtual", "+" + totalClientesCM.size());
		model.addAttribute("totalCadastroMesAnterio", "+" + totalClientesCMA.size());
		List<Cliente> clientesTotal = servicoCliente.buscarTodos();
		List<Cliente> clientesAtivos = clientesTotal.stream().filter(x -> StatusClienteEnum.ATIVO.equals(x.getStatusCliente())).collect(Collectors.toList());
		List<Cliente> clientesInativos = clientesTotal.stream().filter(x -> StatusClienteEnum.INATIVO.equals(x.getStatusCliente())).collect(Collectors.toList());
		List<Cliente> clientesInadimplentes = clientesTotal.stream().filter(x -> StatusClienteEnum.INADIMPLENTE.equals(x.getStatusCliente())).collect(Collectors.toList());
		model.addAttribute("totalAtivos",clientesAtivos.size());
		model.addAttribute("totalInativos",clientesInativos.size());
		model.addAttribute("totalInadim",clientesInadimplentes.size());
		return PAGINA_CLIENTE_DETALHADO;
	}

	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		servicoCliente.salvar(cliente);
		// TODO - RETORNO ("msgSalvamentoCliente", "Novo cliente salvo com sucesso!"
		return ATUALIZAR_PAGINA;
	}

	@GetMapping("editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cliente", servicoCliente.buscarPorId(id));
		return MODAL_EDITAR_CLIENTE;
	}

	@PostMapping("editar")
	public String editar(@Valid Cliente cliente) {
		servicoCliente.editar(cliente);
		// TODO - RETORNO ("msgClienteAlterado", "Cliente alterado com sucesso!"
		return ATUALIZAR_PAGINA;
	}

	@ModelAttribute("servicos")
	public List<Servico> getServicos() {
		return servicoServico.buscarTodos();
	}

	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}
}
