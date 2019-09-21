package br.com.gbsoftware.spacetattoostudio.controller;
/**
 * <b>GB Software</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoRole;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.Role;
import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;
import br.com.gbsoftware.spacetattoostudio.service.RoleService;
import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@Controller
@RequestMapping("adm")
public class AdministracaoController {

	private static final String __ADMINISTRACAO = "admin/admin";
	private static final String ATT_PAGINA__ = "redirect:administracao";
	
	@Autowired
	private UsuarioService servicoUsuario;
	
	@Autowired
	private RoleService servicoRole;
	
	@GetMapping("administracao")
	public String admin(ModelMap model, Cliente cliente, Servico servico, Usuario usuario, Role role) {
		model.addAttribute("AdminAtivo", "active");
		model.addAttribute("listaUsuarios", servicoUsuario.buscarTodos());
		Role r1 = new Role();
		Role r2 = new Role();
		role.setNomeRole("ROLE_ADMIN");
		r1.setNomeRole("ROLE_GERENTE");
		r2.setNomeRole("ROLE_USUARIO");
		servicoRole.salvar(r1);
		servicoRole.salvar(r2);
		servicoRole.salvar(role);
		model.addAttribute("listaUsuarios", servicoUsuario.buscarTodos());
		return __ADMINISTRACAO;
	}
	
	@PostMapping("/salvar-usuario")
	public String salvarUsuario(@Valid Usuario usuario, RedirectAttributes attr) {
		servicoUsuario.salvar(usuario);
		attr.addFlashAttribute("salvou", true);
		
		return ATT_PAGINA__;
	}
	
	@PostMapping("/salvar-role")
	public String salvarUsuario(@Valid Role	role) {
		servicoRole.salvar(role);
		return __ADMINISTRACAO;
	}
	
	@ModelAttribute("tiporole")
	public TipoRole[] getTipoRole() {
		return TipoRole.values();
	}
}
