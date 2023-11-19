package co.edu.unbosque.EntreCOL.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unbosque.EntreCOL.model.Peliculas;
import co.edu.unbosque.EntreCOL.repository.PeliculasRepository;

@Controller
public class PeliculasController {
	@Autowired
	private PeliculasRepository daoPeliulas;
	
	@PostMapping("/pelicula")
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
	        daoPeliulas.saveAll(peliculas);
	        return "Inicio";
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "Error al procesar el archivo";
	    }
	}

}
