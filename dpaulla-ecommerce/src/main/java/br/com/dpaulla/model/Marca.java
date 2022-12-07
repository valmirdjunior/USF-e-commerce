package br.com.dpaulla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "tb_marca")
public @Data class Marca {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="marcaId")
	private int marcaId;
		
	@Column(name="marcaDescricao")
	private String marcaDescricao;	
	
	@Column(name="marcaNivel")
	private String marcaNivel;
	
	@Column(name="marcaLogo")
	private String marcaLogo;

}
