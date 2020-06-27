package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.vw.VwArrecadacaoAnualTipo;
import br.com.gbsoftware.spacetattoostudio.repository.VwArrecadacaoAnualTipoRepository;

@Service
public class VwClienteDadosPiercingServiceImpl implements VwArrecadacaoAnualTipoService{

	@Autowired
	private VwArrecadacaoAnualTipoRepository vwArrecadacaoRepository;

	@Override
	public List<VwArrecadacaoAnualTipo> buscarTodos() {
		return vwArrecadacaoRepository.findAll();
	}


}
