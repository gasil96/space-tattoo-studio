package br.com.gbsoftware.spacetattoostudio.controller;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.VwClienteServicoDadosService;

@Controller
@RequestMapping("promocao")
public class PromocionalController {

	private static final String PAGINANA_DETALHAMENTO_PROMOCIONAL = "detalhamento/promocional-detalhado";
	private static final String ATT_PAGINA = "redirect:detalhamento";

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private VwClienteServicoDadosService servicoVwClienteService;
	
	@GetMapping("detalhamento")
	public String detalhamentoPromocional(Cliente cliente, Model model) {
		model.addAttribute("topTattoo", servicoVwClienteService.buscarTodos().stream().limit(10).collect(Collectors.toList()));
		return PAGINANA_DETALHAMENTO_PROMOCIONAL;
	}

	@PostMapping("add-credito")
	public String adicionarCredito(Cliente cliente) throws Exception {
		Cliente clienteLocalizado = servicoCliente.buscarPorId(cliente.getId()).orElse(null);
		if (clienteLocalizado != null) {
			if (clienteLocalizado.getCreditoCliente() != null) {
				servicoCliente.updateCredito(clienteLocalizado.getCreditoCliente().add(cliente.getCreditoCliente()),
						cliente.getId());
			} else {
				servicoCliente.updateCredito(cliente.getCreditoCliente(), cliente.getId());
			}
		} else {
			throw new Exception("Não foi possivel localizar o cliente");
		}
		return ATT_PAGINA;
	}

	@PostMapping("consumir-credito")
	public String removerCredito(Cliente cliente) throws Exception {
		Cliente clienteLocalizado = servicoCliente.buscarPorId(cliente.getId()).orElse(null);
		if (clienteLocalizado != null) {
			if (clienteLocalizado.getCreditoCliente() != null) {
				servicoCliente.updateCredito(
						clienteLocalizado.getCreditoCliente().subtract(cliente.getCreditoCliente()), cliente.getId());
			} else {
				servicoCliente.updateCredito(new BigDecimal(0).subtract(cliente.getCreditoCliente()), cliente.getId());
			}
		} else {
			throw new Exception("Não foi possivel localizar o cliente");
		}

		return ATT_PAGINA;
	}
}
