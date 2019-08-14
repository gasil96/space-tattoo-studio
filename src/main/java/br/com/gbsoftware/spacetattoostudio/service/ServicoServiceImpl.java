package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.repository.ServicoRepository;

@Service
public class ServicoServiceImpl implements ServicoService{

	@Autowired
	private ServicoRepository servicoDao;

	@Override
	public void salvar(Servico servico) {
		
	}

	@Override
	public void editar(Servico servico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Servico buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Servico> buscarTodos() {
		return servicoDao.findAll();
	}
	

	
}
