package br.com.gbsoftware.spacetattoostudio.controller;
import java.util.HashMap;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

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
		model.addAttribute("listaCliente", servicoCliente.buscarTodos());
		model.addAttribute("totalClientes", totalClientes.size());
		model.addAttribute("totalAgendamentosDia", getAgendamentoDoDia().size());
		model.addAttribute("totalAgendamentosSemana", getAgendamentoDaSemana().size());
		model.addAttribute("proximosAgendamentos", servicoService.getProximosSeisAgendamentos());
		return PAGINA_INICIAL;
	}
	
	 @GetMapping("/login")
	    public String login() {
	        return "login";
	    }
	
	@GetMapping("/home")
	public String dashboard(ModelMap model, Cliente cliente) {
		model.addAttribute("classActivePrincipal", "active");
		List<Cliente> totalClientes = servicoCliente.buscarTodos();
		model.addAttribute("totalClientes", totalClientes.size());
		return PAGINA_INICIAL;
	}
	
	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}
	
	@ModelAttribute("listaAgendamentoDia")
	public List<Servico> getAgendamentoDoDia(){
		return servicoService.getAgendamentoPorDia();
	}
	
	@ModelAttribute("listaAgendamentoDaSemana")
	public List<Servico> getAgendamentoDaSemana(){
		return servicoService.getAgendamentoPorSemana();
	}
	
	@ModelAttribute("tipoagendamento")
	public TipoServicoEnum[] getTipoServico() {
		return TipoServicoEnum.values();
	}
	
	@ModelAttribute("statusagendamento")
	public StatusServicoEnum[] getStatusAgendamento() {
		return StatusServicoEnum.values();
	}
	
	/** 
	 * Testes TODO
	 * 
	 * */
	
	@RequestMapping(value = "/calendario", method = RequestMethod.GET)
    public
    @ResponseBody
    String getCalendario(HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", "2019-09-17");
        map.put("id", 111);
        map.put("title", "Agendamento");
        map.put("url", "http://globo.com/");
        map.put("end", "2019-09-05");

        // Convert to JSON string.
        String ted = new Gson().toJson(map);
         String ss = "{'start':'2019-09-17','end':'2019-09-05','id':111,'title':'Agendamen@@@to','url':'globo.com/'}";
         System.err.println("Objeto json" + ss);
        return ss;
        
	
	}
	
}
