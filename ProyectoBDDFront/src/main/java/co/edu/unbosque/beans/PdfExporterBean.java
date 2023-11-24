package co.edu.unbosque.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.inject.Named;

import java.io.File;
import java.io.IOException;

import org.primefaces.PrimeFaces;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;
import org.primefaces.component.piechart.PieChart;
import org.primefaces.util.ComponentUtils;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;



@Named
@RequestScoped
public class PdfExporterBean {
	private PDFOptions pdfOpt;

	@PostConstruct
	public void init() {
		customizationOptions();
	}

	public void customizationOptions() {
		pdfOpt = new PDFOptions();
		pdfOpt.setFacetBgColor("#F88017");
		pdfOpt.setFacetFontColor("#0000ff");
		pdfOpt.setFacetFontStyle("BOLD");
		pdfOpt.setCellFontSize("12");
		pdfOpt.setFontName("Courier");
		pdfOpt.setOrientation(PDFOrientationType.LANDSCAPE);
	}

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		String separator = File.separator;
		
	    UIComponent pieChart = FacesContext.getCurrentInstance().getViewRoot().findComponent("pieChart");
	    // Agregar la imagen del pieChart al documento PDF
	}
}
