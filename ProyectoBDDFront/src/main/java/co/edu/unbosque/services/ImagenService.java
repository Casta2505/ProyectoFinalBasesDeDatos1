package co.edu.unbosque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.edu.unbosque.persistence.Imagen;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
@Named
@ApplicationScoped
public class ImagenService {
	private List<Imagen> imagenes;
	
	@PostConstruct
	public void init() {
		imagenes = new ArrayList<>();
		imagenes.add(new Imagen("LogoEmpresa.jpg", "Promocion"));
		imagenes.add(new Imagen("LogoEmpresa.jpg", "Promocion"));
		imagenes.add(new Imagen("LogoEmpresa.jpg", "Noticia"));
	}
	public List<Imagen> getProducts(int size) {

        if (size > imagenes.size()) {
            Random rand = new Random();

            List<Imagen> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(imagenes.size());
                randomList.add(imagenes.get(randomIndex));
            }

            return randomList;
        }

        else {
            return new ArrayList<>(imagenes.subList(0, size));
        }

    }
}
