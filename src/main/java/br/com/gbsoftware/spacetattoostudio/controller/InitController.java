package br.com.gbsoftware.spacetattoostudio.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.dto.AgendamentoCalendarDTO;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;

@Controller
@RequestMapping
public class InitController {

	private static final String PAGINA_INICIAL = "home";
	private static final String MODAL_ALERTA_EMAIL = "modal/modal-alerta-email-home";
	private static final String REDIRECIONAR = "redirect:/";
	private static final String MSG_SUCCESS = "success";
	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/")
	public String home(ModelMap model, Cliente cliente, Servico servico) {
		model.addAttribute("classActivePrincipal", "active");

		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		List<Servico> totalAgendamentos = servicoService.buscarTodos();

		model.addAttribute("totalClientes", totalClientes.size());

		model.addAttribute("totalAgendamentosSemana",
				totalAgendamentos.stream()
						.filter(x -> x.getHorarioAgendamento().isAfter(LocalDateTime.now())
								&& x.getHorarioAgendamento().isBefore(LocalDateTime.now().plusWeeks(1)))
						.collect(Collectors.toList()).size());

		model.addAttribute("proximosAgendamentos", totalAgendamentos.stream()
				.filter(agSemana -> agSemana.getHorarioAgendamento().isAfter(LocalDateTime.now()))
				.sorted(Comparator.comparing(Servico::getHorarioAgendamento)).limit(3).collect(Collectors.toList()));
		return PAGINA_INICIAL;
	}

	@RequestMapping(value = "/calendario", method = RequestMethod.GET)
	public @ResponseBody String getCalendario() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		List<AgendamentoCalendarDTO> calendarioDto = servicoService.buscarTodos().stream().map(x -> converterParaDTO(x))
				.collect(Collectors.toList());
		String dadosCalendarioIO = mapper.writeValueAsString(calendarioDto);
		return dadosCalendarioIO;
	}

	@Value("${LINK_FOOTER}")
	private String linkFooter;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("linkFooter", linkFooter);
		return "login";
	}

	@GetMapping("enviar-alerta-email/{id}")
	public String preEnviarAlertaEmail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoService.buscarPorId(id).orElse(null));
		return MODAL_ALERTA_EMAIL;
	}

	@PostMapping("enviar-email-alerta")
	public String enviarAlertaEmail(Servico servico, SimpleMailMessage msgEmail, RedirectAttributes attr) {
		DateTimeFormatter dia = DateTimeFormatter.ofPattern("dd/MM");
		DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm");
		Servico s1 = servicoService.buscarPorId(servico.getId()).orElse(null);
		Cliente c1 = servicoCliente.buscarPorId(s1.getCliente().getId()).orElse(null);
		msgEmail.setTo(c1.getEmail());
		msgEmail.setSubject("Confirmação de Agendamento Space Tattoo Studio");
		msgEmail.setText("Olá " + c1.getNome() + " gostariamos de confirmar seu agendamento de " + s1.getTipoServico()
				+ " para às " + s1.getHorarioAgendamento().format(hora) + " do dia "
				+ s1.getHorarioAgendamento().format(dia));
		mailSender.send(msgEmail);
		attr.addFlashAttribute(MSG_SUCCESS, "Email de alerta enviado para " + c1.getNome());
		return REDIRECIONAR;
	}

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

	private AgendamentoCalendarDTO converterParaDTO(Servico servico) {

		ModelMapper testMapper = new ModelMapper();
		testMapper.addMappings(new PropertyMap<Servico, AgendamentoCalendarDTO>() {

			@Override
			protected void configure() {
				map().setCategoria(source.getCategoria());
				map().setHorarioAgendamento(source.getHorarioAgendamento());
				map().setTipoServico(source.getTipoServico());
			}

		});

		AgendamentoCalendarDTO calendarioDTO = testMapper.map(servico, AgendamentoCalendarDTO.class);
		return calendarioDTO;
	}
}
