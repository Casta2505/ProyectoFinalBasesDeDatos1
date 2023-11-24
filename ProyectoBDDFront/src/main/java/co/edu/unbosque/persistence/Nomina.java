package co.edu.unbosque.persistence;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
public class Nomina implements Serializable {

	private Integer id;

	private Empleado idEmpleado;

	private boolean novedadIncapacidad;

	private boolean novedadVacaciones;

	private Integer diasTrabajados;

	private Integer diasIncapacidad;

	private Integer diasVacaciones;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioVacaciones;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate terminacionVacaciones;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioIncapacidad;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate terminacionIncapacidad;

	private Double bonificacion;

	private Double transporte;

	public Nomina(@JsonProperty("id") Integer id, @JsonProperty("idEmpleado") Empleado idEmpleado,
			@JsonProperty("novedadIncapacidad") boolean novedadIncapacidad,
			@JsonProperty("novedadVacaciones") boolean novedadVacaciones,
			@JsonProperty("diasTrabajados") Integer diasTrabajados,
			@JsonProperty("diasIncapacidad") Integer diasIncapacidad,
			@JsonProperty("diasVacaciones") Integer diasVacaciones,
			@JsonProperty("inicioVacaciones") LocalDate inicioVacaciones,
			@JsonProperty("terminacionVacaciones") LocalDate terminacionVacaciones,
			@JsonProperty("inicioIncapacidad") LocalDate inicioIncapacidad,
			@JsonProperty("terminacionIncapacidad") LocalDate terminacionIncapacidad,
			@JsonProperty("bonificacion") Double bonificacion, @JsonProperty("transporte") Double transporte) {
		super();
		this.id = id;
		this.idEmpleado = idEmpleado;
		this.novedadIncapacidad = novedadIncapacidad;
		this.novedadVacaciones = novedadVacaciones;
		this.diasTrabajados = diasTrabajados;
		this.diasIncapacidad = diasIncapacidad;
		this.diasVacaciones = diasVacaciones;
		this.inicioVacaciones = inicioVacaciones;
		this.terminacionVacaciones = terminacionVacaciones;
		this.inicioIncapacidad  = inicioIncapacidad;
		this.terminacionIncapacidad = terminacionIncapacidad;
		this.bonificacion = bonificacion;
		this.transporte = transporte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Empleado getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleado idEmpleado) {
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

	public void setId(Integer id) {
		this.id = id;
	}

}
