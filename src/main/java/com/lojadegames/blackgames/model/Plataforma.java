package com.lojadegames.blackgames.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_plataforma")
public class Plataforma {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull(message = "O Atributo gênero é obrigatório")
		private String plataforma;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "plataforma", cascade = CascadeType.REMOVE)
		@JsonIgnoreProperties("plataforma")
		private List<Jogos> jogos;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPlataforma() {
			return plataforma;
		}

		public void setPlataforma(String plataforma) {
			this.plataforma = plataforma;
		}

		public List<Jogos> getJogos() {
			return jogos;
		}

		public void setJogos(List<Jogos> jogos) {
			this.jogos = jogos;
		}

	
	
}
