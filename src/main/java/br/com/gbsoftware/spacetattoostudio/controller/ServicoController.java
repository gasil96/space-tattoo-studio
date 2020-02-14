package br.com.gbsoftware.spacetattoostudio.controller;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.google.gson.Gson;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping("agendamento")
public class ServicoController {

	private static final String PAGINA_AGENDAMENTO_DETALHADO = "detalhamento/servico-detalhado";
	private static final String ATUALIZAR_PAGINA = "redirect:detalhamento";
	private static final String MODAL_EDITAR_AGENDAMENTO = "modal/modal-editar-agendamento";
	private static final String MODAL_CONFIRMAR_ENCERRAMENTO = "modal/confirmar-encerramento";
	private static final String MODAL_REABRIR_AGENDAMENTO = "modal/modal-reabrir-agendamento";
	private static final String MODAL_NOVO_AGENDAMENTO_CLIENTE = "modal/modal-novo-agendamento-cliente";

	@Autowired
	private ServicoService servicoSevice;

	@Autowired
	private ClienteService servicoCliente;

	@GetMapping("detalhamento")
	public String servico(Servico agendamento, ModelMap model, Cliente cliente) {
		model.addAttribute("classActiveSubAgendamento", "active");
		model.addAttribute("listaServico", servicoSevice.buscarTodos());
		return PAGINA_AGENDAMENTO_DETALHADO;
	}

	@PostMapping("salvar")
	public String salvar(Servico servico, RedirectAttributes attr) {
		if (servicoCliente.buscarPorId(servico.getCliente().getId()).isPresent()) {
			if (servico.getNumeroSessoes() == null) {
				servico.setNumeroSessoes(1);
				servicoSevice.salvar(servico);
				attr.addFlashAttribute("salvou", true);
				System.err.println(servico.toString());
			} else {
				servicoSevice.salvar(servico);
				attr.addFlashAttribute("salvou", true);
			}
		} else {
			attr.addFlashAttribute("codigoInvalido", true);
		}
		return ATUALIZAR_PAGINA;
	}

	@GetMapping("agendar/{id}")
	public String preAgendar(@PathVariable("id") Long id, Model model, Servico servico) {
		model.addAttribute("id_cliente_referente", id);
		model.addAttribute("clienteNome", servicoCliente.buscarPorId(id).get().getNome());
		return MODAL_NOVO_AGENDAMENTO_CLIENTE;
	}

	@GetMapping("editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model, Servico agendamento) {
		model.addAttribute("servico", servicoSevice.buscarPorId(id));
		return MODAL_EDITAR_AGENDAMENTO;
	}

	@PostMapping("editar")
	public String editar(@Valid Servico agendamento, RedirectAttributes attr) {

		if (agendamento.getStatusAgendamento() == StatusServicoEnum.ENCERRADO) {
			agendamento.setHorarioConclusaoAgendamento(LocalDateTime.now());
		}
		agendamento.setHorarioAgendamento(agendamento.getHorarioAgendamento());
		servicoSevice.editar(agendamento);
		attr.addFlashAttribute("editou", true);
		return ATUALIZAR_PAGINA;
	}

