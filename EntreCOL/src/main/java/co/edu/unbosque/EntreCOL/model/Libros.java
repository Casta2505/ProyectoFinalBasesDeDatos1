package co.edu.unbosque.EntreCOL.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Libros")
public class Libros {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("bookID")
	private Integer idlibro;
	
	@JsonProperty("title")
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	@JsonProperty("authors")
	private String autores;
	
	@JsonProperty("average_rating")
	private String rating;
	
	@JsonProperty("isbn")
	private String isbn;
	
	@JsonProperty("isbn13")
	private String isbn13;
	
	@JsonProperty("language_code")
	private String idioma;
	
	@JsonProperty("num_pages")
	private String paginas;
	
	@JsonProperty("ratings_count")
	private Integer totalRatings;
	
	@JsonProperty("text_reviews_count")
	private Integer totalResenas;
	
	@JsonProperty("publication_date")
	private String fechaPublicacion;
	
	@JsonProperty("publisher")
	private String publicador;

	public Integer getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(Integer idlibro) {
		this.idlibro = idlibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public Integer getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(Integer totalRatings) {
		this.totalRatings = totalRatings;
	}

	public Integer getTotalResenas() {
		return totalResenas;
	}

	public void setTotalResenas(Integer totalResenas) {
		this.totalResenas = totalResenas;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getPublicador() {
		return publicador;
	}

	public void setPublicador(String publicador) {
		this.publicador = publicador;
	}
}
