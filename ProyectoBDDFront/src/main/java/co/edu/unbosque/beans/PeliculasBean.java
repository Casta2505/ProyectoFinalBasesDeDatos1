package co.edu.unbosque.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;

import co.edu.unbosque.daos.PeliculaDAO;
import co.edu.unbosque.persistence.Pelicula;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

@Named
@ViewScoped
public class PeliculasBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Pelicula> peliculas;

	private List<Pelicula> seleccionados;
	
	private PeliculaDAO pelao;
	
	private Pelicula seleccionado;
	
	private LineChartModel lineModel;

	@PostConstruct
	public void init() {
		pelao = new PeliculaDAO();
		seleccionados = new ArrayList<>();
		peliculas = pelao.listar();
		if(peliculas != null){
			createLineModel();
		} else {
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
		for(Pelicula a : peliculas){
			String genero = a.getGenero();
			conteo.put(genero, conteo.getOrDefault(genero, 0) + 1);
		}
		List<String> labels = new ArrayList<>();
		TreeMap<String, Integer> mapaOrdenadoPorClave = new TreeMap<>(conteo);
		for (Map.Entry<String, Integer> entry : mapaOrdenadoPorClave.entrySet()) {
			values.add(entry.getValue());
			labels.add(entry.getKey()+ "");
        }
		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Peliculas por genero.");
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

	public List<Pelicula> getpeliculas() {
		return peliculas;
	}

	public void setpeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public List<Pelicula> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<Pelicula> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public PeliculaDAO getpelao() {
		return pelao;
	}

	public void setpelao(PeliculaDAO pelao) {
		this.pelao = pelao;
	}

	public Pelicula getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Pelicula seleccionado) {
		this.seleccionado = seleccionado;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public PeliculaDAO getPelao() {
		return pelao;
	}

	public void setPelao(PeliculaDAO pelao) {
		this.pelao = pelao;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
			pelao.leer(archivoTemporal);
			peliculas = pelao.listar();
			createLineModel();
			PrimeFaces.current().ajax().update("generopelis");
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (archivoTemporal != null && archivoTemporal.exists()) {
				archivoTemporal.delete();
			}
		}
	}
	
	public void nuevo() {
		this.seleccionado = new Pelicula(0,"","");
	}
	
	public void agregar() {
		seleccionado.setIdPeliculas(peliculas.get(peliculas.size() - 1).getIdPeliculas() + 1);
		pelao.add(seleccionado.getNombre(), seleccionado.getGenero());
		createLineModel();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pelicula Añadida"));
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("generopelis");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public boolean haySelec() {
		return this.seleccionados != null && !this.seleccionados.isEmpty();
	}
	
	public void deleteSelected() {
		
		for(Pelicula e: seleccionados) {
			this.pelao.delete(e.getIdPeliculas());
		}
		this.seleccionados = null;
		createLineModel();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("peliculas Removidas"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().ajax().update("generopelis");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}
	
	public String getDeleteButtonMessage() {
		if (haySelec()) {
			int size = this.seleccionados.size();
			return size > 1 ? size + " peliculas seleccionados" : "1 pelicula seleccionado";
		}

		return "Eliminar";
	}

}
