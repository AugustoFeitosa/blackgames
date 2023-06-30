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

import com.lojadegames.blackgames.model.Categoria;
import com.lojadegames.blackgames.model.Plataforma;
import com.lojadegames.blackgames.repository.PlataformaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/plataforma")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlataformaController {
	
		
		    @Autowired
		    private PlataformaRepository plataformaRepository;
		    
		    @GetMapping
		    public ResponseEntity<List<Plataforma>> getAll(){
		        return ResponseEntity.ok(plataformaRepository.findAll());
		    }
		    
		    @GetMapping("/{id}")
		    public ResponseEntity<Plataforma> getById(@PathVariable Long id){
		        return plataformaRepository.findById(id)
		            .map(resposta -> ResponseEntity.ok(resposta))
		            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		    }
		    
		    @GetMapping("/plataforma/{plataforma}")
		    public ResponseEntity<List<Plataforma>> getByTitle(@PathVariable 
		    String plataforma){
		        return ResponseEntity.ok(plataformaRepository
		            .findAllByPlataformaContainingIgnoreCase(plataforma));
		    }
		    
		    @PostMapping
		    public ResponseEntity<Plataforma> post(@Valid @RequestBody Plataforma plataforma){
		        return ResponseEntity.status(HttpStatus.CREATED)
		                .body(plataformaRepository.save(plataforma));
		    }
		    
		    @PutMapping
		    public ResponseEntity<Plataforma> put(@Valid @RequestBody Plataforma plataforma){
		        return plataformaRepository.findById(plataforma.getId())
		            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
		            .body(plataformaRepository.save(plataforma)))
		            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		    }
		    
		    @ResponseStatus(HttpStatus.NO_CONTENT)
		    @DeleteMapping("/{id}")
		    public void delete(@PathVariable Long id) {
		        Optional<Plataforma> plataforma = plataformaRepository.findById(id);
		        
		        if(plataforma.isEmpty())
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		        
		        plataformaRepository.deleteById(id);              
		    }
	
}
