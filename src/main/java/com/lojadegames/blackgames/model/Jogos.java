package com.lojadegames.blackgames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_jogos")
public class Jogos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo titulo é obrigatório")
	@Size(min = 10, max = 200, message = "O Atributo titulo deve ter no minimo 10 caracteres e no maximo 200 caracteres")
	private String titulo;
	
	@NotBlank(message = "O atributo marca é obrigatório")
	@Size(min = 5, max = 50, message = "O Atributo marca deve ter no minimo 5 caracteres e no maximo 50 caracteres")
	private String marca;
	
	@NotNull(message = "O atributo valor é obrigatório")
	@Positive(message = "O valor deve ser maior do que zero")
	private BigDecimal valor;
	
	@NotBlank(message = "O atributo desenvolvedora é obrigatório")
	@Size(min = 5, max = 50, message = "O Atributo desenvolvedora deve ter no minimo 5 caracteres e no maximo 50 caracteres")
	private String desenvolvedora;
	
	@ManyToOne
	@JsonIgnoreProperties("jogos")
	private Categoria categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("jogos")
	private Plataforma plataforma;

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}
	
	
}
