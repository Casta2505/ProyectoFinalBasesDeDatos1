package co.edu.unbosque.persistence;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vacaciones {

	private Integer idVacaciones;

	private Empleado idEmpleados;

	private LocalDate inicioVacaciones;

	private LocalDate finVacaciones;

	public Vacaciones(@JsonProperty("idVacaciones") Integer idVacaciones, @JsonProperty("idEmpleados") Empleado idEmpleados, @JsonProperty("inicioVacaciones") LocalDate inicioVacaciones, @JsonProperty("finVacaciones") LocalDate finVacaciones) {
		super();
		this.idVacaciones = idVacaciones;
		this.idEmpleados = idEmpleados;
		this.inicioVacaciones = inicioVacaciones;
		this.finVacaciones = finVacaciones;
	}

	public Integer getIdVacaciones() {
		return idVacaciones;
	}

	public void setIdVacaciones(Integer idVacaciones) {
		this.idVacaciones = idVacaciones;
	}

	public Empleado getIdEmpleados() {
		return idEmpleados;
	}

	public void setIdEmpleados(Empleado idEmpleados) {
		this.idEmpleados = idEmpleados;
	}

	public LocalDate getInicioVacaciones() {
		return inicioVacaciones;
	}

	public void setInicioVacaciones(LocalDate inicioVacaciones) {
		this.inicioVacaciones = inicioVacaciones;
	}

	public LocalDate getFinVacaciones() {
		return finVacaciones;
	}

	public void setFinVacaciones(LocalDate finVacaciones) {
		this.finVacaciones = finVacaciones;
	}

}
