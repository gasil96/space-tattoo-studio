package br.com.gbsoftware.spacetattoostudio.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.gbsoftware.spacetattoostudio.domain.model.TokenResetarSenha;
import br.com.gbsoftware.spacetattoostudio.repository.TokenResetarSenhaRepository;

public class TokenResetarSenhaImpl implements TokenResetarSenhaService {

	@Autowired
	private TokenResetarSenhaRepository tokenResetSenhaRepository;

	@Override
	public void salvar(TokenResetarSenha tokenResetarSenha) {
		tokenResetSenhaRepository.save(tokenResetarSenha);
	}

}
