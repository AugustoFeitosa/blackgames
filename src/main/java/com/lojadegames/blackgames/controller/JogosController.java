package com.lojadegames.blackgames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lojadegames.blackgames.model.Jogos;
import com.lojadegames.blackgames.repository.CategoriaRepository;
import com.lojadegames.blackgames.repository.JogosRepository;
import com.lojadegames.blackgames.repository.PlataformaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jogos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JogosController {
	
	@Autowired
	private JogosRepository jogosRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	 @GetMapping
	public ResponseEntity <List<Jogos>> getAll(){
		return ResponseEntity.ok(jogosRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Jogos> getById(@PathVariable Long id){
		return jogosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Jogos>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(jogosRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Jogos> post(@Valid @RequestBody Jogos jogos){
		if(categoriaRepository.existsById(jogos.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(jogosRepository.save(jogos));
		
		throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Gênero não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Jogos> put(@Valid @RequestBody Jogos jogos){
		if(jogosRepository.existsById(jogos.getId())) {
			
			if(categoriaRepository.existsById(jogos.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(jogosRepository.save(jogos));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gênero não existe!", null);
			
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
		
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Jogos> jogos = jogosRepository.findById(id);
		
		if(jogos.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		jogosRepository.deleteById(id);
	

}
}
