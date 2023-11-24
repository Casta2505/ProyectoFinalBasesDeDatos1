package co.edu.unbosque.daos;

import java.io.File;
import java.util.Arrays;
import java.util.List;
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
import co.edu.unbosque.persistence.Pelicula;

public class PeliculaDAO {
	private static final String URL = "http://localhost:8081/Peliculas/";
	private RestTemplate restTemplate;

	public PeliculaDAO() {
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
	
	public List<Pelicula> listar(){
		try {
			String url = URL+"listar";
			ResponseEntity<Pelicula[]> response = restTemplate.getForEntity(url, Pelicula[].class);
			if (response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
				return Arrays.asList(response.getBody());
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public String add(String nombre, String genero) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "agregar");
			builder.queryParam("nombre", nombre);
			builder.queryParam("genero", genero);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "CONFLICT (CODE 409)";
		}
	}
	
	public String actualizar(Integer id, String nombre, String genero) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "actualizar");
			builder.queryParam("id", id);
			builder.queryParam("nombre", nombre);
			builder.queryParam("genero", genero);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}
	
	public String delete(Integer id) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "eliminar");
			builder.queryParam("id", id);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}
	
	public Pelicula buscar(Integer id) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "buscar");
			builder.queryParam("id", id);
			String url = builder.toUriString();
			ResponseEntity<Pelicula> response = restTemplate.getForEntity(url, Pelicula.class);
			return response.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
