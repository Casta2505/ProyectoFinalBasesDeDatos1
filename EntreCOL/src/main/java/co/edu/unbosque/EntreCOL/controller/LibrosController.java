package co.edu.unbosque.EntreCOL.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.EntreCOL.model.Libros;
import co.edu.unbosque.EntreCOL.repository.LibrosRepository;

@Controller
public class LibrosController {
	@Autowired
	private LibrosRepository daoLibros;
	@PostMapping("/leer")
	public String leerArchivoJson(@RequestParam("file") MultipartFile file, Model model) {
		if (file.isEmpty()) {
			return "Por favor seleccione un archivo para subir";
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
            List<Libros> libros = Arrays.asList(objectMapper.readValue(file.getBytes(), Libros[].class));
            daoLibros.saveAll(libros);
            return "Archivo cargado y procesado con éxito";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al procesar el archivo";
        }
	}

	@GetMapping("/")
	public String generarPaginaPrincipal(Model model) {
		return "Inicio";
	}
}
