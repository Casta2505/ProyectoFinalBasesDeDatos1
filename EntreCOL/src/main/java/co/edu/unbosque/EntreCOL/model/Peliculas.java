package co.edu.unbosque.EntreCOL.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Peliculas")
public class Peliculas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPeliculas;
	
	private String nombre;
	
	private String genero;

	public Integer getIdPeliculas() {
		return idPeliculas;
	}

	public void setIdPeliculas(Integer idPeliculas) {
		this.idPeliculas = idPeliculas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
