package br.com.gbsoftware.spacetattoostudio.service;

import java.time.LocalDateTime;
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
		cliente.setInstagram("@"+cliente.getInstagram());
		cliente.setDataCadastro(LocalDateTime.now());
		clienteRepository.save(cliente);
	}

	@Override
	public void editar(Cliente cliente) {
		Optional<Cliente> clienteLocalizado = clienteRepository.findById(cliente.getId());
		if(clienteLocalizado.isPresent()) {
		clienteRepository.save(cliente);
		System.err.println("ALTEROU");
		}
	}

	@Override
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
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

}
