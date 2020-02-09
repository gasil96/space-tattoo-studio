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

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.repository.EntradaCaixaRepository;

@Service
public class EntradaCaixaServiceImpl implements EntradaCaixaService {

	@Autowired
	private EntradaCaixaRepository entradaSaidaRepository;

	@Autowired
	private ClienteService servicoCliente;

	@Override
	public void salvar(EntradaCaixa entradaSaida) {
		addGastoTotalCliente(entradaSaida);
		if (entradaSaida.getDesconto() == null) {
			entradaSaidaRepository.save(entradaSaida);
		} else {
			BigDecimal resultadoValorComDesconto = (entradaSaida.getValor().multiply(entradaSaida.getDesconto())
					.divide(new BigDecimal(100)));
			entradaSaida.setValor(entradaSaida.getValor().subtract(resultadoValorComDesconto));
			entradaSaidaRepository.save(entradaSaida);
		}
	}

	@Override
	public void editar(EntradaCaixa entradaSaida) {
		addGastoTotalCliente(entradaSaida);
		entradaSaida.setHorarioOperacao(LocalDateTime.now());
		if (entradaSaida.getDesconto() == null) {

			entradaSaidaRepository.save(entradaSaida);
		} else {
			BigDecimal resultadoValorComDesconto = (entradaSaida.getValor().multiply(entradaSaida.getDesconto())
					.divide(new BigDecimal(100)));
			entradaSaida.setValor(entradaSaida.getValor().subtract(resultadoValorComDesconto));
			entradaSaidaRepository.save(entradaSaida);
		}
	}

	@Override
	public void deletar(Long id) {
		entradaSaidaRepository.deleteById(id);
	}

	@Override
	public Optional<EntradaCaixa> buscarPorId(Long id) {
		return entradaSaidaRepository.findById(id);
	}

	@Override
	public List<EntradaCaixa> buscarTodos() {
		return entradaSaidaRepository.findAll();
	}

	@Override
	public List<EntradaCaixa> busarTodosDoDia(Long iDCaixaFK) {
		return entradaSaidaRepository.findByEntradaSaida(iDCaixaFK);
	}

	public void addGastoTotalCliente(EntradaCaixa entradaSaida) {
		BigDecimal gastoLista = buscarTodos().stream()
				.filter(x -> entradaSaida.getCliente().getId().equals(x.getCliente().getId()))
				.map(EntradaCaixa::getValor).reduce(BigDecimal::add).orElse(new BigDecimal(0));
		BigDecimal gastoTotalCliente = gastoLista.add(entradaSaida.getValor());
		Optional<Cliente> cliente = servicoCliente.buscarPorId(entradaSaida.getCliente().getId());
		if (cliente.isPresent()) {
			cliente.get().setSaldo(gastoTotalCliente);
		}
	}

	@Override
	public List<EntradaCaixa> buscarPorIntervalo(String dataInicial, String dataFinal) {
		return entradaSaidaRepository.findByInterval(dataInicial, dataFinal);
	}
}
