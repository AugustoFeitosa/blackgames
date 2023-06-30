package com.lojadegames.blackgames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.lojadegames.blackgames.model.Plataforma;

public interface PlataformaRepository  extends JpaRepository<Plataforma, Long> {

	public List<Plataforma> findAllByPlataformaContainingIgnoreCase(@Param("plataforma") String plataforma);
}
