package co.edu.unbosque.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import co.edu.unbosque.daos.EmpleadoDAO;
import co.edu.unbosque.daos.NominaDAO;
import co.edu.unbosque.persistence.Empleado;
import co.edu.unbosque.persistence.Nomina;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

@Named
@ViewScoped
public class NominaBean implements Serializable{

	private List<Nomina> nominas;

	private List<Nomina> seleccionados;
	
	private EmpleadoDAO empdao;

	private NominaDAO nomdao;

	private Nomina nomisel;
	
	@PostConstruct
	public void init() {
		empdao = new EmpleadoDAO();
		seleccionados = new ArrayList<>();
		nomdao = new NominaDAO();
		nominas = nomdao.listar();
		nuevo();
	}

	public void enviar(FileUploadEvent a) {
		
		File archivoTemporal = null;
		try {
			InputStream inputStream = a.getFile().getInputStream();
			
			archivoTemporal = File.createTempFile("tempfile", ".tmp");
			
			try (FileOutputStream outputStream = new FileOutputStream(archivoTemporal)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length);
				}
			}
			
			empdao.leer(archivoTemporal);
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (archivoTemporal != null && archivoTemporal.exists()) {
				archivoTemporal.delete();
			}
		}
	}
	
	public void nuevo() {
		this.nomisel = new Nomina(0, new Empleado(0,"","","",LocalDate.now(),"","","",0.0,new ArrayList<>()),
			false,
			false,
			0,
			0,
			0,
			LocalDate.now(),
			LocalDate.now(),
			LocalDate.now(),
			LocalDate.now(),
			0.0, 0.0);
	}
	
	public void agregar() {
		nomisel.setId(nominas.get(nominas.size() - 1).getId() + 1);
		empdao.addNomina(nomisel.getIdEmpleado().getCodigo(), nomisel.isNovedadIncapacidad(), nomisel.isNovedadVacaciones(), nomisel.getDiasTrabajados(), 
				nomisel.getDiasIncapacidad(), nomisel.getDiasVacaciones(), nomisel.getInicioVacaciones(), nomisel.getTerminacionVacaciones(), nomisel.getInicioIncapacidad(),nomisel.getTerminacionIncapacidad(),nomisel.getBonificacion(),nomisel.getTransporte());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nómina Añadida"));
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public boolean haySelec() {
		return this.seleccionados != null && !this.seleccionados.isEmpty();
	}
	
	public void deleteSelected() {
		
		for(Nomina e: seleccionados) {
			this.empdao.delete(e.getId());
		}
		
		this.seleccionados = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleados Removidos"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}
	
	public String getDeleteButtonMessage() {
		if (haySelec()) {
			int size = this.seleccionados.size();
			return size > 1 ? size + " nóminas seleccionadas" : "1 nómina seleccionada";
		}

		return "Eliminar";
	}
	
	public void agregarEmp() {
		this.nomisel.setIdEmpleado(new Empleado(0,"","","",LocalDate.now(),"","","",0.0,new ArrayList<>()));
	}

	public List<Nomina> getNominas() {
		return nominas;
	}

	public void setNominas(List<Nomina> nominas) {
		this.nominas = nominas;
	}

	public List<Nomina> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<Nomina> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public EmpleadoDAO getEmpdao() {
		return empdao;
	}

	public void setEmpdao(EmpleadoDAO empdao) {
		this.empdao = empdao;
	}

	public NominaDAO getNomdao() {
		return nomdao;
	}

	public void setNomdao(NominaDAO nomdao) {
		this.nomdao = nomdao;
	}

	public Nomina getNomisel() {
		return nomisel;
	}

	public void setNomisel(Nomina nomisel) {
		this.nomisel = nomisel;
	}
		
}
