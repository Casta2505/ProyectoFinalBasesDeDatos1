package co.edu.unbosque.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import co.edu.unbosque.persistence.Empleado;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

@Named
@ViewScoped
public class EmpleadosBean implements Serializable{
	private BarChartModel barModel;
	private List<Empleado> empleados;
    private LineChartModel lineModel1;
	private LineChartModel lineModel;
	private List<Empleado> seleccionados;
	
	private EmpleadoDAO empdao;
	
	private Empleado seleccionado;
	
	private PieChartModel pieModel;
	@PostConstruct
	public void init() {
		empdao = new EmpleadoDAO();
		seleccionados = new ArrayList<>();
		empleados = empdao.listar();
		nuevo();
		createPieModel();
		createBarModel();
		createLineModel1();
		createLineModel();
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<Empleado> getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(List<Empleado> seleccionados) {
		this.seleccionados = seleccionados;
	}

	public EmpleadoDAO getEmpdao() {
		return empdao;
	}

	public void setEmpdao(EmpleadoDAO empdao) {
		this.empdao = empdao;
	}

	public Empleado getSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(Empleado seleccionado) {
		this.seleccionado = seleccionado;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public void setLineModel1(LineChartModel lineModel1) {
		this.lineModel1 = lineModel1;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
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
		this.seleccionado = new Empleado(0,"","","",LocalDate.now(),"","","",0.0,new ArrayList<>());
	}
	
	public void agregar() {
		seleccionado.setCodigo(empleados.get(empleados.size() - 1).getCodigo() + 1);
		empleados.add(seleccionado);
		empdao.add(seleccionado.getCodigo(), seleccionado.getNombre(), seleccionado.getDependencia(), seleccionado.getCargo(), 
				seleccionado.getFechaIngreso(), seleccionado.getEps(), seleccionado.getArl(), seleccionado.getPension(), seleccionado.getSueldo());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Añadido"));
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
	}
	
	public boolean haySelec() {
		return this.seleccionados != null && !this.seleccionados.isEmpty();
	}
	
	public void deleteSelected() {
		
		this.empleados.removeAll(this.seleccionados);
		
		for(Empleado e: seleccionados) {
			this.empdao.delete(e.getCodigo());
		}
		
		this.seleccionados = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleados Removidos"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
		PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
	}
	
	public String getDeleteButtonMessage() {
		if (haySelec()) {
			int size = this.seleccionados.size();
			return size > 1 ? size + " empleados seleccionados" : "1 empleado seleccionado";
		}

		return "Eliminar";
	}
	 public void createBarModel() {
	        barModel = new BarChartModel();
	        ChartData data = new ChartData();
			int idd = 0;
			int ae = 0;
			int ddi = 0;
			int gdv = 0;
			int ddf = 0;
			int ids = 0;
			int dba = 0;
			int ai = 0;
			int ddp = 0;
			int ldi = 0;
			int ddc = 0;
			int qa = 0;
			int ddco = 0;
			int ddv = 0;
			for(int i =0; i<empleados.size();i++){
				if(empleados.get(i).getCargo().equals("Ingeniero de Desarrollo")){
					idd++;
				}else if(empleados.get(i).getCargo().equals("Auxiliar Especializado")){
					ae++;
				}else if(empleados.get(i).getCargo().equals("Director de Impuestos")){
					ddi++;
				}else if(empleados.get(i).getCargo().equals("Gerente de ventas")){
					gdv++;
				}else if(empleados.get(i).getCargo().equals("Director de Facturación")){
					ddf++;
				}else if(empleados.get(i).getCargo().equals("Ingeniero de Soporte")){
					ids++;
				}else if(empleados.get(i).getCargo().equals("DBA")){
					dba++;
				}
				else if(empleados.get(i).getCargo().equals("Auditor interno")){
					ai++;
				}else if(empleados.get(i).getCargo().equals("Director de presupuestos")){
					ddp++;
				}else if(empleados.get(i).getCargo().equals("Líder de infraestructura")){
					ldi++;
				}else if(empleados.get(i).getCargo().equals("Director de cartera")){
					ddc++;
				}else if(empleados.get(i).getCargo().equals("Líder de QA")){
					qa++;
				}else if(empleados.get(i).getCargo().equals("Director de costos")){
					ddco++;
				}else if(empleados.get(i).getCargo().equals("Director de ventas")){
					ddv++;
				}
			}
	        BarChartDataSet barDataSet = new BarChartDataSet();
	        barDataSet.setLabel("Empleados Por Cargo");

	        List<Number> values = new ArrayList<>();
	        values.add(idd);
	        values.add(ae);
	        values.add(ddi);
	        values.add(gdv);
	        values.add(ddf);
	        values.add(ids);
	        values.add(dba);

			values.add(ai);
	        values.add(ddp);
	        values.add(ldi);
	        values.add(ddc);
	        values.add(qa);
	        values.add(ddco);
	        values.add(ddv);
	        barDataSet.setData(values);

	        List<String> bgColor = new ArrayList<>();
	        bgColor.add("rgba(255, 99, 132, 0.2)");
	        bgColor.add("rgba(255, 159, 64, 0.2)");
	        bgColor.add("rgba(255, 205, 86, 0.2)");
	        bgColor.add("rgba(75, 192, 192, 0.2)");
	        bgColor.add("rgba(54, 162, 235, 0.2)");
	        bgColor.add("rgba(153, 102, 255, 0.2)");
	        bgColor.add("rgba(223, 189, 207, 0.2)");
			bgColor.add("rgba(205, 123, 102, 0.2)");
	        bgColor.add("rgba(215, 159, 64, 0.2)");
	        bgColor.add("rgba(105, 205, 86, 0.2)");
	        bgColor.add("rgba(99, 100, 192, 0.2)");
	        bgColor.add("rgba(54, 101, 235, 0.2)");
	        bgColor.add("rgba(153, 102, 205, 0.2)");
	        bgColor.add("rgba(201, 203, 107, 0.2)");
	        barDataSet.setBackgroundColor(bgColor);

	        List<String> borderColor = new ArrayList<>();
	        borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(201, 203, 207)");
			borderColor.add("rgb(255, 99, 132)");
	        borderColor.add("rgb(255, 159, 64)");
	        borderColor.add("rgb(255, 205, 86)");
	        borderColor.add("rgb(75, 192, 192)");
	        borderColor.add("rgb(54, 162, 235)");
	        borderColor.add("rgb(153, 102, 255)");
	        borderColor.add("rgb(201, 203, 207)");
	        barDataSet.setBorderColor(borderColor);
	        barDataSet.setBorderWidth(1);

	        data.addChartDataSet(barDataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("Ingeniero de Desarrollo");
	        labels.add("Auxiliar especializado");
	        labels.add("Director de Impuestos");
	        labels.add("Gerente de ventas");
	        labels.add("Director de Facturación");
	        labels.add("Ingeniero de Soporte");
	        labels.add("DBA");
			labels.add("Auditor interno");
	        labels.add("Director de presupuestos");
	        labels.add("Líder de infraestructura");
			labels.add("Director de cartera");
	        labels.add("Líder de QA");
	        labels.add("Director de costos");
			labels.add("Director de ventas");
			
	        data.setLabels(labels);
	        barModel.setData(data);

	        //Options
	        BarChartOptions options = new BarChartOptions();
	    
	        CartesianScales cScales = new CartesianScales();
	        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
	        linearAxes.setOffset(true);
	        linearAxes.setBeginAtZero(true);
	        CartesianLinearTicks ticks = new CartesianLinearTicks();
	        linearAxes.setTicks(ticks);
	        cScales.addYAxesData(linearAxes);
	        options.setScales(cScales);

	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Bar Chart");
	        options.setTitle(title);

	        Legend legend = new Legend();
	        legend.setDisplay(true);
	        legend.setPosition("top");
	        LegendLabel legendLabels = new LegendLabel();
	        legendLabels.setFontStyle("italic");
	        legendLabels.setFontColor("#2980B9");
	        legendLabels.setFontSize(24);
	        legend.setLabels(legendLabels);
	        options.setLegend(legend);

	        // disable animation
	        Animation animation = new Animation();
	        animation.setDuration(0);
	        options.setAnimation(animation);

	        barModel.setOptions(options);
	    }
	private void createPieModel() {
	        pieModel = new PieChartModel();
	        ChartData data = new ChartData();

	        PieChartDataSet dataSet = new PieChartDataSet();
	        List<Number> values = new ArrayList<>();
			int tec = 0;
			int cont = 0;
			int fact = 0;
			int com = 0;
			for(int i = 0; i<empleados.size();i++){
				if(empleados.get(i).getDependencia().equals("Tecnología")){
					tec++;
				}else if(empleados.get(i).getDependencia().equals("Facturación")){
					fact++;
				}else if(empleados.get(i).getDependencia().equals("Contabilidad")){
					cont++;
				}else if(empleados.get(i).getDependencia().equals("Comercial")){
					com++;
				}
			}
	        values.add(tec);
	        values.add(cont);
	        values.add(com);
			values.add(fact);
	        dataSet.setData(values);

	        List<String> bgColors = new ArrayList<>();
	        bgColors.add("rgb(255, 99, 132)");
	        bgColors.add("rgb(54, 162, 235)");
	        bgColors.add("rgb(255, 205, 86)");
			bgColors.add("rgb(255, 155, 63)");
	        dataSet.setBackgroundColor(bgColors);

	        data.addChartDataSet(dataSet);
	        List<String> labels = new ArrayList<>();
	        labels.add("Tecnología");
	        labels.add("Contabilidad");
	        labels.add("Comercial");
			labels.add("Facturación");
	        data.setLabels(labels);

	        pieModel.setData(data);
	    }
		public void createLineModel1() {
	        lineModel1 = new LineChartModel();
	        ChartData data = new ChartData();

	        LineChartDataSet dataSet = new LineChartDataSet();
	        List<Object> values = new ArrayList<>();
			int sanitas = 0;
			int alisal = 0;
			int sura = 0;
			int nueva = 0;
			for(int i=0;i<empleados.size();i++){
				if(empleados.get(i).getEps().equals("EPS-Sanitas")){
					sanitas++;
				}else if(empleados.get(i).getEps().equals("Aliansalud EPS")){
					alisal++;
				}else if(empleados.get(i).getEps().equals("Nueva EPS")){
					nueva++;
				}else if(empleados.get(i).getEps().equals("EPS-Sura")){
					sura++;
				}
			}
	        values.add(sanitas);
	        values.add(alisal);
	        values.add(nueva);
	        values.add(sura);
	        
	        dataSet.setData(values);
	        dataSet.setFill(false);
	        dataSet.setLabel("Empleados por EPS");
	        dataSet.setBorderColor("rgb(75, 192, 192)");
	        dataSet.setTension(0.1);
	        data.addChartDataSet(dataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("Sanitas");
	        labels.add("Aliansalud");
	        labels.add("Nueva EPS");
	        labels.add("Sura");
	        data.setLabels(labels);

	        //Options
	        LineChartOptions options = new LineChartOptions();
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Line Chart");
	        options.setTitle(title);

	        Title subtitle = new Title();
	        subtitle.setDisplay(true);
	        subtitle.setText("Line Chart Subtitle");

	        lineModel1.setOptions(options);
	        lineModel1.setData(data);
	    }
		public void createLineModel() {
	        lineModel = new LineChartModel();
	        ChartData data = new ChartData();

	        LineChartDataSet dataSet = new LineChartDataSet();
	        List<Object> values = new ArrayList<>();
			int colpen = 0;
			int prote = 0;
			int prove = 0;
			int skand = 0;
		
			for(int i=0;i<empleados.size();i++){
				if(empleados.get(i).getPension().equals("Colpensiones")){
					colpen++;
				}else if(empleados.get(i).getPension().equals("Protección")){
					prote++;
				}else if(empleados.get(i).getPension().equals("Provenir")){
					prove++;
				}else if(empleados.get(i).getPension().equals("Skandia")){
					skand++;
				}
			}
	        values.add(colpen);
	        values.add(prote);
	        values.add(prove);
	        values.add(skand);
	        
	        dataSet.setData(values);
	        dataSet.setFill(false);
	        dataSet.setLabel("Empleados por EPS");
	        dataSet.setBorderColor("rgb(70, 242, 152)");
	        dataSet.setTension(0.1);
	        data.addChartDataSet(dataSet);

	        List<String> labels = new ArrayList<>();
	        labels.add("Colpensiones");
	        labels.add("Protección");
	        labels.add("Provenir");
	        labels.add("Skandia");
	        data.setLabels(labels);

	        //Options
	        LineChartOptions options = new LineChartOptions();
	        Title title = new Title();
	        title.setDisplay(true);
	        title.setText("Empleados por Pensión");
	        options.setTitle(title);

	        Title subtitle = new Title();
	        subtitle.setDisplay(true);
	        subtitle.setText("Line Chart Subtitle");

	        lineModel.setOptions(options);
	        lineModel.setData(data);
	    }
}
