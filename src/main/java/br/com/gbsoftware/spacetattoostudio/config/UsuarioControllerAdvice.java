package br.com.gbsoftware.spacetattoostudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import br.com.gbsoftware.spacetattoostudio.service.UsuarioService;

@ControllerAdvice
public class UsuarioControllerAdvice {
	
	@Autowired
	private UsuarioService servicoUsuario;

	
//	@ModelAttribute("usuarioLogadoNome")
//	public String getNomeUsuarioLogado() {
//		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
//		String login = usuarioLogado.getName();
//		String usuarioLogadoNome = servicoUsuario.findById(login).get().getNomeCompleto();
//		return usuarioLogadoNome;
//	}
//	
//	@ModelAttribute("usuarioLogadoCargo")
//	public String getCargoUsuarioLogado() {
//		Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
//		String login = usuarioLogado.getName();
//		String usuarioLogadoCargo = servicoUsuario.findById(login).get().getCargo().toString();
//		return usuarioLogadoCargo;
//	}
	
}
