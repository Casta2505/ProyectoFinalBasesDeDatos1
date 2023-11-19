package co.edu.unbosque.persistence;

import java.io.Serializable;

public class Pelicula implements Serializable {
	
	private Integer idPeliculas;

	private String nombre;

	private String genero;

	public Integer getIdPelicula() {
		return idPeliculas;
	}

	public void setIdPelicula(Integer idPeliculas) {
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