	@GetMapping("encerrar/{id}")
	public String preEncerrar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoSevice.buscarPorId(id));
		return MODAL_CONFIRMAR_ENCERRAMENTO;
	}

	@PostMapping("encerrar")
	public String encerrar(@Valid Servico servico, RedirectAttributes attr) {
		servico.setHorarioConclusaoAgendamento(LocalDateTime.now());
		servico.setStatusAgendamento(StatusServicoEnum.ENCERRADO);
		servicoSevice.editar(servico);
		attr.addFlashAttribute("encerrou", true);
		return ATUALIZAR_PAGINA;
	}

	@GetMapping("reabrir/{id}")
	public String preReabrir(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoSevice.buscarPorId(id));
		return MODAL_REABRIR_AGENDAMENTO;
	}

	@PostMapping("reabrir")
	public String reabrir(@Valid Servico servico, RedirectAttributes attr) {
		servico.setStatusAgendamento(StatusServicoEnum.ATIVO);
		servicoSevice.editar(servico);
		attr.addFlashAttribute("reabriu", true);
		return ATUALIZAR_PAGINA;
	}

	@RequestMapping(value = "/agendamentos", method = RequestMethod.GET)
	public @ResponseBody String getAgendamentos(String agendamentos) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return agendamentos = mapper.writeValueAsString(servicoSevice.buscarTodos());
	}

	@RequestMapping(value = "/dadosMA", method = RequestMethod.GET)
	public @ResponseBody String getDadosMA(String servicosMesAtual) throws JsonProcessingException {
		List<Servico> listaAgendamentosMesAtual = servicoSevice.getAgendamentosMesAtual();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotal", filtroAgendamento(listaAgendamentosMesAtual, TipoServicoEnum.BARBEARIA).size());
		map.put("piercingTotal", filtroAgendamento(listaAgendamentosMesAtual, TipoServicoEnum.PIERCING).size());
		map.put("tattooTotal", filtroAgendamento(listaAgendamentosMesAtual, TipoServicoEnum.TATTOO).size());

		return servicosMesAtual = new Gson().toJson(map);
	}

	@RequestMapping(value = "/dadosUltimosTresM", method = RequestMethod.GET)
	public @ResponseBody String getDadoUltimosTresM(String agendamentosUltimosTresMeses)
			throws JsonProcessingException {
		List<Servico> listaAgendamentosUltimosTresMeses = servicoSevice.getAgendamentosUltimosTresMeses();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotal",
				filtroAgendamento(listaAgendamentosUltimosTresMeses, TipoServicoEnum.BARBEARIA).size());
		map.put("piercingTotal", filtroAgendamento(listaAgendamentosUltimosTresMeses, TipoServicoEnum.PIERCING).size());
		map.put("tattooTotal", filtroAgendamento(listaAgendamentosUltimosTresMeses, TipoServicoEnum.TATTOO).size());

		return agendamentosUltimosTresMeses = new Gson().toJson(map);
	}

	@RequestMapping(value = "/dadosProximosTresM", method = RequestMethod.GET)
	public @ResponseBody String getDadoProximosTresM(String agendamentosProximosTresMeses)
			throws JsonProcessingException {
		List<Servico> listaAgendamentosProximosTresMeses = servicoSevice.getAgendamentosProximosTresMeses();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotal",
				filtroAgendamento(listaAgendamentosProximosTresMeses, TipoServicoEnum.BARBEARIA).size());
		map.put("piercingTotal",
				filtroAgendamento(listaAgendamentosProximosTresMeses, TipoServicoEnum.PIERCING).size());
		map.put("tattooTotal", filtroAgendamento(listaAgendamentosProximosTresMeses, TipoServicoEnum.TATTOO).size());

		return agendamentosProximosTresMeses = new Gson().toJson(map);
	}

	@RequestMapping(value = "/dadosEncerrados", method = RequestMethod.GET)
	public @ResponseBody String getDadosEncerrados(String agendamentosEncerradosMesAtual)
			throws JsonProcessingException {
		List<Servico> listaAgendamentosEncerradosMesAtual = servicoSevice.encerramentoMesAtual();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotalEncerrado",
				filtroAgendamento(listaAgendamentosEncerradosMesAtual, TipoServicoEnum.BARBEARIA).size());
		map.put("piercingTotalEncerrado",
				filtroAgendamento(listaAgendamentosEncerradosMesAtual, TipoServicoEnum.PIERCING).size());
		map.put("tattooTotalEncerrado",
				filtroAgendamento(listaAgendamentosEncerradosMesAtual, TipoServicoEnum.TATTOO).size());

		return agendamentosEncerradosMesAtual = new Gson().toJson(map);
	}

	private List<Servico> filtroAgendamento(List<Servico> servicos, TipoServicoEnum tipoServico) {
		List<Servico> listraFiltrada = servicos.stream().filter(x -> x.getTipoServico().equals(tipoServico))
				.collect(Collectors.toList());
		return listraFiltrada;
	}

	@ModelAttribute("cliente")
	public List<Cliente> getCliente() {
		return servicoCliente.buscarTodos();
	}

	@ModelAttribute("tipoagendamento")
	public TipoServicoEnum[] getTipoServico() {
		return TipoServicoEnum.values();
	}

	@ModelAttribute("statusagendamento")
	public StatusServicoEnum[] getStatusAgendamento() {
		return StatusServicoEnum.values();
	}
}
