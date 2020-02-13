package br.com.gbsoftware.spacetattoostudio.repository;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gbsoftware.spacetattoostudio.domain.model.Caixa;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long>{

}
