package br.com.gbsoftware.spacetattoostudio.service;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void salvar(Cliente cliente) {

		if (!cliente.getInstagram().isEmpty()) {
			cliente.setInstagram("@" + cliente.getInstagram());
		}
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> buscarPorInstagram(String instagram) {
		return clienteRepository.findByInstagram(instagram);
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {

		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public void editar(Cliente cliente) {
		if (!cliente.getInstagram().isEmpty()) {
			char primeiroCaractere = cliente.getInstagram().charAt(0);
			if (primeiroCaractere != '@') {
				cliente.setInstagram("@" + cliente.getInstagram());
			}
		}
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> getPorCadastroMes() {
		return clienteRepository.getPorCadastroMesAtual();
	}

	@Override
	public List<Cliente> getPorCadastroMesAnterio() {
		return clienteRepository.getPorCadastroMesPassado();
	}

	@Override
	public List<Cliente> getClienteIdInstaNome() {
		return clienteRepository.getClienteIdInstaNome();
	}

	@Override
	public void salvarOptional(Optional<Cliente> cliente) {
//		System.err.println(cliente.toString());
	}
}
