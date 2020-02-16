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

import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.repository.EntradaCaixaRepository;

@Service
public class EntradaCaixaServiceImpl implements EntradaCaixaService {

	@Autowired
	private EntradaCaixaRepository entradaRepository;

	@Override
	public void salvar(EntradaCaixa entradaCaixa) {
		entradaRepository.save(entradaCaixa);
	}

	@Override
	public void alterar(EntradaCaixa entradaCaixa) {
		entradaRepository.save(entradaCaixa);
	}

	@Override
	public void deletar(Long id) {
		entradaRepository.deleteById(id);
	}

	@Override
	public Optional<EntradaCaixa> buscarPorId(Long id) {
		return entradaRepository.findById(id);
	}

	@Override
	public List<EntradaCaixa> buscarTodos() {
		return entradaRepository.findAll();
	}
}
