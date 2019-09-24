package br.com.gbsoftware.spacetattoostudio.controller;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("promocional")
public class PromocionalController {

	private static final String PAGINA_PROMOCIONAL = "promocional/descontos-promocoes";
	
	@GetMapping("promo")
	public String promocional(Model model) {
		model.addAttribute("classActivePromocional", "active");
		return PAGINA_PROMOCIONAL;
	}
}
