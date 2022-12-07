package br.com.dpaulla.mail.server.model.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.dpaulla.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public @Data class UserBanco implements Serializable{
		private static final long serialVersionUID = -4499140210680610037L;

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long userId;

	 	private String nome;
	 	
	 	private String sobrenome;
	 	
	 	private String cpf;
	 	
	 	private String datanascimento;
	 	
	 	private String telefone;
	 	
	 	private String celular;
	 	
	 	private String cep;
	 	
	 	private String logradouro;
	 	
	 	private String logradouronumero;
	 	
	 	private String complemento;
	 	
	 	private String bairro;
	 	
	 	private String cidade;
	 	
	 	private String estado;
	 	
	    private String username;

	    private String password;

	    @Transient
	    private String passwordConfirm;
	    
	    @Transient
	    private String usernameConfirm;
	    
	    @Transient
	    private Set<Role> roles;
	    
}