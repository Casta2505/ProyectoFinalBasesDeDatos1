function obtenerTodasLasGanancias() {
	var controllers = [
		{ city: 'Bogota', url: 'http://localhost:8081/api/bogota/ganancia' },
		{ city: 'Leticia', url: 'http://localhost:8081/api/leticia/ganancia' },
		{ city: 'Nariño', url: 'http://localhost:8081/api/nariño/ganancia' },
		{ city: 'Pasto', url: 'http://localhost:8081/api/pasto/ganancia' },
		{ city: 'Caracas', url: 'http://localhost:8081/api/caracas/ganancia' },
		{ city: 'Villavicencio', url: 'http://localhost:8081/api/villavicencio/ganancia' }
	];

	var ganancias = [];

	controllers.forEach(function(controller) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', controller.url, true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					var ganancia = JSON.parse(xhr.responseText);
					ganancias.push({ city: controller.city, ganancia: ganancia });
					// Verificar si se han obtenido todas las ganancias
					if (ganancias.length === controllers.length) {
						// Todas las ganancias han sido obtenidas
						procesarGanancias(ganancias);
					}
				} else {
					console.error('Error al obtener la ganancia de ' + controller.city + ':', xhr.statusText);
				}
			}
		};

		xhr.send();
	});
}

function procesarGanancias(ganancias) {
	// Aquí puedes procesar las ganancias como desees, por ejemplo, agregarlas a la gráfica

	// Ordenar las ganancias por ciudad para asegurar el orden en la gráfica
	ganancias.sort(function(a, b) {
		return a.city.localeCompare(b.city);
	});

	var labels = ganancias.map(function(ganancia) {
		return ganancia.city;
	});

	var data = {
		labels: labels,
		datasets: [{
			label: 'Ganancias(En %)	',
			data: ganancias.map(function(ganancia) {
				return ganancia.ganancia;
			}),
			backgroundColor: 'rgba(75, 192, 192, 0.5)',
			borderColor: 'rgba(75, 192, 192, 1)',
			borderWidth: 1
		}]
	};

	var options = {
		scales: {
			y: {
				beginAtZero: true
			}
		}
	};

	var ctx = document.getElementById('barChart').getContext('2d');
	new Chart(ctx, {
		type: 'bar',
		data: data,
		options: options
	});
}

// Llamar a la función para obtener todas las ganancias
obtenerTodasLasGanancias();
function hacerPDF() {
	// Obtener el canvas de la gráfica
	var canvas = document.getElementById('barChart');

	// Crear un elemento <img> para insertar el contenido del canvas
	var imgData = canvas.toDataURL('image/png');
	var img = document.createElement('img');
	img.src = imgData;
	img.style.width = '800px'; // Cambiar el ancho de la imagen
	img.style.height = '600px'; // Cambiar el alto de la imagen

	// Crear un elemento <div> para contener la imagen de la gráfica
	var container = document.createElement('div');
	container.appendChild(img);

	// Convertir el contenido HTML en un PDF
	html2pdf().from(container).save('Informe.pdf');
}
