package br.com.gbsoftware.spacetattoostudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Gabriel Silva - gasil96@gmail.com
 * 
 * @version 2019
 * 
 * */

@SpringBootApplication
public class SpacetattooApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpacetattooApplication.class, args);
	}
	
}
