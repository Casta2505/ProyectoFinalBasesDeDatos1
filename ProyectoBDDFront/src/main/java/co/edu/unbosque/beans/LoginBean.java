package co.edu.unbosque.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class LoginBean {

	private String correo;
	private String contrasena;

	// Getters y Setters

	public String iniciarSesion() {
		if ("joyolag@unbosque.edu.co".equals(correo) && "12345".equals(contrasena)) {
			// Autenticación exitosa, redirige a la página deseada
			return "/tablaempleados?faces-redirect=true";
		} else {
			// Autenticación fallida, muestra un mensaje de error
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciales incorrectas", null));
			return null;
		}
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
