package br.com.gbsoftware.spacetattoostudio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		cliente.setDataCadastro(LocalDateTime.now());
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
		Long id = (long) 1;
		return clienteRepository. findTitleById(id);
	}

	@Override
	public void editar(Cliente cliente) {
		cliente.setDataCadastro(cliente.getDataCadastro());
		clienteRepository.save(cliente);
	}
//	@PersistenceContext
//	private EntityManager manager;
//	
//	 public Cliente findBy(Long id) {
//	        TypedQuery<Cliente> query = manager
//	                .createQuery(
//	                        "select c.id, c.nome from cliente c  where c.id = :id ",
//	                        Cliente.class);
//	        query.setParameter("id", id);
//	        return query.getSingleResult();
//	    }
	

}
