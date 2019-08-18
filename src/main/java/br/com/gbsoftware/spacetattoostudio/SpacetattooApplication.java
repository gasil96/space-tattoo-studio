package br.com.gbsoftware.spacetattoostudio;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
