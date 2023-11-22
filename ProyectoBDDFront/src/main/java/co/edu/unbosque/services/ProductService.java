package co.edu.unbosque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import co.edu.unbosque.persistence.Product;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class ProductService {
	private List<Product> products;

	@PostConstruct
	public void init() {
		products = new ArrayList<>();
		products.add(new Product(1, "f230fh0g", "Bamboo Watch", "Product Description", "LogoUni.jpg", 65,
				"Accessories", 24, 5));
		products.add(new Product(1, "f230fh0g", "Bamboo Watch", "Product Description", "LogoUni.jpg", 65,
				"Accessories", 24, 5));

	}

	public List<Product> getProducts() {
		return new ArrayList<>(products);
	}

	public List<Product> getProducts(int size) {

		if (size > products.size()) {
			Random rand = new Random();

			List<Product> randomList = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				int randomIndex = rand.nextInt(products.size());
				randomList.add(products.get(randomIndex));
			}

			return randomList;
		}

		else {
			return new ArrayList<>(products.subList(0, size));
		}

	}

	public List<Product> getClonedProducts(int size) {
		List<Product> results = new ArrayList<>();
		List<Product> originals = getProducts(size);
		for (Product original : originals) {
			results.add(original.clone());
		}

		// make sure to have unique codes
		for (Product product : results) {
			product.setCode(UUID.randomUUID().toString().replace("-", "").substring(0, 8));
		}

		return results;
	}

}
