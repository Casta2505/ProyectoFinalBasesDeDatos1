package co.edu.unbosque.persistence;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Libro implements Serializable {

	private Integer idlibro;

	private String titulo;

	private String autores;

	private String rating;

	private String isbn;

	private String isbn13;

	private String idioma;

	private String paginas;

	private Integer totalRatings;

	private Integer totalResenas;

	private String fechaPublicacion;

	private String publicador;

	public Libro(@JsonProperty("idLibro") Integer idlibro, @JsonProperty("titulo") String titulo,
			@JsonProperty("autores") String autores, @JsonProperty("rating") String rating,
			@JsonProperty("isbn") String isbn, @JsonProperty("isbn13") String isbn13,
			@JsonProperty("idioma") String idioma, @JsonProperty("paginas") String paginas, @JsonProperty("totalRatings") Integer totalRatings,
			@JsonProperty("totalResenas") Integer totalResenas, @JsonProperty("fechaPublicacion") String fechaPublicacion, @JsonProperty("publicador") String publicador) {
		super();
		this.idlibro = idlibro;
		this.titulo = titulo;
		this.autores = autores;
		this.rating = rating;
		this.isbn = isbn;
		this.isbn13 = isbn13;
		this.idioma = idioma;
		this.paginas = paginas;
		this.totalRatings = totalRatings;
		this.totalResenas = totalResenas;
		this.fechaPublicacion = fechaPublicacion;
		this.publicador = publicador;
	}

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
