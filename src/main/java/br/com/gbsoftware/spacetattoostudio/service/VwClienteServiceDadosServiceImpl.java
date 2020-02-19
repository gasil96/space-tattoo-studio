package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.vw.VwClienteServicoDados;
import br.com.gbsoftware.spacetattoostudio.repository.VwClienteServicoDadosRepository;

@Service
public class VwClienteServiceDadosServiceImpl implements VwClienteServicoDadosService{

	@Autowired
	private VwClienteServicoDadosRepository vwClienteServiceDadosRepository;
	
	@Override
	public List<VwClienteServicoDados> buscarTodos() {
		return vwClienteServiceDadosRepository.findAll();
	}

}
