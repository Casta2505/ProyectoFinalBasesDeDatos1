package co.edu.unbosque.EntreCOL.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.EntreCOL.model.Libros;
import co.edu.unbosque.EntreCOL.repository.LibrosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Libros")

public class LibrosController {
	@Autowired
	private LibrosRepository daoLibros;

	@PostMapping("/leer")
	public ResponseEntity<String> leerArchivoJson(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Por favor seleccione un archivo para subir");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Libros> libros = Arrays.asList(objectMapper.readValue(file.getBytes(), Libros[].class));
			daoLibros.saveAll(libros);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Archivo procesado con exito");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo");
		}
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Libros>> getAll() {

		List<Libros> lista = daoLibros.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);

	}

	@PostMapping("/agregar")
	public ResponseEntity<String> agregar(@RequestParam Integer idlibro, @RequestParam String autores,
			@RequestParam String rating, @RequestParam String isbn, @RequestParam String isbn13,
			@RequestParam String idioma, @RequestParam String paginas, @RequestParam Integer totalRatings,
			@RequestParam Integer totalResenas, @RequestParam String fechaPublicacion, @RequestParam String publicador,
			@RequestParam String titulo) {

		Optional<Libros> aux = daoLibros.findById(idlibro);

		if (aux.isPresent()) {

			daoLibros.delete(aux.get());

			Libros ag = new Libros();

			ag.setAutores(autores);
			ag.setFechaPublicacion(fechaPublicacion);
			ag.setIdioma(idioma);
			ag.setIdlibro(idlibro);
			ag.setIsbn(isbn13);
			ag.setIsbn(isbn);
			ag.setPaginas(paginas);
			ag.setPublicador(publicador);
			ag.setRating(rating);
			ag.setTitulo(titulo);
			ag.setTotalRatings(totalRatings);
			ag.setTotalResenas(totalResenas);

			daoLibros.save(ag);

			return ResponseEntity.status(HttpStatus.CREATED).body("Creado (201)");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");

	}

	@PutMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer idlibro1, @RequestParam Integer idlibro2,
			@RequestParam String autores, @RequestParam String rating, @RequestParam String isbn,
			@RequestParam String isbn13, @RequestParam String idioma, @RequestParam String paginas,
			@RequestParam Integer totalRatings, @RequestParam Integer totalResenas,
			@RequestParam String fechaPublicacion, @RequestParam String publicador, @RequestParam String titulo) {

		Optional<Libros> aux = daoLibros.findById(idlibro1);

		if (!aux.isPresent()) {

			Libros ag = new Libros();

			ag.setAutores(autores);
			ag.setFechaPublicacion(fechaPublicacion);
			ag.setIdioma(idioma);
			ag.setIdlibro(idlibro2);
			ag.setIsbn(isbn13);
			ag.setIsbn(isbn);
			ag.setPaginas(paginas);
			ag.setPublicador(publicador);
			ag.setRating(rating);
			ag.setTitulo(titulo);
			ag.setTotalRatings(totalRatings);
			ag.setTotalResenas(totalResenas);

			daoLibros.save(ag);

			return ResponseEntity.status(HttpStatus.CREATED).body("Creado (201)");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");

	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Integer idlibro) {

		Optional<Libros> aux = daoLibros.findById(idlibro);

		if (!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
		daoLibros.delete(aux.get());
		return ResponseEntity.status(HttpStatus.FOUND).body("Eliminado");

	}

	@GetMapping("/buscar")
	public ResponseEntity<Libros> buscar(@RequestParam Integer idlibro) {

		Optional<Libros> aux = daoLibros.findById(idlibro);

		if (!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(aux.get());

	}

}
