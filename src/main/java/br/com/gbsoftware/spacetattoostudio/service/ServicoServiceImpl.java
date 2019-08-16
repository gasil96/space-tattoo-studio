package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.model.Servico;
import br.com.gbsoftware.spacetattoostudio.repository.ServicoRepository;

@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public void salvar(Servico servico) {
		servicoRepository.save(servico);
	}

	@Override
	public void editar(Servico servico) {
		servicoRepository.save(servico);

	}

	@Override
	public void excluir(Long id) {
		servicoRepository.deleteById(id);
	}

	@Override
	public Servico buscarPorId(Long id) {
		return null; // TODO - servicoRepository.findById(id);
	}

	@Override
	public List<Servico> buscarTodos() {
		return servicoRepository.findAll();
	}

}
