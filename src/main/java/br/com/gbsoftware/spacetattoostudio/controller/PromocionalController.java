package br.com.gbsoftware.spacetattoostudio.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.CorpoWhatsApp;
import br.com.gbsoftware.spacetattoostudio.domain.vw.VwClienteDadosPiercing;
import br.com.gbsoftware.spacetattoostudio.domain.vw.VwClienteDadosTattoo;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.VwClienteDadosPiercingService;
import br.com.gbsoftware.spacetattoostudio.service.VwClienteDadosTattooService;

@Controller
@RequestMapping("promocao")
public class PromocionalController {

	private static final String PAGINANA_DETALHAMENTO_PROMOCIONAL = "detalhamento/promocional-detalhado";
	private static final String ATT_PAGINA = "redirect:detalhamento";
	private static final String MODAL_ENVIAR_MSG_WPP = "modal/modal-enviar-msg-wpp";
	private static final String WHATSAPP_URL = "wa.me/55";

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private VwClienteDadosTattooService servicoVwClienteTattoo;

	@Autowired
	private VwClienteDadosPiercingService servicoVwClientePiercing;

	@GetMapping("detalhamento")
	public String detalhamentoPromocional(Cliente cliente, Model model) {
		List<VwClienteDadosTattoo> listaVwTattoo = servicoVwClienteTattoo.buscarTodos();
		List<VwClienteDadosPiercing> listaVwPiercing = servicoVwClientePiercing.buscarTodos();

		model.addAttribute("topTattoo",
				listaVwTattoo.stream()
						.sorted(Comparator.comparingLong(VwClienteDadosTattoo::getNumeroAgendamentos).reversed())
						.limit(10).collect(Collectors.toList()));

		model.addAttribute("topPiercing",
				listaVwPiercing.stream()
						.sorted(Comparator.comparingLong(VwClienteDadosPiercing::getNumeroAgendamentos).reversed())
						.limit(10).collect(Collectors.toList()));

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

	@GetMapping("whatsapp/{id}")
	public String msgWhatsApp(@PathVariable("id") Long id, Model model, CorpoWhatsApp corpoWpp) throws Exception {
		Cliente clienteLocalizado = servicoCliente.buscarPorId(id).orElse(null);
		if (clienteLocalizado == null) {
			throw new Exception("Cliente com id: " + id + " não existe!");
		} else {
			corpoWpp.setMsg(msgOferta(clienteLocalizado));
			corpoWpp.setNumero(clienteLocalizado.getTelefone());
			model.addAttribute("url", urlWhatsApp(corpoWpp));
			model.addAttribute("whatsapp", corpoWpp);
			return MODAL_ENVIAR_MSG_WPP;
		}
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

	private String urlWhatsApp(CorpoWhatsApp corpoWpp) {
		String numeroTratato = corpoWpp.getNumero().replaceAll("\\D", "");
		corpoWpp.setNumero(numeroTratato);
		String urlParametriza = WHATSAPP_URL + numeroTratato + "?text=" + corpoWpp.getMsg();
		return urlParametriza;
	}

	private String msgOferta(Cliente cliente) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String menssagem = "Olá " + cliente.getNome()
				+ ", você está na lista dos *MELHORES CLIENTES* do Space Tattoo Studio e "
				+ "com isso acaba de ganhar uma oferta especial para você. Responda esssa mensagem para mais informações "
				+ " *Oferta válida até:* "
				+ LocalDateTime.now().plusDays(15).format(dateFormat);
		return menssagem;
	}

}
