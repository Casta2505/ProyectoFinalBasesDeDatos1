package co.edu.unbosque.EntreCOL.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.EntreCOL.model.Empleados;
import co.edu.unbosque.EntreCOL.model.NominaEmpleado;
import co.edu.unbosque.EntreCOL.repository.EmpleadosRepository;
import co.edu.unbosque.EntreCOL.repository.NominaEmpleadosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/NominaEmpleado")
public class NominaEmpleadoController {
	
	@Autowired
	private NominaEmpleadosRepository daoNomina;
	
	@Autowired
	private EmpleadosRepository daoEmpleados;
	 
	@GetMapping("/listar")
	public ResponseEntity<List<NominaEmpleado>> getAll(){
		
		List<NominaEmpleado> lista = daoNomina.findAll();
		
		if(lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
		
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<String> agregar(@RequestParam Integer codempleado, @RequestParam boolean novedadIncapacidad, @RequestParam boolean novedadVacaciones,
											@RequestParam Integer diasTrabajados, @RequestParam Integer diasIncapacidad, @RequestParam Integer diasVacaciones,
											@RequestParam Date inicioVacaciones, @RequestParam Date terminacionVacaciones, @RequestParam Date inicioIncapacidad,
											@RequestParam Date terminacionIncapacidad, @RequestParam Double bonificacion, @RequestParam Double transporte){
		
		Optional<Empleados> emp = daoEmpleados.findByCodigo(codempleado);
		
		if(emp.isPresent()) {
			
			NominaEmpleado ag = new NominaEmpleado();
			
			ag.setBonificacion(bonificacion);
			ag.setDiasIncapacidad(diasIncapacidad);
			ag.setDiasTrabajados(diasTrabajados);
			ag.setDiasVacaciones(diasVacaciones);
			ag.setIdEmpleado(emp.get());
			ag.setInicioIncapacidad(inicioIncapacidad);
			ag.setInicioVacaciones(inicioVacaciones);
			ag.setNovedadIncapacidad(novedadIncapacidad);
			ag.setNovedadVacaciones(novedadVacaciones);
			ag.setTerminacionIncapacidad(terminacionIncapacidad);
			ag.setTerminacionVacaciones(terminacionVacaciones);
			ag.setTransporte(transporte);
			
			daoNomina.save(ag);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Creado (201)");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");
		
	}
	
	@DeleteMapping
	public ResponseEntity<String> eliminar(@RequestParam Integer id){
		
		Optional<NominaEmpleado> aux = daoNomina.findById(id);
		
		if(!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
		
		daoNomina.delete(aux.get());
		return ResponseEntity.status(HttpStatus.FOUND).body("Eliminado");
		
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<NominaEmpleado> buscar(@RequestParam Integer id){
		
		Optional<NominaEmpleado> aux = daoNomina.findById(id);
		
		if(!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(aux.get());
		
	}
	
	@PostMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer id, @RequestParam Integer codempleado, @RequestParam boolean novedadIncapacidad, @RequestParam boolean novedadVacaciones,
											@RequestParam Integer diasTrabajados, @RequestParam Integer diasIncapacidad, @RequestParam Integer diasVacaciones,
											@RequestParam Date inicioVacaciones, @RequestParam Date terminacionVacaciones, @RequestParam Date inicioIncapacidad,
											@RequestParam Date terminacionIncapacidad, @RequestParam Double bonificacion, @RequestParam Double transporte){
		
		Optional<NominaEmpleado> aux = daoNomina.findById(id);
		
		if(aux.isPresent()) {
		
			Optional<Empleados> emp = daoEmpleados.findByCodigo(codempleado);
		
			if(emp.isPresent()) {
			
				NominaEmpleado ag = new NominaEmpleado();
				
				ag.setBonificacion(bonificacion);
				ag.setDiasIncapacidad(diasIncapacidad);
				ag.setDiasTrabajados(diasTrabajados);
				ag.setDiasVacaciones(diasVacaciones);
				ag.setIdEmpleado(emp.get());
				ag.setInicioIncapacidad(inicioIncapacidad);
				ag.setInicioVacaciones(inicioVacaciones);
				ag.setNovedadIncapacidad(novedadIncapacidad);
				ag.setNovedadVacaciones(novedadVacaciones);
				ag.setTerminacionIncapacidad(terminacionIncapacidad);
				ag.setTerminacionVacaciones(terminacionVacaciones);
				ag.setTransporte(transporte);
			
				daoNomina.save(ag);
			
				return ResponseEntity.status(HttpStatus.CREATED).body("Actualizado (201)");
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el empleado");
		
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");
		
	}

}
