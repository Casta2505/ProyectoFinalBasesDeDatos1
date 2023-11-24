package co.edu.unbosque.persistence;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class Empleado implements Serializable {

	private Integer codigo;

	private String nombre;

	private String dependencia;

	private String cargo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaIngreso;

	private String eps;

	private String arl;

	private String pension;

	private List<Nomina> nominas;

	private Double sueldo;

	@JsonCreator
	public Empleado(@JsonProperty("codigo") Integer codigo, @JsonProperty("nombre") String nombre,
			@JsonProperty("dependencia") String dependencia, @JsonProperty("cargo") String cargo,
			@JsonProperty("fechaIngreso") LocalDate fechaIngreso, @JsonProperty("eps") String eps,
			@JsonProperty("arl") String arl, @JsonProperty("pension") String pension,
			@JsonProperty("sueldo") Double sueldo, @JsonProperty("nominas") List<Nomina> nominas) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.dependencia = dependencia;
		this.cargo = cargo;
		this.fechaIngreso = fechaIngreso;
		this.eps = eps;
		this.arl = arl;
		this.pension = pension;
		this.nominas = nominas;
		this.sueldo = sueldo;
	}

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

	public List<Nomina> getNominas() {
		return nominas;
	}

	public void setNominas(List<Nomina> nominas) {
		this.nominas = nominas;
	}

}
