package br.com.dpaulla.model;

import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
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

	    private Set<Role> roles;
	    
}