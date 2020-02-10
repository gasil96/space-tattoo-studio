package br.com.gbsoftware.spacetattoostudio.service;

import java.math.BigDecimal;
import java.util.Comparator;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.model.Cliente;
import br.com.gbsoftware.spacetattoostudio.domain.model.EntradaCaixa;
import br.com.gbsoftware.spacetattoostudio.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EntradaCaixaService entradaSaidaServico;

	@Override
	public void salvar(Cliente cliente) {

		if (!cliente.getInstagram().isEmpty()) {
			cliente.setInstagram("@" + cliente.getInstagram());
		}
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> buscarPorInstagram(String instagram) {
		return clienteRepository.findByInstagram(instagram);
	}

	@Override
	public List<Cliente> buscarPorNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {

		return clienteRepository.findById(id);
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll().stream().sorted(Comparator.comparing(Cliente::getDataCadastro).reversed())
				.collect(Collectors.toList());
	}

	@Override
	public void editar(Cliente cliente) {
		if (!cliente.getInstagram().isEmpty()) {
			char primeiroCaractere = cliente.getInstagram().charAt(0);
			if (primeiroCaractere != '@') {
				cliente.setInstagram("@" + cliente.getInstagram());
			}
		}
		clienteRepository.save(cliente);
	}

	@Override
	public List<Cliente> getPorCadastroMes() {
		return clienteRepository.getPorCadastroMesAtual();
	}

	@Override
	public List<Cliente> getPorCadastroMesAnterio() {
		return clienteRepository.getPorCadastroMesPassado();
	}

	@Override
	public List<Cliente> getClienteIdInstaNome() {
		return clienteRepository.getClienteIdInstaNome();
	}

	@Override
	public BigDecimal calcularGastoTotalCliente(Cliente cliente) {
		BigDecimal gastoTotalCliente = entradaSaidaServico.buscarTodos().stream()
				.filter(x -> cliente.getId().equals(x.getCliente().getId())).map(EntradaCaixa::getValor)
				.reduce(BigDecimal::add).orElse(new BigDecimal(0));
		return gastoTotalCliente;
	}

	@Override
	public void updateCredito(BigDecimal valorCredito, Long idCliente) {
		clienteRepository.updateCredito(valorCredito, idCliente);
	}

	@Override
	public void updateStatus(String statusCliente, Long idCliente) {
		clienteRepository.updateStatus(statusCliente, idCliente);
	}
}
