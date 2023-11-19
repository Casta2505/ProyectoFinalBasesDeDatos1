function abrirDialog() {
	PF('myDialog').show();
}
function guardarColaborador() {
	var nombre = encodeURIComponent(document.getElementById('inputNombre').value);
	var cedula = encodeURIComponent(document.getElementById('inputCedula').value);
	var sucursal = encodeURIComponent(document.getElementById('inputSucursal').value);
	var edad = encodeURIComponent(document.getElementById('inputEdad').value);
	var cargo = encodeURIComponent(document.getElementById('inputCargo').value);

	// Realizar la solicitud para guardar el colaborador
	var url = "http://localhost:8081/api/colaborador";
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.onload = function() {
		if (xhr.status === 201) {
			// Guardado exitoso
			console.log("Colaborador guardado exitosamente");
			// Realizar las acciones adicionales necesarias, como actualizar la tabla
		} else {
			// Error al guardar el colaborador
			console.error("Error al guardar el colaborador:", xhr.responseText);
		}
	};
	xhr.send("nombre=" + nombre + "&cedula=" + cedula + "&sucursal=" + sucursal + "&edad=" + edad + "&cargo=" + cargo);

	alert("Colaborador guardado exitosamente");
}

var url = "http://localhost:8081/api/colaborador";
var xhr = new XMLHttpRequest();
xhr.open('GET', url, true);
xhr.onload = function() {
	if (xhr.status === 200) {
		var colaboradores = JSON.parse(xhr.responseText);
		generarFilasTabla(colaboradores);
	} else {
		console.error("Error al obtener los colaboradores:", xhr.responseText);
	}
};
xhr.send(null);

function generarFilasTabla(colaboradores) {
	var tbody = document.getElementById("tablaColaboradoresBody");
	tbody.innerHTML = "";

	for (var i = 0; i < colaboradores.length; i++) {
		var colaborador = colaboradores[i];

		var fila = document.createElement("tr");
		var columnaID = document.createElement("td");
		var columnaNombre = document.createElement("td");
		var columnaCedula = document.createElement("td");
		var columnaSucursal = document.createElement("td");
		var columnaEdad = document.createElement("td");
		var columnaCargo = document.createElement("td");
		var columnaFunciones = document.createElement("td");

		columnaID.textContent = colaborador.id;
		columnaNombre.textContent = colaborador.nombre;
		columnaCedula.textContent = colaborador.cedula;
		columnaSucursal.textContent = colaborador.sucursal;
		columnaEdad.textContent = colaborador.edad;
		columnaCargo.textContent = colaborador.cargo;

		// Crear el botón de Editar
		var botonEditar = document.createElement("button");
		botonEditar.setAttribute("type", "button");
		botonEditar.classList.add("btn", "btn-primary");
		botonEditar.innerHTML = 'Editar <i class="bi bi-check"></i>';

		botonEditar.addEventListener("click", function(event) {
			var colaboradorData = JSON.parse(event.target.getAttribute("data-colaborador"));
			editarColaborador(colaboradorData);
		});

		// Agregar atributo "data-colaborador" al botón de Editar
		botonEditar.setAttribute("data-colaborador", JSON.stringify(colaborador));

		// Crear el botón de Eliminar
		var botonEliminar = document.createElement("button");
		botonEliminar.setAttribute("type", "button");
		botonEliminar.classList.add("btn", "btn-danger");
		botonEliminar.innerHTML = 'Eliminar <i class="bi bi-x"></i>';

		botonEliminar.addEventListener("click", function(event) {
			var colaboradorData = JSON.parse(event.target.getAttribute("data-colaborador"));
			eliminarColaborador(colaboradorData);
		});

		// Agregar atributo "data-colaborador" al botón de Eliminar
		botonEliminar.setAttribute("data-colaborador", JSON.stringify(colaborador));

		columnaFunciones.appendChild(botonEditar);
		columnaFunciones.appendChild(botonEliminar);

		fila.appendChild(columnaID);
		fila.appendChild(columnaNombre);
		fila.appendChild(columnaCedula);
		fila.appendChild(columnaSucursal);
		fila.appendChild(columnaEdad);
		fila.appendChild(columnaCargo);
		fila.appendChild(columnaFunciones);

		tbody.appendChild(fila);
	}
}
function editarColaborador(colaborador) {
	var id = colaborador.id;
	var nuevoNombre = encodeURIComponent(prompt("Ingrese el nuevo nombre del colaborador:", colaborador.nombre));
	var nuevaCedula = encodeURIComponent(prompt("Ingrese la nueva cédula del colaborador:", colaborador.cedula));
	var nuevaSucursal = encodeURIComponent(prompt("Ingrese la nueva sucursal del colaborador:", colaborador.sucursal));
	var nuevaEdad = encodeURIComponent(prompt("Ingrese la nueva edad del colaborador:", colaborador.edad));
	var nuevoCargo = encodeURIComponent(prompt("Ingrese el nuevo cargo del colaborador:", colaborador.cargo));

	// Validar que se haya ingresado un nuevo nombre de colaborador
	if (nuevoNombre !== null) {
		// Realizar la solicitud para actualizar el colaborador
		var url = "http://localhost:8081/api/colaborador/" + id;
		var xhr = new XMLHttpRequest();
		xhr.open('PUT', url, true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onload = function() {
			if (xhr.status === 200) {
				// Actualización exitosa
				console.log("Colaborador actualizado exitosamente");
				// Realizar las acciones adicionales necesarias, como actualizar la tabla
			} else {
				// Error al actualizar el colaborador
				console.error("Error al actualizar el colaborador:", xhr.responseText);
			}
		};
		xhr.send("id=" + id + "&nombre=" + nuevoNombre + "&cedula=" + nuevaCedula + "&sucursal=" + nuevaSucursal + "&edad=" + nuevaEdad + "&cargo=" + nuevoCargo);
	}
}



function eliminarColaborador(colaborador) {
	var id = colaborador.id;
	var confirmacion = confirm("¿Está seguro que desea eliminar el colaborador?");

	// Validar que se haya confirmado la eliminación
	if (confirmacion) {
		// Realizar la solicitud para eliminar el colaborador
		var url = "http://localhost:8081/api/colaborador/" + id;
		var xhr = new XMLHttpRequest();
		xhr.open('DELETE', url, true);
		xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		xhr.onload = function() {
			if (xhr.status === 200) {
				// Eliminación exitosa
				console.log("Colaborador eliminado exitosamente");

				// Realizar las acciones adicionales necesarias, como actualizar la tabla
			} else {
				// Error al eliminar el colaborador
				console.error("Error al eliminar el colaborador:", xhr.responseText);
			}
		};
		xhr.send("id=" + id);

		alert("Colaborador eliminado exitosamente");
	}
}







