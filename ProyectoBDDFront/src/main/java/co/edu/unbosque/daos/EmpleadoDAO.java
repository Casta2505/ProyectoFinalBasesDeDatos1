package co.edu.unbosque.daos;

import java.io.File;
import java.nio.file.spi.FileSystemProvider;
import java.time.LocalDate;
import java.util.Arrays;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.persistence.Empleado;

public class EmpleadoDAO {

	private static final String URL = "http://localhost:8081/Empleados/";
	private RestTemplate restTemplate;

	public EmpleadoDAO() {
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
	
	public List<Empleado> listar(){
    try {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL+"listar");
        String url = builder.toUriString();
        ResponseEntity<List<Empleado>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Empleado>>() {}
        );
        if (response.getStatusCode().equals(HttpStatus.FOUND)){
            return response.getBody();
        }
    } catch (Exception e) {
        e.printStackTrace(); // Imprime la excepción para debug
    }
    return null;
}

	public String add(Integer codigo, String nombre, String dependencia, String cargo, LocalDate fechaIngreso,
			String eps, String arl, String pension, Double sueldo) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "agregar");
			builder.queryParam("codigo", codigo);
			builder.queryParam("nombre", nombre);
			builder.queryParam("dependencia", dependencia);
			builder.queryParam("cargo", cargo);
			builder.queryParam("fechaIngreso", fechaIngreso);
			builder.queryParam("eps", eps);
			builder.queryParam("arl", arl);
			builder.queryParam("pension", pension);
			builder.queryParam("sueldo", sueldo);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "CONFLICT (CODE 409)";
		}
	}
	
	public String actualizar(Integer codigo1, Integer codigo2,
			String nombre, String dependencia, String cargo,
			LocalDate fechaIngreso, String eps, String arl,
			String pension, Double sueldo) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "actualizar");
			builder.queryParam("codigo1", codigo1);
			builder.queryParam("codigo2", codigo2);
			builder.queryParam("nombre", nombre);
			builder.queryParam("dependencia", dependencia);
			builder.queryParam("cargo", cargo);
			builder.queryParam("fechaIngreso", fechaIngreso);
			builder.queryParam("eps", eps);
			builder.queryParam("arl", arl);
			builder.queryParam("pension", pension);
			builder.queryParam("sueldo", sueldo);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}
	
	public String delete(Integer codigo) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "eliminar");
			builder.queryParam("codigo", codigo);
			String url = builder.toUriString();
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
			return response.getBody();
		} catch (Exception e) {
			return "NOT FOUND (CODE 404)";
		}
	}
	
	public Empleado buscar(Integer codigo) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL + "buscar");
			builder.queryParam("codigo", codigo);
			String url = builder.toUriString();
			ResponseEntity<Empleado> response = restTemplate.getForEntity(url, Empleado.class);
			if(response.getStatusCode().equals(HttpStatus.FOUND)){
				return response.getBody();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
