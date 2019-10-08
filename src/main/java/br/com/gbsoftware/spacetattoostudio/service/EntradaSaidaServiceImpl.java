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
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaSaida;
import br.com.gbsoftware.spacetattoostudio.repository.EntradaSaidaRepository;

@Service
public class EntradaSaidaServiceImpl implements EntradaSaidaService {

	@Autowired
	private EntradaSaidaRepository entradaSaidaRepository;

	@Autowired
	private ClienteService servicoCliente;

	@Override
	public void salvar(EntradaSaida entradaSaida) {
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
	public void editar(EntradaSaida entradaSaida) {
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
	public Optional<EntradaSaida> buscarPorId(Long id) {
		return entradaSaidaRepository.findById(id);
	}

	@Override
	public List<EntradaSaida> buscarTodos() {
		return entradaSaidaRepository.findAll();
	}

	@Override
	public List<EntradaSaida> busarTodosDoDia(Long iDCaixaFK) {
		return entradaSaidaRepository.findByEntradaSaida(iDCaixaFK);
	}

	@Override
	public void addGastoTotalCliente(EntradaSaida entradaSaida) {
		Optional<Cliente> cliente = servicoCliente.buscarPorId(entradaSaida.getCliente().getId());
		if (cliente.isPresent()) {
			cliente.get().setSaldo(cliente.get().getSaldo().add(entradaSaida.getValor()));
		}
	}

	// TODO - REMOVER ENTRADA DO GASTO TOTAL METODO A SER IMPL
	@Override
	public void removeGastoTotalCliente(EntradaSaida entradaSaida) {
		Optional<Cliente> cliente = servicoCliente.buscarPorId(entradaSaida.getCliente().getId());
		if (cliente.isPresent()) {
			cliente.get().setSaldo(cliente.get().getSaldo().subtract(entradaSaida.getValor()));
		}
	}
}
