package co.edu.unbosque.daos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import co.edu.unbosque.persistence.Vacaciones;

public class VacacionesDAO {
	private static final String URL = "http://localhost:8081/Vacaciones/";
	private RestTemplate restTemplate;

	public VacacionesDAO() {
		restTemplate = new RestTemplate();
	}

	public List<Vacaciones> listar() {
		try {
			String url = URL + "listar";
			ResponseEntity<Vacaciones[]> response = restTemplate.getForEntity(url, Vacaciones[].class);
			if (response.getStatusCode().equals(HttpStatus.FOUND)) {
				return Arrays.asList(response.getBody());
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public String add(Integer idempleado, LocalDate inicioVacaciones, LocalDate finVacaciones) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "agregar");
			builder.queryParam("idempleado", idempleado);
			builder.queryParam("inicioVacaciones", inicioVacaciones);
			builder.queryParam("inicioVacaciones", finVacaciones);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "CONFLICT (CODE 409)";
		}
	}

	public String actualizar(Integer id, Integer idempleado, LocalDate inicioVacaciones, LocalDate finVacaciones) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "actualizar");
			builder.queryParam("id", id);
			builder.queryParam("idempleado", idempleado);
			builder.queryParam("inicioVacaciones", inicioVacaciones);
			builder.queryParam("inicioVacaciones", finVacaciones);
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

	public Vacaciones buscar(Integer id) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "buscar");
			builder.queryParam("id", id);
			String url = builder.toUriString();
			ResponseEntity<Vacaciones> response = restTemplate.getForEntity(url, Vacaciones.class);
			return response.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
