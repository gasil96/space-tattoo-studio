package br.com.gbsoftware.spacetattoostudio.config;

/**
 * <b>Gabriel S. Sofware</b>
 * 
 * @author Gabriel Silva - gasil96@gmail.com
 * @version 2019 - Criação
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/financeiro/detalhamento").hasAnyRole("ADMIN", "GERENTE", "USUARIO")
				.antMatchers(HttpMethod.GET, "/promocional/promo").hasAnyRole("ADMIN", "GERENTE", "USUARIO")
				.antMatchers(HttpMethod.GET, "/adm/administracao").hasRole("ADMIN").anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		http.sessionManagement().maximumSessions(2).and();
		http.sessionManagement().sessionFixation().none();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
		auth.inMemoryAuthentication().withUser("adm").password("{noop}Q$&u1d&#51@").roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/data/**", "/fonts/**", "/icons-reference/**", "/img/**", "/js/**",
				"/vendor/**");
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
