package co.edu.unbosque.persistence;

import java.io.Serializable;
import java.util.Date;

public class Nomina implements Serializable {
	private int id;

	private int idEmpleado;

	private boolean novedadIncapacidad;

	private boolean novedadVacaciones;

	private Integer diasTrabajados;

	private Integer diasIncapacidad;

	private Integer diasVacaciones;

	private Date inicioVacaciones;

	private Date terminacionVacaciones;

	private Date inicioIncapacidad;

	private Date terminacionIncapacidad;

	private Double bonificacion;

	private Double transporte;
	
	@Override
	public Nomina clone() {
		Nomina clonedNomina = new Nomina();
		clonedNomina.setId(getId());
		clonedNomina.setIdEmpleado(getIdEmpleado());
		clonedNomina.setNovedadIncapacidad(isNovedadIncapacidad());
		clonedNomina.setNovedadVacaciones(isNovedadVacaciones());
		clonedNomina.setDiasTrabajados(getDiasTrabajados());
		clonedNomina.setDiasIncapacidad(getDiasIncapacidad());
		clonedNomina.setDiasVacaciones(getDiasVacaciones());
		clonedNomina.setInicioVacaciones(getInicioVacaciones() != null ? (Date) getInicioVacaciones().clone() : null);
		clonedNomina.setTerminacionVacaciones(
				getTerminacionVacaciones() != null ? (Date) getTerminacionVacaciones().clone() : null);
		clonedNomina
				.setInicioIncapacidad(getInicioIncapacidad() != null ? (Date) getInicioIncapacidad().clone() : null);
		clonedNomina.setTerminacionIncapacidad(
				getTerminacionIncapacidad() != null ? (Date) getTerminacionIncapacidad().clone() : null);
		clonedNomina.setBonificacion(getBonificacion());
		clonedNomina.setTransporte(getTransporte());
		return clonedNomina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
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

	public Date getInicioVacaciones() {
		return inicioVacaciones;
	}

	public void setInicioVacaciones(Date inicioVacaciones) {
		this.inicioVacaciones = inicioVacaciones;
	}

	public Date getTerminacionVacaciones() {
		return terminacionVacaciones;
	}

	public void setTerminacionVacaciones(Date terminacionVacaciones) {
		this.terminacionVacaciones = terminacionVacaciones;
	}

	public Date getInicioIncapacidad() {
		return inicioIncapacidad;
	}

	public void setInicioIncapacidad(Date inicioIncapacidad) {
		this.inicioIncapacidad = inicioIncapacidad;
	}

	public Date getTerminacionIncapacidad() {
		return terminacionIncapacidad;
	}

	public void setTerminacionIncapacidad(Date terminacionIncapacidad) {
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
