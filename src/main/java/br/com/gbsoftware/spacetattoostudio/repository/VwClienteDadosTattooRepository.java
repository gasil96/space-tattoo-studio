package br.com.gbsoftware.spacetattoostudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.vw.VwClienteDadosTattoo;

@Repository
public interface VwClienteDadosTattooRepository extends JpaRepository<VwClienteDadosTattoo, Long>{

}
