package co.edu.unbosque.daos;

import java.io.File;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import co.edu.unbosque.persistence.Libro;

public class LibroDAO {
	private static final String URL = "http://localhost:8081/Libros/";
	private RestTemplate restTemplate;

	public LibroDAO() {
		restTemplate = new RestTemplate();
	}

	public String leer(File archivo) {
		try {
			FileSystemResource resourse = new FileSystemResource(archivo);
			LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("file", resourse);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<LinkedMultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "leer");
			ResponseEntity<String> response = restTemplate.postForEntity(builder.toUriString(), request, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "CONFLICT (CODE 409)";
		}
	}

	public List<Libro> listar() {
		try {
	        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL+"listar");
	        String url = builder.toUriString();
	        ResponseEntity<List<Libro>> response = restTemplate.exchange(
	            url,
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<Libro>>() {}
	        );
	        if (response.getStatusCode().equals(HttpStatus.ACCEPTED)){
	            return response.getBody();
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Imprime la excepción para debug
	    }
	    return null;
	}

	public String add(Integer idlibro, String autores,
			String rating, String isbn, String isbn13,
			String idioma, String paginas, Integer totalRatings,
			Integer totalResenas, String fechaPublicacion, String publicador,
			String titulo) {
		try {
			System.out.println("entra");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "agregar");
			builder.queryParam("idlibro", idlibro);
			builder.queryParam("autores", autores);
			builder.queryParam("rating", rating);
			builder.queryParam("isbn", isbn);
			builder.queryParam("isbn13", isbn13);
			builder.queryParam("idioma", idioma);
			builder.queryParam("paginas", paginas);
			builder.queryParam("totalRatings", totalRatings);
			builder.queryParam("totalResenas", totalResenas);
			builder.queryParam("fechaPublicacion", fechaPublicacion);
			builder.queryParam("publicador", publicador);
			builder.queryParam("titulo", titulo);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "CONFLICT (CODE 409)";
		}
	}

	public String actualizar(Integer idlibro1, Integer idlibro2, String autores,
			String rating, String isbn, String isbn13,
			String idioma, String paginas, Integer totalRatings,
			Integer totalResenas, String fechaPublicacion, String publicador,
			String titulo) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "actualizar");
			builder.queryParam("idlibro1", idlibro1);
			builder.queryParam("idlibro2", idlibro2);
			builder.queryParam("autores", autores);
			builder.queryParam("rating", rating);
			builder.queryParam("isbn", isbn);
			builder.queryParam("isbn13", isbn13);
			builder.queryParam("idioma", idioma);
			builder.queryParam("paginas", paginas);
			builder.queryParam("totalRatings", totalRatings);
			builder.queryParam("totalResenas", totalResenas);
			builder.queryParam("fechaPublicacion", fechaPublicacion);
			builder.queryParam("publicador", publicador);
			builder.queryParam("titulo", titulo);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}

	public String delete(Integer idlibro) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "eliminar");
			builder.queryParam("idlibro", idlibro);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}

	public Libro buscar(Integer idlibro) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "buscar");
			builder.queryParam("idlibro", idlibro);
			String url = builder.toUriString();
			ResponseEntity<Libro> response = restTemplate.getForEntity(url, Libro.class);
			return response.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
