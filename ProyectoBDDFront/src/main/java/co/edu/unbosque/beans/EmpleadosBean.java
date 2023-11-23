package co.edu.unbosque.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.primefaces.event.FileUploadEvent;

import co.edu.unbosque.daos.EmpleadoDAO;
import co.edu.unbosque.persistence.Empleado;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class EmpleadosBean {

	private List<Empleado> empleados;

	private List<Empleado> seleccionados;

	private EmpleadoDAO empdao;

	@PostConstruct
	public void init() {
		empdao = new EmpleadoDAO();
		empleados = empdao.listar();
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<Empleado> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<Empleado> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public EmpleadoDAO getEmpdao() {
		return empdao;
	}

	public void setEmpdao(EmpleadoDAO empdao) {
		this.empdao = empdao;
	}

	public void enviar(FileUploadEvent a) {

		// Crear un objeto File temporal para el procesamiento
		File archivoTemporal = null;
		try {
			InputStream inputStream = a.getFile().getInputStream();
			// Crear un archivo temporal
			archivoTemporal = File.createTempFile("tempfile", ".tmp");

			// Utilizar flujos de entrada y salida para copiar el InputStream al archivo
			// temporal
			try (FileOutputStream outputStream = new FileOutputStream(archivoTemporal)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}
			}

			// Llamar al método que necesita un objeto File
			empdao.leer(archivoTemporal);
		} catch (IOException e) {
			// Manejar la excepción según sea necesario
			e.printStackTrace();
		} finally {
			// Borrar el archivo temporal después de utilizarlo, si es necesario
			if (archivoTemporal != null && archivoTemporal.exists()) {
				archivoTemporal.delete();
			}
		}
	}

}
