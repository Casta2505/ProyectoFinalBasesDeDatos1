package co.edu.unbosque.EntreCOL.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.EntreCOL.model.Empleados;
import co.edu.unbosque.EntreCOL.model.Vacaciones;
import co.edu.unbosque.EntreCOL.repository.EmpleadosRepository;
import co.edu.unbosque.EntreCOL.repository.VacacionesRepository;

public class VacacionesController {
	
	@Autowired
	private VacacionesRepository daoVacaciones;
	
	@Autowired
	private EmpleadosRepository daoEmpleados;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Vacaciones>> getAll(){
		
		List<Vacaciones> lista = daoVacaciones.findAll();
		
		if(lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<String> agregar(@RequestParam Integer idempleado,@RequestParam Date inicioVacaciones, @RequestParam Date finVacaciones){
		
		Optional<Empleados> aux = daoEmpleados.findByCodigo(idempleado);
		
		if(!aux.isPresent()) {
			
			Vacaciones ag = new Vacaciones();
			
			ag.setIdEmpleados(aux.get());
			ag.setFinVacaciones(finVacaciones);
			ag.setInicioVacaciones(inicioVacaciones);
			
			daoVacaciones.save(ag);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Creado (201)");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");
		
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer id, @RequestParam Integer idempleado,@RequestParam Date inicioVacaciones, @RequestParam Date finVacaciones){
		
		Optional<Vacaciones> up = daoVacaciones.findById(id);
		
		if(up.isPresent()) {
		
		Optional<Empleados> aux = daoEmpleados.findByCodigo(idempleado);
		
			if(!aux.isPresent()) {
				
				daoVacaciones.delete(up.get());
				
				Vacaciones ag = new Vacaciones();
				
				ag.setIdEmpleados(aux.get());
				ag.setFinVacaciones(finVacaciones);
				ag.setInicioVacaciones(inicioVacaciones);
				
				daoVacaciones.save(ag);
				
				return ResponseEntity.status(HttpStatus.CREATED).body("Acutalizado (201)");
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No actualizado");
		
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No actualizado");
		
	}
	
	@DeleteMapping
	public ResponseEntity<String> eliminar(@RequestParam Integer id){
		
		Optional<Vacaciones> aux = daoVacaciones.findById(id);
		
		if(!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
		daoVacaciones.delete(aux.get());
		return ResponseEntity.status(HttpStatus.FOUND).body("Eliminado");
		
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<Vacaciones> buscar(@RequestParam Integer id){
		
		Optional<Vacaciones> aux = daoVacaciones.findById(id);
		
		if(!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		daoVacaciones.delete(aux.get());
		return ResponseEntity.status(HttpStatus.FOUND).body(aux.get());
		
	}

}
