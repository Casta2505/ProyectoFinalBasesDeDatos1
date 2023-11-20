
function iniciarSesion() {
	var correo = document.getElementById('correo').value;
	var contrasena = document.getElementById('contrasena').value;

	// Simulación de autenticación (puedes reemplazar esto con una llamada AJAX a un servicio de autenticación real)
	if (correo === 'joyolag@unbosque.edu.co' && contrasena === '12345') {
		// Redirige o realiza alguna acción en caso de autenticación exitosa
		window.location.href = '/tablaempleados.xhtml';
	} else {
		alert('Credenciales incorrectas');
	}
}
