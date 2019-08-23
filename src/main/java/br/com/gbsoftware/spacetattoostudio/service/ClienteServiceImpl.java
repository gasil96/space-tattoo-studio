package br.com.gbsoftware.spacetattoostudio.service;

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
		return clienteRepository. findAll();
	}

	@Override
	public void editar(Cliente cliente) {
		if (!cliente.getInstagram().isEmpty()) {
			cliente.setInstagram("@" + cliente.getInstagram());
		}
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> clienteNomeId() {
		return clienteRepository.clienteNomeId();
	}
	
}
