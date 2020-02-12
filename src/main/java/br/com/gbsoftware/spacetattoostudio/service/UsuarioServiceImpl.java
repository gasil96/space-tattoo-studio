package br.com.gbsoftware.spacetattoostudio.service;

import java.util.ArrayList;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.enums.CargoUsuarioEnum;
import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoRoleEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Role;
import br.com.gbsoftware.spacetattoostudio.domain.model.Usuario;
import br.com.gbsoftware.spacetattoostudio.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> buscarTodos() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void salvar(Usuario usuario) {

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		setRoleCargo(usuario);
		usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findById(String login) {
		return usuarioRepository.findById(login);
	}

	@Override
	public void deletar(String login) {
		usuarioRepository.deleteById(login);
	}

	@Override
	public void alterar(Usuario usuario) {
		setRoleCargo(usuario);
		usuarioRepository.save(usuario);
	}

	private void setRoleCargo(Usuario usuario) {
		Role role = new Role();
		List<Role> roles = new ArrayList<Role>();

		if (usuario.getCargo().equals(CargoUsuarioEnum.GERENTE)) {
			role.setNomeRole(TipoRoleEnum.ROLE_GERENTE.name());
		} else if (usuario.getCargo().equals(CargoUsuarioEnum.ADMINISTRADOR)) {
			role.setNomeRole(TipoRoleEnum.ROLE_ADMIN.name());
		} else if (usuario.getCargo().equals(CargoUsuarioEnum.FUNCIONARIO)) {
			role.setNomeRole(TipoRoleEnum.ROLE_USUARIO.name());
		}
		usuario.setRoles(roles);
		roles.add(role);
	}

	@Override
	public void deletarPorUsuario(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
}
