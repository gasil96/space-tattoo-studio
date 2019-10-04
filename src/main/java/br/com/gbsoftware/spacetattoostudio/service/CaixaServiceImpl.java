package br.com.gbsoftware.spacetattoostudio.service;

import java.math.BigDecimal;
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

import br.com.gbsoftware.spacetattoostudio.domain.enums.TipoOperacaoEnum;
import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;
import br.com.gbsoftware.spacetattoostudio.repository.CaixaRepository;

@Service
public class CaixaServiceImpl implements CaixaService {

	@Autowired
	private CaixaRepository caixaRepository;

	@Autowired
	private EntradaSaidaService entradaSaidaServico;

	@Override
	public void salvar(Caixa caixa) {
		System.err.println("------qtd lancamentos------> " + getLancamentos().size());
		System.err.println("-----vlr somatorio entrada-------> " + sumValorEntradaDia().get());
		System.err.println("-----vlr somatorio saida-------> " + sumValorSaidaDia().get());
		System.err.println("-----total caixa-------> " + calculoValorTotalDia());
//		caixaRepository.save(caixa);
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

	// TODO - BUSCAR TODOS LANCAMENTOS DO DIA ENTRADA/SAIDA
	@Override
	public List<EntradaSaida> getLancamentos() {
		return entradaSaidaServico.busarTodosDoDia(getDiaAtual().getId());
	}

	// TODO - SOMA VALORES ENTRADA DO DIA
	@Override
	public Optional<BigDecimal> sumValorEntradaDia() {
		return getLancamentos().stream().filter(x -> TipoOperacaoEnum.ENTRADA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);
	}

	// TODO - SOMA VALORES SAIDA DO DIA
	@Override
	public Optional<BigDecimal> sumValorSaidaDia() {
		return getLancamentos().stream().filter(x -> TipoOperacaoEnum.SAIDA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);
	}
	
	// TODO - SOMA TOTAL CAIXA 
	@Override
	public BigDecimal calculoValorTotalDia() {
		Optional<BigDecimal> entrada = sumValorEntradaDia();
		Optional<BigDecimal> saida = sumValorSaidaDia();
		return entrada.get().subtract(saida.get());
	}

}
