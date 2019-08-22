package br.com.gbsoftware.spacetattoostudio.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
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
@RequestMapping("cliente")
public class ClienteController {

	private static final String PAGINA_INICIAL = "home";
	private static final String PAGINA_CLIENTE_DETALHADO = "detalhamento/cliente-detalhado";
	private static final String ATUALIZAR_PAGINA = "redirect:detalhamento";
	
	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoServico;
	

	@GetMapping("detalhamento")
	public String cliente(Cliente cliente, Model model) {
		model.addAttribute("classActiveSubCliente","active"); 
		model.addAttribute("listaCliente", servicoCliente.buscarTodos());
		return PAGINA_CLIENTE_DETALHADO;
	}

	/*SALVAR NOVO CLIENTE*/
	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		servicoCliente.salvarOuEditar(cliente);
		attr.addAttribute("sucessoMenssagemSalvar", "Cliente salvo com sucesso!");
		return  ATUALIZAR_PAGINA;
		//TODO - FECHA O MODAL e madna msg 
	}

	/*EDITAR PASSANDO CAMPOS*/
	
	@GetMapping("editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", servicoCliente.buscarPorId(id));
		return PAGINA_CLIENTE_DETALHADO;
		// MODAL PREENCHIDO COM TODOS OS CAMPOS COM ID JA PASSADA;
	}
	
	/*EDITAR*/
	@PostMapping("editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
			attr.addAttribute("erroMenssagemEditar", "Não foi possivel editar o cliente");
			servicoCliente.salvarOuEditar(cliente);
			return ATUALIZAR_PAGINA;
	}
	

	/*BUSCAR POR NOME*/
	@GetMapping("/busca-nome")
	public String buscarClienteNome(@RequestParam("nome") ModelMap model, String nome) {
		model.addAttribute("lista", servicoCliente.buscarPorNome(nome));
		return PAGINA_INICIAL;
		// TODO - aqui vai a a *section* com o clietne localizado
	}
	
	/*BUSCAR POR INSTAGRAM*/
	@GetMapping("busca-instagram")
	public String buscarClienteInstagram(ModelMap model, String instagram) {
		model.addAttribute("lista", servicoCliente.buscarPorInstagram(instagram));
		return PAGINA_INICIAL;
		// TODO - aqui vai a a *section* com o clietne localizado
	}
	
	/*BUSCAR TODOS*/
	@GetMapping("busca-todos")
	public String buscar(ModelMap model, String nome, RedirectAttributes attr, Cliente cliente) {
		model.addAttribute("listaCliente", servicoCliente.buscarTodos());
		return PAGINA_CLIENTE_DETALHADO;
		// TODO - aqui vai a  *tabela* com a lista de clientes localizados
	}

	
	@ModelAttribute("servicos")
	public List<Servico> getServicos(){
		return servicoServico.buscarTodos();
	}
	
	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliente() {
		return StatusClienteEnum.values();
	}		
	
}
