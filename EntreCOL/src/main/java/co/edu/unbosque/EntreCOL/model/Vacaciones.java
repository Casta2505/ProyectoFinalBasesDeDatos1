package co.edu.unbosque.EntreCOL.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Vacaciones")
public class Vacaciones {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVacaciones;
	@ManyToOne
	@JoinColumn(name = "idEmpleados")
	private Empleados idEmpleados;
	
	private LocalDate inicioVacaciones;
	
	private LocalDate finVacaciones;

	public Integer getIdVacaciones() {
		return idVacaciones;
	}

	public void setIdVacaciones(Integer idVacaciones) {
		this.idVacaciones = idVacaciones;
	}

	public Empleados getIdEmpleados() {
		return idEmpleados;
	}

	public void setIdEmpleados(Empleados idEmpleados) {
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
