package com.utn.dto;

import java.util.ArrayList;
import java.util.List;

import com.utn.entities.AdnEntity;

public class PersonaAdnDTO {

	private int id;
	private int edad;
	private String nombre;
	private String apellido;
	
	private boolean Mutant;
	
	private List<AdnEntity> adn = new ArrayList<>();
	

	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<AdnEntity> getAdn() {
		return adn;
	}

	public void setAdn(List<AdnEntity> adn) {
		this.adn = adn;
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getMutant() {
		return Mutant;
	}

	public void setMutant(boolean isMutant) {
		this.Mutant = isMutant;
	}
}
