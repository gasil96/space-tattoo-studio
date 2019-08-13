package br.com.gbsoftware.spacetattoostudio.dao;

import java.util.List;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;

public interface ClienteDao {

	void save(Cliente cliente);
	
	void update(Cliente cliente);
	
	void delete(Long id);
	
	Cliente findById(Long id);
	
	List<Cliente> findAll();
	
}
