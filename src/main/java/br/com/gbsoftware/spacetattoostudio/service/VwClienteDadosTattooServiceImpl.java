package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.vw.VwClienteDadosTattoo;
import br.com.gbsoftware.spacetattoostudio.repository.VwClienteDadosTattooRepository;

@Service
public class VwClienteDadosTattooServiceImpl implements VwClienteDadosTattooService{

	@Autowired
	private VwClienteDadosTattooRepository vwClienteServiceDadosRepository;
	
	@Override
	public List<VwClienteDadosTattoo> buscarTodos() {
		return vwClienteServiceDadosRepository.findAll();
	}

}
