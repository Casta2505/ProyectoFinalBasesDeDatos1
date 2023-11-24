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
	
	private String field13;

	public Libro(@JsonProperty("bookID") Integer idlibro, @JsonProperty("title") String titulo,
			@JsonProperty("authors") String autores, @JsonProperty("average_rating") String rating,
			@JsonProperty("isbn") String isbn, @JsonProperty("isbn13") String isbn13,
			@JsonProperty("language_code") String idioma, @JsonProperty("num_pages") String paginas, @JsonProperty("ratings_count") Integer totalRatings,
			@JsonProperty("text_reviews_count") Integer totalResenas, @JsonProperty("publication_date") String fechaPublicacion, @JsonProperty("publisher") String publicador,
			@JsonProperty("FIELD13") String field13) {
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
		this.field13 = field13;
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

	public String getField13() {
		return field13;
	}

	public void setField13(String field13) {
		this.field13 = field13;
	}

}
