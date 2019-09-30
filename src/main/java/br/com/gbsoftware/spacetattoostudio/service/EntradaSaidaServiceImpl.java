package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;
import br.com.gbsoftware.spacetattoostudio.repository.EntradaSaidaRepository;

@Service
public class EntradaSaidaServiceImpl implements EntradaSaidaService{

	@Autowired
	private EntradaSaidaRepository entradaSaidaRepository;
	
	@Override
	public void salvar(EntradaSaida entradaSaida) {

		entradaSaidaRepository.save(entradaSaida);
	}

	@Override
	public void editar(EntradaSaida entradaSaida) {

		entradaSaidaRepository.saveAndFlush(entradaSaida);
	}

	@Override
	public void deletar(Long id) {
		entradaSaidaRepository.deleteById(id);
	}

	@Override
	public Optional<EntradaSaida> buscarPorId(Long id) {
		return entradaSaidaRepository.findById(id);
	}

	@Override
	public List<EntradaSaida> buscarTodos() {
		return entradaSaidaRepository.findAll();
	}

}
