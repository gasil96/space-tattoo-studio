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
		clienteSemCadastro(entradaCaixa);
		entradaRepository.save(entradaCaixa);
	}

	@Override
	public void alterar(EntradaCaixa entradaCaixa) {
		clienteSemCadastro(entradaCaixa);
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

	@Override
	public List<EntradaCaixa> buscarTodosDoDia() {
		return entradaRepository.findByDoDia();
	}

	@Override
	public List<EntradaCaixa> buscarTodosIntervalo(LocalDateTime inicio, LocalDateTime fim){
		return entradaRepository.findByIntervalo(inicio, fim);
	}
	
	private void clienteSemCadastro(EntradaCaixa entradaCaixa) {
		if (entradaCaixa.getCliente() != null && entradaCaixa.getCliente().getId() == null)
			entradaCaixa.setCliente(null);
	}

	@Override
	public Optional<BigDecimal> sumTotalEntrada() {
		return this.buscarTodosDoDia().stream().map(EntradaCaixa::getValor).reduce(BigDecimal::add);
	}

}
