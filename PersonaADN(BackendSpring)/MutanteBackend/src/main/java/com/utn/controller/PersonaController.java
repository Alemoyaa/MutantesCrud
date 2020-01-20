package com.utn.controller;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RestController;

import com.utn.dto.PersonaAdnDTO;
import com.utn.service.PersonaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/persona-adn")
@Transactional
public class PersonaController {

	private PersonaService servicio;
	
	public PersonaController(PersonaService servicio) {
		this.servicio=servicio;
	}
	
	   @GetMapping("/")
	    public ResponseEntity getAll() {
	        try {
	            return ResponseEntity.ok().body(servicio.findAll());
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("{\"message\": \"Error. Please try again later.\"}");
	        }
	    }
	   
	   @GetMapping("/{id}")
	    public ResponseEntity getOne(@PathVariable int id) {
	        try {
	            return ResponseEntity.ok().body(servicio.findById(id));
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("{\"message\": \"Error. Please check the ID, and try again later.\"}");
	        }
	    }

	    @PostMapping("/")
	    public ResponseEntity post(@RequestBody PersonaAdnDTO dto) {
	    	
	    	try {
	    		return ResponseEntity.ok().body(servicio.save(dto));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Error. Please check the BODY request, and try again later.\"}");
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity put(@PathVariable int id, @RequestBody PersonaAdnDTO dto) {
	        try {
	            return ResponseEntity.ok().body(servicio.update(id, dto));
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("{\"message\": \"Error. Please check the ID or BODY request, and try again later.\"}");
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity delete(@PathVariable int id) {
	        try {
	            return ResponseEntity.ok().body(servicio.delete(id));
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("{\"message\": \"Error. Please check the ID, and try again later.\"}");
	        }
	    }
}
