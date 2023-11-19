package co.edu.unbosque.beans;


import java.io.Serializable;
import java.util.List;

import co.edu.unbosque.persistence.Imagen;
import co.edu.unbosque.services.ImagenService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ImagenBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Imagen> imagenes;
	@Inject
	private ImagenService service;
	
	@PostConstruct
	public void init() {
		setImagenes(service.getProducts(3));
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
}
