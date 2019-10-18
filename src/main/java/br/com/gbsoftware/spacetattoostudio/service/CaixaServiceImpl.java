package br.com.gbsoftware.spacetattoostudio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import br.com.gbsoftware.spacetattoostudio.domain.enums.FormaPagamentoEnum;
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
		caixaRepository.save(caixa);
	}

	@Override
	public void editar(Caixa caixa) {
		caixa.setTotal(calculoValorTotalDia());
		caixa.setTotalAvista(calculoTotalAVista());
		caixa.setTotalCredito(calculoTotalCredito());
		caixa.setTotalDebito(calculoTotalDebito());
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

	@Override
	public List<EntradaSaida> getLancamentos() throws NullPointerException {
		if (getDiaAtual() == null) {
			List<EntradaSaida> dados = new ArrayList<EntradaSaida>();
			return dados;
		} else {
			return entradaSaidaServico.busarTodosDoDia(getDiaAtual().getId());
		}
	}

	@Override
	public Optional<BigDecimal> sumValorEntradaDia() {
		return getLancamentos().stream().filter(x -> TipoOperacaoEnum.ENTRADA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);
	}

	@Override
	public Optional<BigDecimal> sumValorSaidaDia() {
		return getLancamentos().stream().filter(x -> TipoOperacaoEnum.SAIDA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);
	}

	@Override
	public BigDecimal calculoValorTotalDia() {
		Optional<BigDecimal> entrada = sumValorEntradaDia();
		Optional<BigDecimal> saida = sumValorSaidaDia();
		if (entrada.isPresent() && saida.isPresent()) {
			BigDecimal dado = entrada.get().subtract(saida.get());
			return dado;
		} else if (entrada.isPresent() && !saida.isPresent()) {
			BigDecimal dadoSemSaida = entrada.get().subtract(new BigDecimal(0));
			return dadoSemSaida;
		} else if (!entrada.isPresent() && saida.isPresent()) {
			BigDecimal dadoSemEntrada = new BigDecimal(0).subtract(saida.get());
			return dadoSemEntrada;
		} else {
			BigDecimal dadoZero = new BigDecimal(0);
			return dadoZero;
		}
	}

	@Override
	public BigDecimal calculoTotalDebito() {
		Optional<BigDecimal> entrada = getLancamentos().stream()
				.filter(x -> FormaPagamentoEnum.DEBITO.equals(x.getFormaPagamento())
						&& TipoOperacaoEnum.ENTRADA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);

		Optional<BigDecimal> saida = getLancamentos().stream()
				.filter(x -> FormaPagamentoEnum.DEBITO.equals(x.getFormaPagamento())
						&& TipoOperacaoEnum.SAIDA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);

		if (entrada.isPresent() && saida.isPresent()) {
			BigDecimal dado = entrada.get().subtract(saida.get());
			return dado;
		} else if (entrada.isPresent() && !saida.isPresent()) {
			BigDecimal dadoSemSaida = entrada.get().subtract(new BigDecimal(0));
			return dadoSemSaida;
		} else if (!entrada.isPresent() && saida.isPresent()) {
			BigDecimal dadoSemEntrada = new BigDecimal(0).subtract(saida.get());
			return dadoSemEntrada;
		} else {
			BigDecimal dadoZero = new BigDecimal(0);
			return dadoZero;
		}
	}

	@Override
	public BigDecimal calculoTotalCredito() {
		Optional<BigDecimal> entrada = getLancamentos().stream()
				.filter(x -> FormaPagamentoEnum.CREDITO.equals(x.getFormaPagamento())
						&& TipoOperacaoEnum.ENTRADA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);

		Optional<BigDecimal> saida = getLancamentos().stream()
				.filter(x -> FormaPagamentoEnum.CREDITO.equals(x.getFormaPagamento())
						&& TipoOperacaoEnum.SAIDA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);

		if (entrada.isPresent() && saida.isPresent()) {
			BigDecimal dado = entrada.get().subtract(saida.get());
			return dado;
		} else if (entrada.isPresent() && !saida.isPresent()) {
			BigDecimal dadoSemSaida = entrada.get().subtract(new BigDecimal(0));
			return dadoSemSaida;
		} else if (!entrada.isPresent() && saida.isPresent()) {
			BigDecimal dadoSemEntrada = new BigDecimal(0).subtract(saida.get());
			return dadoSemEntrada;
		} else {
			BigDecimal dadoZero = new BigDecimal(0);
			return dadoZero;
		}
	}

	@Override
	public BigDecimal calculoTotalAVista() {
		Optional<BigDecimal> entrada = getLancamentos().stream()
				.filter(x -> FormaPagamentoEnum.A_VISTA.equals(x.getFormaPagamento())
						&& TipoOperacaoEnum.ENTRADA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);

		Optional<BigDecimal> saida = getLancamentos().stream()
				.filter(x -> FormaPagamentoEnum.A_VISTA.equals(x.getFormaPagamento())
						&& TipoOperacaoEnum.SAIDA.equals(x.getTipoOperacao()))
				.map(EntradaSaida::getValor).reduce(BigDecimal::add);

		if (entrada.isPresent() && saida.isPresent()) {
			BigDecimal dado = entrada.get().subtract(saida.get());
			return dado;
		} else if (entrada.isPresent() && !saida.isPresent()) {
			BigDecimal dadoSemSaida = entrada.get().subtract(new BigDecimal(0));
			return dadoSemSaida;
		} else if (!entrada.isPresent() && saida.isPresent()) {
			BigDecimal dadoSemEntrada = new BigDecimal(0).subtract(saida.get());
			return dadoSemEntrada;
		} else {
			BigDecimal dadoZero = new BigDecimal(0);
			return dadoZero;
		}
	}

	@Override
	public List<Caixa> buscarPorIntervalo(String dataInicial, String dataFinal) {
		return caixaRepository.findByInterval(dataInicial, dataFinal);
	}

	@Override
	public List<Caixa> buscarTodosMes(String relGeralMensal) {
		return caixaRepository.findByMes(relGeralMensal);
	}

	@Override
	public ArrayList<Object> relatorio(String relGeralMensal) {

		ArrayList<Object> relatorio = new ArrayList<>();

		List<Caixa> listaRelGeral = buscarTodosMes(relGeralMensal);

		if (!listaRelGeral.isEmpty()) {
			Optional<BigDecimal> totalGeral = listaRelGeral.stream().filter(x -> x.getTotal() != null)
					.map(Caixa::getTotal).reduce(BigDecimal::add);

			Optional<BigDecimal> totalCredito = listaRelGeral.stream().filter(x -> x.getTotalCredito() != null)
					.map(Caixa::getTotalCredito).reduce(BigDecimal::add);

			Optional<BigDecimal> totalDebito = listaRelGeral.stream().filter(x -> x.getTotalDebito() != null)
					.map(Caixa::getTotalDebito).reduce(BigDecimal::add);

			Optional<BigDecimal> totalAVista = listaRelGeral.stream().filter(x -> x.getTotalAvista() != null)
					.map(Caixa::getTotalAvista).reduce(BigDecimal::add);

			relatorio.add(totalGeral);
			relatorio.add(totalCredito);
			relatorio.add(totalDebito);
			relatorio.add(totalAVista);

			return relatorio;

		} else {
			return null;
		}
	}
}
