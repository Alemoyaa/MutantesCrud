package com.utn.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "persona")
public class PersonaEntity extends BaseEntity{
	
	@Column(name = "persona_edad")
	private int edad;

	@Column(name = "persona_nombre")
	private String nombre;

	@Column(name = "persona_apellido")
	private String apellido;

	@Column(name = "persona_mutant")
	private boolean Mutant;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "persona_fk_adn")
	private AdnEntity adn = new AdnEntity();
	
		
	public boolean getMutant() {
		return Mutant;
	}

	public void setMutant(boolean isMutant) {
		this.Mutant = isMutant;
	}

	public AdnEntity getAdn() {
		return adn;
	}

	public void setAdn(AdnEntity adn) {
		this.adn = adn;
	}

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
}
