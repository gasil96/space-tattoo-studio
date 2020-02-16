package br.com.gbsoftware.spacetattoostudio.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
		dataOperacao(entradaCaixa);
		valorComDesconto(entradaCaixa);
		entradaRepository.save(entradaCaixa);
	}

	@Override
	public void alterar(EntradaCaixa entradaCaixa) {
		valorComDesconto(entradaCaixa);
		dataOperacao(entradaCaixa);
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

	private void dataOperacao(EntradaCaixa entradaCaixa) {
		entradaCaixa.setHorarioOperacao(LocalDateTime.now());

		if (entradaCaixa.getPorcentagemDesconto() != null) {

		}
	}

	private void valorComDesconto(EntradaCaixa entradaCaixa) {
		if (entradaCaixa.getPorcentagemDesconto() != null) {
			BigDecimal desconto = new BigDecimal(entradaCaixa.getPorcentagemDesconto()).divide(new BigDecimal(100));
			BigDecimal valorDesconto = entradaCaixa.getValor().multiply(desconto);
			entradaCaixa.setValor(entradaCaixa.getValor().subtract(valorDesconto));
		}
	}

}
