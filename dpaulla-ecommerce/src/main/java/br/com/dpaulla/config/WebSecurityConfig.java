package br.com.dpaulla.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;

	private static Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("enter on webSecurity: {}", http);
		http
		.authorizeRequests()
		//.antMatchers("/acesso-restrito").hasRole("admin")
 		.antMatchers("/resources/**", "/assets/**", "/static/**", "/css/**", "/js/**", "/images/**",
						"/produtos/**", "/webapp/**", "/WEB-INF/**", "/pages/**", "/registration", "/", "/dpaulla",
						"/login", "/para_seu_cabelo", "/suporte", "/tipos_de_cabelo", "/marcas", "/promocao", "/dicas",
						"/marca", "/produto", "/teste", "/cadastro","/session", "/destroy", "/persistMessage", "/meuCarrinhoRemoverProduto",
						"/meu-carrinho", "/meuCarrinhoComprarAgora", "/valorFrete", "/acesso-restrito", "/acesso-restrito-cadastrar-marca", 
						"/cadastrarMarca", "/acesso-restrito-cadastrar-produto", "/cadastrarProduto").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().permitAll();
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		log.info("enter on customAuthenticationManager bean: {}");
		return authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder()).getUserDetailsService();
	}
}