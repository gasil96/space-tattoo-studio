package br.com.gbsoftware.spacetattoostudio.service;

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

//		BigDecimal big1 = new BigDecimal("1000.0"); // TODO - REMOVER APOS CONCLUSAO METODO
		
		/**
		 * TODO - Cálculo do total aqui
		 * */
//		caixa.setTotal(big1); // TODO - AQUI VAI O TOTAL CALCULADO AO FECHAR CAIXA
		
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

	@Override
	public Caixa getDiaAtual() {
		return caixaRepository.getCaixaDiaAtual();
	}

}
