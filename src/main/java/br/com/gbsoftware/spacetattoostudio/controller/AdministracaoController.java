package br.com.gbsoftware.spacetattoostudio.controller;

import java.util.Optional;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gbsoftware.spacetattoostudio.domain.enums.CargoUsuarioEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoRoleEnum;
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
	private static final String MODAL_EDITAR_USUARIO = "modal/modal-editar-usuario";
	private static final String MODAL_VISUALIZAR_USUARIO = "modal/modal-visualizar-usuario";
	private static final String MODAL_CONFIMAR_EXCLUSAO = "fragments/modal-confirmar-exclusao";

	@Autowired
	private UsuarioService servicoUsuario;

	@Autowired
	private RoleService servicoRole;

	@GetMapping("administracao")
	public String admin(ModelMap model, Cliente cliente, Servico servico, Usuario usuario, Role role) {
		model.addAttribute("AdminAtivo", "active");
		salvarRoles(role);
		model.addAttribute("listaUsuarios", servicoUsuario.buscarTodos());
		return __ADMINISTRACAO;
	}

	@GetMapping("editar/{usuario-login}")
	public String preEditar(@PathVariable("usuario-login") String login, Model model) {
		model.addAttribute("usuario", servicoUsuario.findById(login));
		return MODAL_EDITAR_USUARIO;
	}
	
	@GetMapping("visualizar/{usuario-login}")
	public String visualizarUsuario(@PathVariable("usuario-login") String login, Model model) {
		model.addAttribute("usuario", servicoUsuario.findById(login));
		Optional<Usuario> user = servicoUsuario.findById(login);
		System.err.println(user.get().getPassword());
		return MODAL_VISUALIZAR_USUARIO;
	}

	@PostMapping("/editar-usuario")
	public String editar(@Valid Usuario usuario, RedirectAttributes attr) {
		servicoUsuario.alterar(usuario);
		attr.addFlashAttribute("editou", true);
		return ATT_PAGINA__;
	}

	@GetMapping("/excluir/{usuario-login}")
	public String preExcluir(@PathVariable("usuario-login") String login, Model model) {
		model.addAttribute("usuario", servicoUsuario.findById(login));
		System.err.println(servicoUsuario.findById(login).toString());
		return MODAL_CONFIMAR_EXCLUSAO;
	}

	@PostMapping("/excluir-usuario")
	public String excluirPorLogin(Usuario usuario, RedirectAttributes attr){
		servicoUsuario.deletar(usuario.getLogin());
		attr.addFlashAttribute("deletou", true);
		return ATT_PAGINA__;
	}
	
	@PostMapping("/salvar-usuario")
	public String salvarUsuario(@Valid Usuario usuario, RedirectAttributes attr) {
		servicoUsuario.salvar(usuario);
		attr.addFlashAttribute("salvou", true);
		return ATT_PAGINA__;
	}

	@PostMapping("/salvar-role")
	public String salvarUsuario(@Valid Role role) {
		servicoRole.salvar(role);
		return __ADMINISTRACAO;
	}

	@ModelAttribute("tiporole")
	public TipoRoleEnum[] getTipoRole() {
		return TipoRoleEnum.values();
	}

	@ModelAttribute("cargo")
	public CargoUsuarioEnum[] getCargo() {	
		return CargoUsuarioEnum.values();
	}

	private void salvarRoles(Role role) {
		if (servicoRole.buscarTodos().isEmpty()) {
			Role r1 = new Role();
			Role r2 = new Role();
			role.setNomeRole("ROLE_ADMIN");
			r1.setNomeRole("ROLE_GERENTE");
			r2.setNomeRole("ROLE_USUARIO");
			servicoRole.salvar(r1);
			servicoRole.salvar(r2);
			servicoRole.salvar(role);
		}
	}

}
