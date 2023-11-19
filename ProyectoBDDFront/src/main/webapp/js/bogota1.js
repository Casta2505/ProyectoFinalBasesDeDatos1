function abrirDialog() {
	PF('myDialog').show();
}
function guardarProducto() {
	var producto = encodeURIComponent(document.getElementById('inputproducto').value);
	var precioCompra = encodeURIComponent(document.getElementById('inputventa').value);
	var precioVenta = encodeURIComponent(document.getElementById('inputcompra').value);

	// Realizar la solicitud para guardar el producto
	var url = "http://localhost:8081/api/bogota";
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	xhr.onload = function() {
		if (xhr.status === 201) {
			// Guardado exitoso
			console.log("Producto guardado exitosamente");
			// Realizar las acciones adicionales necesarias, como actualizar la tabla
		} else {
			// Error al guardar el producto
			console.error("Error al guardar el producto:", xhr.responseText);
		}
	};
	xhr.send("producto=" + producto + "&costoproducto=" + precioCompra + "&precioventa=" + precioVenta);

	alert("Producto guardado exitosamente");
}
var url = "http://localhost:8081/api/bogota";
var xhr = new XMLHttpRequest();
xhr.open('GET', url, true);

xhr.onload = function() {
	if (xhr.status == 200) {
		var productos = JSON.parse(xhr.responseText);
		generarFilasTabla(productos);
	} else {
		console.error("Error al obtener los productos:", xhr.responseText);
	}
};
xhr.send(null);

function generarFilasTabla(productos) {
	var tbody = document.getElementById("tablaProductosBody");
	tbody.innerHTML = "";

	for (var i = 0; i < productos.length; i++) {
		var producto = productos[i];

		var fila = document.createElement("tr");
		var columnaID = document.createElement("td");
		var columnaProducto = document.createElement("td");
		var columnaCosto = document.createElement("td");
		var columnaPrecio = document.createElement("td");
		var columnaFunciones = document.createElement("td");

		columnaID.textContent = producto.id;
		columnaProducto.textContent = producto.producto;
		columnaCosto.textContent = producto.costoproducto;
		columnaPrecio.textContent = producto.precioventa;

		// Crear el botón de Editar
		var botonEditar = document.createElement("button");
		botonEditar.setAttribute("type", "button");
		botonEditar.classList.add("btn", "btn-primary");
		botonEditar.innerHTML = 'Editar <i class="bi bi-check"></i>';

		botonEditar.addEventListener("click", function(event) {
			var productoData = JSON.parse(event.target.getAttribute("data-producto"));
			editarProducto(productoData);
		});

		// Agregar atributo "data-producto" al botón de Editar
		botonEditar.setAttribute("data-producto", JSON.stringify(producto));

		// Crear el botón de Eliminar
		var botonEliminar = document.createElement("button");
		botonEliminar.setAttribute("type", "button");
		botonEliminar.classList.add("btn", "btn-danger");
		botonEliminar.innerHTML = 'Eliminar <i class="bi bi-x"></i>';


		botonEliminar.addEventListener("click", function(event) {
			var productoData = JSON.parse(event.target.getAttribute("data-producto"));
			eliminarProducto(productoData);
		});

		// Agregar atributo "data-producto" al botón de Eliminar
		botonEliminar.setAttribute("data-producto", JSON.stringify(producto));

		columnaFunciones.appendChild(botonEditar);
		columnaFunciones.appendChild(botonEliminar);

		fila.appendChild(columnaID);
		fila.appendChild(columnaProducto);
		fila.appendChild(columnaCosto);
		fila.appendChild(columnaPrecio);
		fila.appendChild(columnaFunciones);

		tbody.appendChild(fila);
	}
	function editarProducto(producto) {
		var id = producto.id;
		var nuevoProducto = encodeURIComponent(prompt("Ingrese el nuevo nombre del producto:", producto.producto));
		var nuevoCosto = encodeURIComponent(prompt("Ingrese el nuevo costo del producto:", producto.costoproducto));
		var nuevoPrecio = encodeURIComponent(prompt("Ingrese el nuevo precio de venta del producto:", producto.precioventa));


		// Validar que se haya ingresado un nuevo nombre de producto
		if (nuevoProducto !== null) {
			// Realizar la solicitud para actualizar el producto
			var url = "http://localhost:8081/api/bogota/" + id;
			var xhr = new XMLHttpRequest();
			xhr.open('PUT', url, true);
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.onload = function() {
				if (xhr.status === 200) {
					// Actualización exitosa
					console.log("Producto actualizado exitosamente");
					// Realizar las acciones adicionales necesarias, como actualizar la tabla
				} else {
					// Error al actualizar el producto
					console.error("Error al actualizar el producto:", xhr.responseText);
				}
			};
			xhr.send("id=" + id + "&producto=" + nuevoProducto + "&costoproducto=" + nuevoCosto + "&precioventa=" + nuevoPrecio);

		}
	}


	function eliminarProducto(producto) {
		var id = producto.id;
		var confirmacion = confirm("¿Está seguro que desea eliminar el producto?");
		// Validar que se haya ingresado un nuevo nombre de producto
		if (confirmacion) {
			// Realizar la solicitud para actualizar el producto
			var url = "http://localhost:8081/api/bogota/" + id;
			var xhr = new XMLHttpRequest();
			xhr.open('DELETE', url, true);
			xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
			xhr.onload = function() {
				if (xhr.status === 200) {
					// Actualización exitosa
					console.log("Producto eliminado exitosamente");
					
					// Realizar las acciones adicionales necesarias, como actualizar la tabla
				} else {
					// Error al actualizar el producto
					console.error("Error al eliminar el producto:", xhr.responseText);
				}
			};
			xhr.send("id=" + id);
			alert("Producto eliminado exitosamente");
		}
	}




}
