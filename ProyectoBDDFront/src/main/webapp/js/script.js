function iniciarSesion() {
	var correo = document.getElementById('correo').value;
	var contrasena = document.getElementById('contrasena').value;

		if (correo === 'joyolag@unbosque.edu.co' && contrasena === '12341234') {
		window.location.href = '/tablaempleados.xhtml';
	} else {
		alert('Credenciales incorrectas');
	}
}
//<![CDATA[
function exportChart() {
	//export image
	$('#output').empty().append(PF('chart').exportAsImage());
	
	//show the dialog
	PF('dlg').show();
}
    //]]>
function exportPDF() {
	//export image
	$('#output').empty().append(PF('chart').exportAsImage());
	
	//show the dialog
	PF('dlg').show();
}
    //]]>