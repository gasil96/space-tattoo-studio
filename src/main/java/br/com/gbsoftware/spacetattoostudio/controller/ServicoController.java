package br.com.gbsoftware.spacetattoostudio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoServicoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.service.ClienteService;
import br.com.gbsoftware.spacetattoostudio.service.ServicoService;
/**
 * 
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 *
 */
@Controller
@RequestMapping("agendamento")
public class ServicoController {

	private static final String PAGINA_INICIAL = "home";
	private static final String PAGINA_AGENDAMENTO_DETALHADO = "detalhamento/servico-detalhado";
	private static final String ATUALIZAR_PAGINA = "redirect:detalhamento";
	private static final String MODAL_EDITAR_AGENDAMENTO = "modal/modal-editar-agendamento";
	
	
	
	@Autowired
	private ServicoService servicoSevice;
	
	@Autowired
	private ClienteService servicoCliente;
	
	
	@GetMapping("detalhamento")
	public String servico( Servico agendamento, ModelMap model, Cliente cliente) {
		
		
		model.addAttribute("listaServico", servicoSevice.buscarTodos());
		model.addAttribute("classActiveSubAgendamento","active");
		
		return PAGINA_AGENDAMENTO_DETALHADO;
	}
	
	@GetMapping("cadastrar")
	public String Cadastrar(Servico agendamento) {
		return PAGINA_INICIAL;
	}
	
	
	@PostMapping("salvar")
	public String salvar(Servico agendamento) {
	
		servicoSevice.salvar(agendamento);
		return ATUALIZAR_PAGINA;
		
	}
	
	@GetMapping("editar/{id}")
	public String preEditar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("servico", servicoSevice.buscarPorId(id));
		return MODAL_EDITAR_AGENDAMENTO; 
	}
	
	@PostMapping("editar")
	public String editar(@Valid Servico agendamento, BindingResult result, RedirectAttributes attr) {
		servicoSevice.editar(agendamento);
		return ATUALIZAR_PAGINA;
	}
	
	
	@ModelAttribute("cliente")
	public List<Cliente> getCliente(){
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
