package br.com.gbsoftware.spacetattoostudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@ControllerAdvice
public class UsuarioControllerAdvice {
	
	@Autowired
	private UsuarioService servicoUsuario;

	
	@ModelAttribute("usuarioLogadoNome")
	public String getNomeUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
			String login = usuarioLogado.getName();
			if(usuarioLogado.getName().equals("adm")) {
				return "Administrador";
			}
			String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
			return usuarioLogadoNome;
		}else {
			return null;
		}
	}
	
	@ModelAttribute("usuarioLogadoCargo")
	public String getCargoUsuarioLogado() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
		String login = usuarioLogado.getName();
		if(usuarioLogado.getName().equals("adm")) {
			return "Principal Conta";
		}
		String usuarioLogadoCargo = servicoUsuario.findById(login).get().getCargo().toString();
		String cargoTratado = usuarioLogadoCargo.substring(0,1).concat(usuarioLogadoCargo.substring(1).toLowerCase());
		return cargoTratado;
		}else {
			return null;
		}
	}
	
}
