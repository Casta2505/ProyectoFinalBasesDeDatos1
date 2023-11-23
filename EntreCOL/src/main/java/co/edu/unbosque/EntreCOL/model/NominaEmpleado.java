package co.edu.unbosque.EntreCOL.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "NominaEmpleado")
public class NominaEmpleado {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "idEmpleado")
	@JsonBackReference
	private Empleados idEmpleado;
	
	private boolean novedadIncapacidad;
	
	private boolean novedadVacaciones;
	
	private Integer diasTrabajados;
	
	private Integer diasIncapacidad;
	
	private Integer diasVacaciones;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate inicioVacaciones;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate terminacionVacaciones;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate inicioIncapacidad;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate terminacionIncapacidad;
	
	private Double bonificacion;
	
	private Double transporte;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getInicioVacaciones() {
		return inicioVacaciones;
	}

	public void setInicioVacaciones(LocalDate inicioVacaciones) {
		this.inicioVacaciones = inicioVacaciones;
	}

	public LocalDate getTerminacionVacaciones() {
		return terminacionVacaciones;
	}

	public void setTerminacionVacaciones(LocalDate terminacionVacaciones) {
		this.terminacionVacaciones = terminacionVacaciones;
	}

	public Empleados getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleados idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public boolean isNovedadIncapacidad() {
		return novedadIncapacidad;
	}

	public void setNovedadIncapacidad(boolean novedadIncapacidad) {
		this.novedadIncapacidad = novedadIncapacidad;
	}

	public boolean isNovedadVacaciones() {
		return novedadVacaciones;
	}

	public void setNovedadVacaciones(boolean novedadVacaciones) {
		this.novedadVacaciones = novedadVacaciones;
	}

	public Integer getDiasTrabajados() {
		return diasTrabajados;
	}

	public void setDiasTrabajados(Integer diasTrabajados) {
		this.diasTrabajados = diasTrabajados;
	}

	public Integer getDiasIncapacidad() {
		return diasIncapacidad;
	}

	public void setDiasIncapacidad(Integer diasIncapacidad) {
		this.diasIncapacidad = diasIncapacidad;
	}

	public Integer getDiasVacaciones() {
		return diasVacaciones;
	}

	public void setDiasVacaciones(Integer diasVacaciones) {
		this.diasVacaciones = diasVacaciones;
	}

	public LocalDate getInicioIncapacidad() {
		return inicioIncapacidad;
	}

	public void setInicioIncapacidad(LocalDate inicioIncapacidad) {
		this.inicioIncapacidad = inicioIncapacidad;
	}

	public LocalDate getTerminacionIncapacidad() {
		return terminacionIncapacidad;
	}

	public void setTerminacionIncapacidad(LocalDate terminacionIncapacidad) {
		this.terminacionIncapacidad = terminacionIncapacidad;
	}

	public Double getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Double bonificacion) {
		this.bonificacion = bonificacion;
	}

	public Double getTransporte() {
		return transporte;
	}

	public void setTransporte(Double transporte) {
		this.transporte = transporte;
	}
	
	
}
