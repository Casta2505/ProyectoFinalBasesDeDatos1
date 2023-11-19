function redirectToPage(tipo) {
    if (tipo === 'Personal1.jpg') {
      window.location.href = 'colaboradores.xhtml';
    } else if (tipo === 'ProductosBimbo.png') {
      PF('myDialog').show();	
    } else if (tipo === 'Grafica.png'){
		window.location.href = 'graficas.xhtml';
	}
  }
  function abrirPagina() {
		var selectedOption = document.getElementById('floatingSucursal').value;

		switch (selectedOption) {
			case 'Bogota':
				window.location.href = 'bogota.xhtml';
				break;
			case 'Leticia':
				window.location.href = 'leticia.xhtml';
				break;
			case 'Villavicencio':
				window.location.href = 'villavicencio.xhtml';
				break;
			case 'Nariño':
				window.location.href = 'nariño.xhtml';
				break;
			case 'Pasto':
				window.location.href = 'pasto.xhtml';
				break;
			case 'Caracas':
				window.location.href = 'caracas.xhtml';
				break;
			default:
				// No se seleccionó ninguna opción
				break;
		}
	}