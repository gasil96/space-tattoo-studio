package br.com.gbsoftware.spacetattoostudio.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.model.SaidaCaixa;
import br.com.gbsoftware.spacetattoostudio.repository.SaidaCaixaRepository;

@Service
public class SaidaCaixaServiceImpl implements SaidaCaixaService {

	@Autowired
	private SaidaCaixaRepository saidaRepository;

	@Override
	public void salvar(SaidaCaixa saidaCaixa) {
		saidaRepository.save(saidaCaixa);
	}

	@Override
	public void alterar(SaidaCaixa saidaCaixa) {
		saidaRepository.save(saidaCaixa);
	}

	@Override
	public void deletar(Long id) {
		saidaRepository.deleteById(id);
	}

	@Override
	public Optional<SaidaCaixa> buscarPorId(Long id) {
		return saidaRepository.findById(id);
	}

	@Override
	public List<SaidaCaixa> buscarTodos() {
		return saidaRepository.findAll();
	}

	@Override
	public List<SaidaCaixa> buscarTodosDoDia() {
		return saidaRepository.findByDiaAtual();
	}

	@Override
	public Optional<BigDecimal> sumTotalSaida() {
		return this.buscarTodosDoDia().stream().map(SaidaCaixa::getValor).reduce(BigDecimal::add);
	}
	
}
