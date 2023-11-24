package co.edu.unbosque.beans;

import org.primefaces.model.charts.ChartData;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.charts.line.LineChartDataSet;
import co.edu.unbosque.daos.LibroDAO;
import co.edu.unbosque.persistence.Libro;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class LibroBean implements Serializable {

	private List<Libro> libros;

	private List<Libro> seleccionados;

	private LibroDAO libdao;

	private Libro seleccionado;

	private LineChartModel lineModel;

	@PostConstruct
	public void init() {

		libdao = new LibroDAO();
		libros = libdao.listar();
		seleccionados = new ArrayList<>();
		if(seleccionados != null){
			createLineModel();
		}else{
			lineModel = new LineChartModel();
		}
		nuevo();

	}

	public void createLineModel() {
		lineModel = new LineChartModel();
		ChartData data = new ChartData();
		LineChartDataSet dataSet = new LineChartDataSet();
		List<Object> values = new ArrayList<>();
		Map<String, Integer> conteo = new HashMap<>();
		for(Libro a : libros){
			String fecha = a.getFechaPublicacion();
			if(fecha.split("/").length == 3){
				conteo.put(fecha.split("/")[2], conteo.getOrDefault(fecha.split("/")[2], 0) +1);
			}
		}
		List<String> labels = new ArrayList<>();
		TreeMap<String, Integer> mapaOrdenadoPorClave = new TreeMap<>(conteo);
		for (Map.Entry<String, Integer> entry : mapaOrdenadoPorClave.entrySet()) {
			values.add(entry.getValue());
			labels.add(entry.getKey()+ "");
        }
		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Libros por año de publicacion.");
		dataSet.setBorderColor("rgb(75, 192, 192)");
		dataSet.setTension(0.1);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		options.setTitle(title);
		Title subtitle = new Title();
		subtitle.setDisplay(true);
		lineModel.setOptions(options);
		lineModel.setData(data);
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public List<Libro> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<Libro> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public LibroDAO getLibdao() {
		return libdao;
	}

	public void setLibdao(LibroDAO libdao) {
		this.libdao = libdao;
	}

	public Libro getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Libro seleccionado) {
		this.seleccionado = seleccionado;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public void leer(FileUploadEvent a) {

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

			libdao.leer(archivoTemporal);
			libros = libdao.listar();
			createLineModel();
			PrimeFaces.current().ajax().update("librosano");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (archivoTemporal != null && archivoTemporal.exists()) {
				archivoTemporal.delete();
			}

		}
	}

	public void nuevo() {
		this.seleccionado = new Libro(0, "", "", "", "", "", "", "", 0, 0, "", "", "");
	}

	public void agregar() {
		seleccionado.setIdlibro(this.libros.get(this.libros.size() - 1).getIdlibro() + 1);
		libros.add(seleccionado);
		libdao.add(seleccionado.getIdlibro(), seleccionado.getAutores(), seleccionado.getRating(),
				seleccionado.getIsbn(), seleccionado.getIsbn13(), seleccionado.getIdioma(), seleccionado.getPaginas(),
				seleccionado.getTotalRatings(), seleccionado.getTotalResenas(), seleccionado.getFechaPublicacion(),
				seleccionado.getPublicador(), seleccionado.getTitulo());
		createLineModel();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro Añadido"));
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().ajax().update("librosano");
	}

	public boolean haySelec() {
		return this.seleccionados != null && !this.seleccionados.isEmpty();
	}

	public void deleteSelected() {

		this.libros.removeAll(this.seleccionados);

		for (Libro e : seleccionados) {
			this.libdao.delete(e.getIdlibro());
		}

		this.seleccionados = null;
		createLineModel();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleados Removidos"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
		PrimeFaces.current().ajax().update("librosano");
	}

	public String getDeleteButtonMessage() {
		if (haySelec()) {
			int size = this.seleccionados.size();
			return size > 1 ? size + " empleados seleccionados" : "1 empleado seleccionado";
		}

		return "Eliminar";
	}

}
