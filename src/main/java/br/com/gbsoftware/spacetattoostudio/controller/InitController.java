package br.com.gbsoftware.spacetattoostudio.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping
public class InitController {

	private static final String PAGINA_INICIAL = "home";

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoService;

	@GetMapping("/")
	public String home(ModelMap model, Cliente cliente, Servico servico) {
		model.addAttribute("classActivePrincipal", "active");

		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		List<Servico> totalAgendamentos = servicoService.buscarTodos();
		
		model.addAttribute("totalClientes", totalClientes.size());
 		
		model.addAttribute("totalAgendamentosSemana", totalAgendamentos.stream()
				.filter(agSemana -> agSemana.getHorarioAgendamento()
						.isAfter(LocalDateTime.now())).collect(Collectors.toList()).size());

		model.addAttribute("proximosAgendamentos", totalAgendamentos.stream()
				.filter(agSemana -> agSemana.getHorarioAgendamento()
						.isAfter(LocalDateTime.now()))
				.sorted(Comparator.comparing(Servico::getHorarioAgendamento))
				.limit(3).collect(Collectors.toList()));
		
		
		return PAGINA_INICIAL;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

//	@ModelAttribute("listaAgendamentoDia")
//	public List<Servico> getAgendamentoDoDia() {
//		return servicoService.getAgendamentoPorDia();
//	}

//	@ModelAttribute("listaAgendamentoDaSemana")
//	public List<Servico> getAgendamentoDaSemana() {
//		return servicoService.getAgendamentoPorSemana();
//	}

	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}

	@ModelAttribute("tipoagendamento")
	public TipoServicoEnum[] getTipoServico() {
		return TipoServicoEnum.values();
	}

	@ModelAttribute("statusagendamento")
	public StatusServicoEnum[] getStatusAgendamento() {
		return StatusServicoEnum.values();
	}

	@RequestMapping(value = "/calendario", method = RequestMethod.GET)
	public @ResponseBody String getCalendario(HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		List<Servico> listaServicos = servicoService.buscarTodos();
		String listaServicosJson = mapper.writeValueAsString(listaServicos);
		return listaServicosJson;
	}

}
