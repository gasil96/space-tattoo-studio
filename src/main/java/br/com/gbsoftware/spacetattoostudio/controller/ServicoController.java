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

import javax.servlet.http.HttpServletResponse;
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

	@RequestMapping(value = "/dadosMA", method = RequestMethod.GET)
	public @ResponseBody String getDadosMA(HttpServletResponse response) throws JsonProcessingException {

		List<Servico> listaAgendamentosMesAtual = servicoSevice.getAgendamentosMesAtual();

		List<Servico> agendamentosBarbearia = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.BARBEARIA.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosPiercing = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.PIERCING.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosTattoo = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.TATTOO.equals(x.getTipoServico())).collect(Collectors.toList());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotal", agendamentosBarbearia.size());
		map.put("piercingTotal", agendamentosPiercing.size());
		map.put("tattooTotal", agendamentosTattoo.size());

		String listaTotaisAgendamentosJson = new Gson().toJson(map);

		return listaTotaisAgendamentosJson;
	}

	@RequestMapping(value = "/dadosUltimosTresM", method = RequestMethod.GET)
	public @ResponseBody String getDadoUltimosTresM(HttpServletResponse response) throws JsonProcessingException {

		List<Servico> listaAgendamentosMesAtual = servicoSevice.getAgendamentosUltimosTresMeses();

		List<Servico> agendamentosBarbearia = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.BARBEARIA.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosPiercing = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.PIERCING.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosTattoo = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.TATTOO.equals(x.getTipoServico())).collect(Collectors.toList());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotal", agendamentosBarbearia.size());
		map.put("piercingTotal", agendamentosPiercing.size());
		map.put("tattooTotal", agendamentosTattoo.size());

		String listaTotaisAgendamentosJson = new Gson().toJson(map);

		return listaTotaisAgendamentosJson;
	}

	@RequestMapping(value = "/dadosProximosTresM", method = RequestMethod.GET)
	public @ResponseBody String getDadoProximosTresM(HttpServletResponse response) throws JsonProcessingException {

		List<Servico> listaAgendamentosMesAtual = servicoSevice.getAgendamentosProximosTresMeses();

		List<Servico> agendamentosBarbearia = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.BARBEARIA.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosPiercing = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.PIERCING.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosTattoo = listaAgendamentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.TATTOO.equals(x.getTipoServico())).collect(Collectors.toList());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("barbeariaTotal", agendamentosBarbearia.size());
		map.put("piercingTotal", agendamentosPiercing.size());
		map.put("tattooTotal", agendamentosTattoo.size());

		String listaTotaisAgendamentosJson = new Gson().toJson(map);

		return listaTotaisAgendamentosJson;
	}

	@RequestMapping(value = "/dadosEncerrados", method = RequestMethod.GET)
	public @ResponseBody String getDadosEncerrados(HttpServletResponse response) throws JsonProcessingException {

		List<Servico> encerramentosMesAtual = servicoSevice.encerramentoMesAtual();

		List<Servico> agendamentosBarbeariaE = encerramentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.BARBEARIA.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosPiercingE = encerramentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.PIERCING.equals(x.getTipoServico())).collect(Collectors.toList());

		List<Servico> agendamentosTattooE = encerramentosMesAtual.stream()
				.filter(x -> TipoServicoEnum.TATTOO.equals(x.getTipoServico())).collect(Collectors.toList());

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("barbeariaTotalEncerrado", agendamentosBarbeariaE.size());
		map.put("piercingTotalEncerrado", agendamentosPiercingE.size());
		map.put("tattooTotalEncerrado", agendamentosTattooE.size());

		String listaTotaisAgendamentosJsonEncerrados = new Gson().toJson(map);

		return listaTotaisAgendamentosJsonEncerrados;
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
