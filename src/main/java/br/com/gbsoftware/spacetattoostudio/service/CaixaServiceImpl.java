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

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.repository.CaixaRepository;

@Service
public class CaixaServiceImpl implements CaixaService {

	@Autowired
	private CaixaRepository caixaRepository;

	@Override
	public void salvar(Caixa caixa) {
		caixaRepository.save(caixa);
	}

	@Override
	public void editar(Caixa caixa) {
		caixaRepository.saveAndFlush(caixa);
	}

	@Override
	public void detelar(Long id) {
		caixaRepository.deleteById(id);
	}

	@Override
	public Optional<Caixa> buscarPorId(Long id) {
		return caixaRepository.findById(id);
	}

	@Override
	public List<Caixa> buscarTodos() {
		return caixaRepository.findAll();
	}






}
