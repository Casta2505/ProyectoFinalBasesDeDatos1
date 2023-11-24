package co.edu.unbosque.daos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import co.edu.unbosque.persistence.Nomina;

public class NominaDAO {
	private static final String URL = "http://localhost:8081/NominaEmpleado/";
	private RestTemplate restTemplate;

	public NominaDAO() {
		restTemplate = new RestTemplate();
	}

	public List<Nomina> listar() {
		try {
			String url = URL + "listar";
			ResponseEntity<Nomina[]> response = restTemplate.getForEntity(url, Nomina[].class);
			if (response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
				return Arrays.asList(response.getBody());
			}
		} catch (Exception e) {
			return null;
		}
		return null;
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

public String actualizar(Integer id, Integer codempleado, boolean novedadIncapacidad, boolean novedadVacaciones,
			Integer diasTrabajados, Integer diasIncapacidad, Integer diasVacaciones, LocalDate inicioVacaciones,
			LocalDate terminacionVacaciones, LocalDate inicioIncapacidad, LocalDate terminacionIncapacidad,
			Double bonificacion, Double transporte) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "actualizar");
			builder.queryParam("id", id);
			builder.queryParam("novedadIncapacidad", novedadIncapacidad);
			builder.queryParam("novedadVacaciones", novedadVacaciones);
			builder.queryParam("diasTrabajados", diasTrabajados);
			builder.queryParam("diasIncapacidad", diasIncapacidad);
			builder.queryParam("diasVacaciones", diasVacaciones);
			builder.queryParam("inicioVacaciones", inicioVacaciones);
			builder.queryParam("terminacionVacaciones", terminacionVacaciones);
			builder.queryParam("inicioIncapacidad", inicioIncapacidad);
			builder.queryParam("terminacionIncapacidad", terminacionIncapacidad);
			builder.queryParam("bonificacion", bonificacion);
			builder.queryParam("transporte", transporte);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}

	public Nomina buscar(Integer id) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "buscar");
			builder.queryParam("codigo", id);
			String url = builder.toUriString();
			ResponseEntity<Nomina> response = restTemplate.getForEntity(url, Nomina.class);
			return response.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
