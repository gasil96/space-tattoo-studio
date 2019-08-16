package br.com.gbsoftware.spacetattoostudio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.enums.StatusClienteEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void salvar(Cliente cliente) {
		cliente.setDataCadastro(LocalDateTime.now());
		clienteRepository.save(cliente);
	}

	@Override
	public void editar(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {

		return clienteRepository.findById(id);
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
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

}
