package br.com.gbsoftware.spacetattoostudio;

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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@SpringBootApplication
public class SpacetattooApplication {
	
	@Value("${xpto}")
	public String xpto;

	public static void main(String[] args) {
		SpringApplication.run(SpacetattooApplication.class, args);
	}
	
	@EventListener(value=ApplicationReadyEvent.class)
	public void teste() {
		System.out.println("############## "+ xpto );
	}

}
