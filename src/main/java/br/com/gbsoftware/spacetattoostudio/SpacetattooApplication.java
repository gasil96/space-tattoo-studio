package br.com.gbsoftware.spacetattoostudio;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@SpringBootApplication
public class SpacetattooApplication {

	@Value("${xpto}")
	public String xpto;

	public static void main(String[] args) {
		SpringApplication.run(SpacetattooApplication.class, args);
	}

	@EventListener(value = ApplicationReadyEvent.class)
	public void teste() {
		System.out.println("############## " + xpto);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("gasil96@gmail.com");
		mailSender.setPassword("#euamo694314");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}
