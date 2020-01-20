package com.utn.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adn")
public class AdnEntity extends BaseEntity {
		
	@Column(name = "adn_cadena")
	private String cadena;
	
	public AdnEntity(String cadena) {
		this.cadena = cadena;
	}

	public AdnEntity() {
	}
	
	
	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}
	
}
