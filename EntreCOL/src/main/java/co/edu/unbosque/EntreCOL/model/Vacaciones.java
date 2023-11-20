package co.edu.unbosque.EntreCOL.model;

import java.util.Date;

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
	
	private Date inicioVacaciones;
	
	private Date finVacaciones;

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

	public Date getInicioVacaciones() {
		return inicioVacaciones;
	}

	public void setInicioVacaciones(Date inicioVacaciones) {
		this.inicioVacaciones = inicioVacaciones;
	}

	public Date getFinVacaciones() {
		return finVacaciones;
	}

	public void setFinVacaciones(Date finVacaciones) {
		this.finVacaciones = finVacaciones;
	}
	
}
