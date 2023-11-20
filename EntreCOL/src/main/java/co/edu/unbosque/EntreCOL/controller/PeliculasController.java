package co.edu.unbosque.EntreCOL.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.EntreCOL.model.Peliculas;
import co.edu.unbosque.EntreCOL.repository.PeliculasRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Peliculas")
public class PeliculasController {
	@Autowired
	private PeliculasRepository daoPeliculas;
	
	@PostMapping("/leer")
	public String leerArchivo(@RequestParam("file") MultipartFile file, Model model) {
	    if (file.isEmpty()) {
	        return "Por favor seleccione un archivo para subir";
	    }
	    try {
	        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
	        List<String> lines = Arrays.asList(content.split("\\r?\\n"));
	        List<Peliculas> peliculas = new ArrayList<>();
	        for (String line : lines) {
	            String[] parts = line.split("::");
	            if (parts.length == 3) {
	            	Peliculas peliculasaux = new Peliculas();
	            	peliculasaux.setIdPeliculas(Integer.parseInt(parts[0]));
	            	peliculasaux.setNombre(parts[1]);
	            	peliculasaux.setGenero(parts[2]);
	            	peliculas.add(peliculasaux);
	            }
	        }
	        daoPeliculas.saveAll(peliculas);
	        return "Inicio";
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error al procesar el archivo";
	    }
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<String> agregar(@RequestParam String nombre, @RequestParam String genero){
		
		Peliculas ag = new Peliculas();
		
		ag.setNombre(nombre);
		ag.setGenero(genero);
		
		daoPeliculas.save(ag);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Creado (201)");
		
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Peliculas>> getAll(){
		
		List<Peliculas> lista = daoPeliculas.findAll();
		
		if(lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
		
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer id, @RequestParam String nombre, @RequestParam String genero){
		
		Optional<Peliculas> aux = daoPeliculas.findById(id);
		
		if(aux.isPresent()) {
			
			daoPeliculas.delete(aux.get());
			
			Peliculas ag = new Peliculas();
			
			ag.setNombre(nombre);
			ag.setGenero(genero);
			
			daoPeliculas.save(ag);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Actualizado (201)");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");
		
	}
	
	@DeleteMapping()
	public ResponseEntity<String> eliminar(@RequestParam Integer id){
		
		Optional<Peliculas> aux = daoPeliculas.findById(id);
		
		if(!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
		daoPeliculas.delete(aux.get());
		return ResponseEntity.status(HttpStatus.FOUND).body("Eliminado");
		
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<Peliculas> buscar(@RequestParam Integer id){
		
		Optional<Peliculas> aux = daoPeliculas.findById(id);
		
		if(!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(aux.get());
		
	}

}
