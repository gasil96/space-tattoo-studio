package br.com.gbsoftware.spacetattoostudio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gbsoftware.spacetattoostudio.domain.vw.VwClienteDadosPiercing;
import br.com.gbsoftware.spacetattoostudio.repository.VwClienteDadosPiercingRepository;

@Service
public class VwClienteDadosPiercingServiceImpl implements VwClienteDadosPiercingService{

	@Autowired
	private VwClienteDadosPiercingRepository vwClienteDadosPiercingRepository;

	@Override
	public List<VwClienteDadosPiercing> buscarTodos() {
		return vwClienteDadosPiercingRepository.findAll();
	}
	

}
