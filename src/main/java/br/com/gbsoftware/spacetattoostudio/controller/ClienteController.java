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

	@Autowired
	private ClienteService servicoCliente;

	@Autowired
	private ServicoService servicoServico;
	

	@GetMapping("cadastrar")
	public String cadastrar(Cliente cliente, Model model) {
		model.addAttribute("classActiveCliente","active"); 
		return "detalhamento/cliente";
		// TODO - retorno e a tela de especificacoes de cliente
	}

	@GetMapping("/excluir-cliente/{id}")
	public String excluirCliente(@PathVariable("id") Long id, RedirectAttributes attr) {
		servicoCliente.excluir(id);
		attr.addFlashAttribute("successoExcluir", "Cliente excluido com sucesso!");
		// TODO - logica de excluir sem relacionametno
		return "redirect:/";
	}

	@GetMapping("busca/id")
	public String buscarClienteId(@PathVariable("id") ModelMap model, Long id) {
		model.addAttribute("lista", servicoCliente.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException()));
		return "home";
		// TODO - aqui vai a a *section* com o clietne localizado
	}

	// TODO - DEFAUTL
	@GetMapping("/busca/nome")
	public String buscarClienteNome(@RequestParam("nome") ModelMap model, String nome) {
		model.addAttribute("lista", servicoCliente.buscarPorNome(nome));
		return "home";
		// TODO - aqui vai a a *section* com o clietne localizado
	}

	@GetMapping("busca/instagram")
	public String buscarClienteInstagram(ModelMap model, String instagram) {
		model.addAttribute("lista", servicoCliente.buscarPorInstagram(instagram));
		return "home";
		// TODO - aqui vai a a *section* com o clietne localizado
	}

	@GetMapping("busca/todos")
	public String buscar(ModelMap model, String nome, RedirectAttributes attr, Cliente cliente) {
		model.addAttribute("listaCliente", servicoCliente.buscarTodos());
		return "home";
		// TODO - aqui vai a  *tabela* com a lista de clientes localizados
	}

	@PostMapping("salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		servicoCliente.salvar(cliente);
		attr.addAttribute("sucessoMenssagemSalvar", "Cliente salvo com sucesso!");
		return "home";
		//TODO - FECHA O MODAL e madna msg 
	}

	@GetMapping("/edita/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", servicoCliente.buscarPorId(id));
		return "detalhamento/cliente";
		// MODAL PREENCHIDO COM TODOS OS CAMPOS COM ID JA PASSADA;
	}
	
	
	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			attr.addAttribute("erroMenssagemEditar", "Não foi possivel editar o cliente");
			
			return "detalhamento/cliente";
			
		}else if (cliente.getId() != null) {
			servicoCliente.editar(cliente);
			attr.addAttribute("sucessoMenssagemEditar", "Cliente editado com sucesso!");
			// TODO - CLOSE O MODAL DE EDICAO
		}
		return "home";
	}
	
	@ModelAttribute("servicos")
	public List<Servico> getServicos(){
		return servicoServico.buscarTodos();
	}
	
	@ModelAttribute("statuscliente")
	public StatusClienteEnum[] getStatusCliuente() {
		return StatusClienteEnum.values();
	}		
	
}
