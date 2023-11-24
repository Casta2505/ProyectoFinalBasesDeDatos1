 package co.edu.unbosque.persistence;

public class Imagen {
	private String url;
	private String tipo;
	public Imagen(String url,String tipo) {
		super();
		this.url = url;
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
