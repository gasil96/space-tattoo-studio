package br.com.gbsoftware.spacetattoostudio.controller.error;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
@Component
public class PaginaErro implements ErrorViewResolver{

	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> map) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("status", status.value());

		switch (status.value()) {
			case 404:
				model.addObject("error", "Página não encontrada ");
				model.addObject("message", "A url para a página '  " + map.get("path") + "' Não existe.");
				break;
			case 403:
				model.addObject("error", "Usuario sem permissão para acessar essa página e/ou executar essa ação");
				model.addObject("message", "A url para a página '  " + map.get("path") + "' Não existe.");
				break;
			case 500:
				model.addObject("error", "Erro interno no servidor");
				model.addObject("message", "Ocorreu um erro, porfavor tenta mais tarde!");
				break;
			default:
				model.addObject("error", map.get("error"));
				model.addObject("message", map.get("message"));
				break;
			}
			return model;
	}
}
