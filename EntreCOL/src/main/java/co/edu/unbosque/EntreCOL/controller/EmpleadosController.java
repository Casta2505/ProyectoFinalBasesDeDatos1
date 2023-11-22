package co.edu.unbosque.EntreCOL.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import co.edu.unbosque.EntreCOL.model.Empleados;
import co.edu.unbosque.EntreCOL.model.NominaEmpleado;
import co.edu.unbosque.EntreCOL.repository.EmpleadosRepository;
import co.edu.unbosque.EntreCOL.repository.NominaEmpleadosRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Empleados")

public class EmpleadosController {
	@Autowired
	private EmpleadosRepository daoEmpleados;
	@Autowired
	private NominaEmpleadosRepository daoNominaEmpleado;

	@PostMapping("/leer")
	public ResponseEntity<String> leerArchivo(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Por favor seleccione un archivo para subir");
		}
		try {
			List<Empleados> empleados = new ArrayList<>();
			try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
				Sheet sheet = workbook.getSheetAt(0);
				for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
					Row row = sheet.getRow(i);
					Empleados empleado = new Empleados();
					empleado.setCodigo((int) row.getCell(0).getNumericCellValue());
					empleado.setNombre(row.getCell(1).getStringCellValue());
					empleado.setDependencia(row.getCell(2).getStringCellValue());
					empleado.setCargo(row.getCell(3).getStringCellValue());
					String tmp = row.getCell(4).getStringCellValue();
					LocalDate fechaIngreso = LocalDate.of(Integer.parseInt(tmp.substring(0, 4)),
							Integer.parseInt(tmp.substring(4, 6)), Integer.parseInt(tmp.substring(6)));
					empleado.setFechaIngreso(fechaIngreso);
					empleado.setEps(row.getCell(5).getStringCellValue());
					empleado.setArl(row.getCell(6).getStringCellValue());
					empleado.setPension(row.getCell(7).getStringCellValue());
					empleado.setSueldo(row.getCell(8).getNumericCellValue());
					empleados.add(empleado);
				}
				daoEmpleados.saveAll(empleados);
				Sheet sheet2 = workbook.getSheetAt(1);
				List<NominaEmpleado> nominas = new ArrayList<>();
				for (int i = 1; i < sheet2.getPhysicalNumberOfRows(); i++) {
					Row row = sheet2.getRow(i);
					int codigo = (int) row.getCell(0).getNumericCellValue();
					Empleados empleado = daoEmpleados.findById(codigo).orElse(null);
					if (empleado != null) {
						NominaEmpleado nomina = new NominaEmpleado();
						nomina.setIdEmpleado(empleado);
						nomina.setNovedadIncapacidad(transformaEquis(row.getCell(1).getStringCellValue()));
						nomina.setNovedadVacaciones(transformaEquis(row.getCell(2).getStringCellValue()));
						nomina.setDiasTrabajados((int) row.getCell(3).getNumericCellValue());
						nomina.setDiasIncapacidad((int) row.getCell(4).getNumericCellValue());
						nomina.setDiasVacaciones((int) row.getCell(5).getNumericCellValue());
						String tmp = row.getCell(6).getStringCellValue();
						LocalDate fecha = LocalDate.of(Integer.parseInt(tmp.substring(0, 4)),
								Integer.parseInt(tmp.substring(4, 6)), Integer.parseInt(tmp.substring(6)));
						nomina.setInicioVacaciones(fecha);
						tmp = row.getCell(7).getStringCellValue();
						fecha = LocalDate.of(Integer.parseInt(tmp.substring(0, 4)),
								Integer.parseInt(tmp.substring(4, 6)), Integer.parseInt(tmp.substring(6)));
						nomina.setTerminacionVacaciones(fecha);
						tmp = row.getCell(8).getStringCellValue();
						fecha = LocalDate.of(Integer.parseInt(tmp.substring(0, 4)),
								Integer.parseInt(tmp.substring(4, 6)), Integer.parseInt(tmp.substring(6)));
						nomina.setInicioIncapacidad(fecha);
						tmp = row.getCell(9).getStringCellValue();
						fecha = LocalDate.of(Integer.parseInt(tmp.substring(0, 4)),
								Integer.parseInt(tmp.substring(4, 6)), Integer.parseInt(tmp.substring(6)));
						nomina.setTerminacionIncapacidad(fecha);
						nomina.setBonificacion(row.getCell(10).getNumericCellValue());
						nomina.setTransporte(row.getCell(11).getNumericCellValue());
						nominas.add(nomina);
					}
				}
				daoNominaEmpleado.saveAll(nominas);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Archivo procesado con exito");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar el archivo");
		}
	}

	public boolean transformaEquis(String a) {
		if (a.contains("X")) {
			return true;
		}
		return false;
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Empleados>> getAll() {

		List<Empleados> lista = daoEmpleados.findAll();

		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(lista);

	}

	@PostMapping("/agregar")
	public ResponseEntity<String> agregar(@RequestParam Integer codigo, @RequestParam String nombre,
			@RequestParam String dependencia, @RequestParam String cargo, @RequestParam LocalDate fechaIngreso,
			@RequestParam String eps, @RequestParam String arl, @RequestParam String pension,
			@RequestParam Double sueldo) {

		Optional<Empleados> aux = daoEmpleados.findByCodigo(codigo);

		if (!aux.isPresent()) {

			Empleados ag = new Empleados();

			ag.setCodigo(codigo);
			ag.setArl(arl);
			ag.setCargo(cargo);
			ag.setDependencia(dependencia);
			ag.setEps(eps);
			ag.setFechaIngreso(fechaIngreso);
			ag.setNombre(nombre);
			ag.setPension(pension);
			ag.setSueldo(sueldo);

			daoEmpleados.save(ag);

			return ResponseEntity.status(HttpStatus.CREATED).body("Creado (201)");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");

	}

	@PutMapping("/actualizar")
	public ResponseEntity<String> actualizar(@RequestParam Integer codigo1, @RequestParam Integer codigo2,
			@RequestParam String nombre, @RequestParam String dependencia, @RequestParam String cargo,
			@RequestParam LocalDate fechaIngreso, @RequestParam String eps, @RequestParam String arl,
			@RequestParam String pension, @RequestParam Double sueldo) {

		Optional<Empleados> aux = daoEmpleados.findByCodigo(codigo1);

		if (aux.isPresent()) {

			daoEmpleados.delete(aux.get());

			Empleados ag = new Empleados();

			ag.setCodigo(codigo2);
			ag.setArl(arl);
			ag.setCargo(cargo);
			ag.setDependencia(dependencia);
			ag.setEps(eps);
			ag.setFechaIngreso(fechaIngreso);
			ag.setNombre(nombre);
			ag.setPension(pension);
			ag.setSueldo(sueldo);

			daoEmpleados.save(ag);

			return ResponseEntity.status(HttpStatus.CREATED).body("Actualizado (201)");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No creado");

	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<String> eliminar(@RequestParam Integer codigo) {

		Optional<Empleados> aux = daoEmpleados.findByCodigo(codigo);

		if (!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
		}
		daoEmpleados.delete(aux.get());
		return ResponseEntity.status(HttpStatus.FOUND).body("Eliminado");

	}

	@GetMapping("/buscar")
	public ResponseEntity<Empleados> buscar(@RequestParam Integer codigo) {

		Optional<Empleados> aux = daoEmpleados.findByCodigo(codigo);

		if (!aux.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(aux.get());

	}

}
