package co.edu.unbosque.EntreCOL.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Empleados")
public class Empleados {
	@Id
	private Integer codigo;
	
	private String nombre;
	
	private String dependencia;
	
	private String cargo;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fechaIngreso;
	
	private String eps;
	
	private String arl;
	
	private String pension;
	
	private Double sueldo;
	
	@OneToMany(mappedBy = "idEmpleado", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonManagedReference
	private List<NominaEmpleado> nominas;

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

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
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

	public List<NominaEmpleado> getNominas() {
		return nominas;
	}

	public void setNominas(List<NominaEmpleado> nominas) {
		this.nominas = nominas;
	}
	
}
