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
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.GenericReponse;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;
import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@Controller
@RequestMapping
public class InitController {

	private static final String PAGINA_INICIAL = "home";

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoService;

	@Autowired
	private UsuarioService servicoUsuario;
	
	@Autowired
	private MailSender mailSender;
	
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
		List<Servico> listaServicos = servicoService.buscarTodos().stream()
				.filter(x -> x.getStatusAgendamento().equals(StatusServicoEnum.ATIVO)).collect(Collectors.toList());
		String listaServicosJson = mapper.writeValueAsString(listaServicos);
		return listaServicosJson;
	}

	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public GenericReponse resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) throws Exception {
		Usuario usuario = servicoUsuario.buscarPorEmail(userEmail);
		if(usuario == null) {
			throw new Exception("Usuário não encontrado");
		}
		String token = UUID.randomUUID().toString();
		servicoUsuario.createPasswordResetTokenForUser(usuario, token);
		mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, usuario));
		return new GenericReponse(message.get, error)
	}
	
	private SimpleMailMessage constructResetTokenEmail(String contexPath, Locale locale, String token, Usuario usuario) {
		String url = contexPath = "/user/changePassword?id="+usuario.getLogin() + "&token=" + token;
		 String message = messages.getMessage("message.resetPassword", 
			      null, locale);
		 
	}
	
	private SimpleMailMessage constructEmail(String subject, String body, 
			  Usuario usuario) {
			    SimpleMailMessage email = new SimpleMailMessage();
			    email.setSubject(subject);
			    email.setText(body);
			    email.setTo(usuario.getEmail());
			    email.setFrom(env.getProperty("support.email"));
			    return email;
			}
	
	@GetMapping("/login")
	public String login() {
		return "login";
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

	
}
