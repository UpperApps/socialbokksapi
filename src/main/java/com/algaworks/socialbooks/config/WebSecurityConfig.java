package com.algaworks.socialbooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		//Configura uma autenticação global em memória.
		auth.inMemoryAuthentication().withUser("algaworks")
		.password("s3nh4").roles("USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		
		http.
			authorizeRequests()
			.antMatchers("/h2-console/**").permitAll() //Libera o acesso ao h2-console sem autenticação. 
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //Permite o acesso de aplicações de origens diferentes (Cross-Origin)
			.anyRequest().authenticated() //Todas as requisições terão que estar autenticadas.
			.and()
				.httpBasic() //Pela autenticação básica do HTTP.
			.and()
				.csrf().disable();//E com a definição de certificado desabilitada, pois a API será pública.
	}
}
