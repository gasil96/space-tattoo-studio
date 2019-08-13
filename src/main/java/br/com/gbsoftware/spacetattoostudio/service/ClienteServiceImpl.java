package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gbsoftware.spacetattoostudio.dao.ClienteDao;
import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

@Service @Transactional
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteDao dao;
	
	@Override
	public void salvar(Cliente cliente) {
		dao.save(cliente);
	}

	@Override
	public void editar(Cliente cliente) {
		dao.update(cliente);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override @Transactional(readOnly = false)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = false)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

}
