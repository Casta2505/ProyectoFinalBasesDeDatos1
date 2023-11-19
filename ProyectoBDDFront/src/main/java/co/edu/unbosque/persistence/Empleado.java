package co.edu.unbosque.persistence;

import java.io.Serializable;

public class Empleado implements Serializable {
	private Integer codigo;

	private String nombre;

	private String dependencia;

	private String cargo;

	private Integer fechaIngreso;

	private String eps;

	private String arl;

	private String pension;

	private Double sueldo;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Integer getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Integer fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getArl() {
		return arl;
	}

	public void setArl(String arl) {
		this.arl = arl;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public Double getSueldo() {
		return sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}
	
	
}
