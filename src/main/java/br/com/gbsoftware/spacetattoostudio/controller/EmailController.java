package br.com.gbsoftware.spacetattoostudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("email")
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("enviar-email-default")
	public String sendMail(Model model) {
		SimpleMailMessage message = new SimpleMailMessage();
	
		message.setText("TEste");
		message.setSubject("@@@");
		message.setTo("gasil96@gmail.com");
		message.setFrom("laland@gmail.com");

		try {
			mailSender.send(message);
			
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao enviar email.";
		}
	}

}
