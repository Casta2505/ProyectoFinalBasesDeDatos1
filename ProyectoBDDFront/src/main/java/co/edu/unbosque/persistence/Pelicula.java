package co.edu.unbosque.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Pelicula implements Serializable {

	private Integer idPeliculas;

	private String nombre;

	private String genero;

	public Pelicula(@JsonProperty("idPeliculas") Integer idPeliculas, @JsonProperty("nombre") String nombre,
			@JsonProperty("genero") String genero) {
		super();
		this.idPeliculas = idPeliculas;
		this.nombre = nombre;
		this.genero = genero;
	}

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
