// rutas de endpoints
const url = "http://localhost";
const puerto = "9090";
const baseUrl = `${url}:${puerto}`;
const epGuardarAutos = `${baseUrl}/auto/crear/`;
const epListarAutos = `${baseUrl}/autos/listar/`;
const epListarAutoId = `${baseUrl}/auto/listar/`;
const epModificarAuto = `${baseUrl}/auto/editar/`;
const epBorrarAuto = `${baseUrl}/auto/borrar/`;

// Obtengo los elementos DOM de formulario de alta y bloque de resultados
const formulario = document.getElementById("form-alta-datos");
const resultadoFormulario = document.getElementById("resultado-formulario");

formulario.addEventListener("submit", async evento => {
    evento.preventDefault();
    // Valores de campos de formulario a variables para crear atributos de entidad
    const marca = document.getElementById("marca").value;
    const modelo = document.getElementById("modelo").value;
    const color = document.getElementById("color").value;
    const precio = parseFloat(document.getElementById("precio").value);
    const patente = document.getElementById("patente").value;
    const nroChasis = document.getElementById("chasis").value;
    const nroMotor = document.getElementById("motor").value;

    // Genero el objeto entidad con los datos de los campor del formulario
    const entidad = {
        marca,
        modelo,
        color,
        precio,
        patente,
        nroChasis,
        nroMotor
    };

    console.log(entidad);
    console.log(JSON.stringify(entidad));

    // Envío los datos de formulario a endPoint POST
    const respuesta = await fetch(epGuardarAutos, {
        method: "POST",
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(entidad)
    });

    // Mensaje de retorno del servidor
    // Si no hubo errores, el mensaje desaparece a los 5 segundos
    // Si hubo errores, el mensaje desaparece luego de un minuto
    const tiempoMensajeOk = 5 * 1000;
    const tiempoMensajeErr = 60 * 1000;
    resultadoFormulario.innerHTML = `<button type="button" class="btn-close" data-bs-dismiss="alert"></button>`;
    resultadoFormulario.innerHTML += "<strong>Atención</strong> " + await respuesta.text();
    resultadoFormulario.style.display = "block";
    // Limpio los campos del formulario
    formulario.reset();
    // Espero el intervalo definido según éxito o error y oculto en mensaje
    setTimeout(() => {
        document.getElementById("resultado-formulario").textContent = "";
        document.getElementById("resultado-formulario").style.display = "none";
    }, respuesta.ok ? tiempoMensajeOk : tiempoMensajeErr);

    mostrarTabla();

});

mostrarTabla();

function modificarRegistro(id) {
    fetch(epListarAutoId + id)
        .then(responde => responde.json())
        .then(datos => {
            // Cargo los valores de la entidad recuperada del servidor en los campos de formularo para editar
            document.getElementById("marcaModifica").value = datos.marca;
            document.getElementById("modeloModifica").value = datos.modelo;
            document.getElementById("precioModifica").value = datos.precio;
            document.getElementById("patenteModifica").value = datos.patente;
            document.getElementById("chasisModifica").value = datos.nroChasis;
            document.getElementById("chasisModifica").setAttribute("disabled", true);
            document.getElementById("motorModifica").value = datos.nroMotor;
            document.getElementById("motorModifica").setAttribute("disabled", true);
            
            // Selecciono el color en el combo de opciones
            Array.from(document.getElementById("colorModifica").children).forEach(op => {
                if(op.value === datos.color.toLowerCase()) {
                    op.setAttribute("selected", true);
                }
            });

            // Agrego evento de submit a botón de formulario de modificación de datos
            const formModificacion = document.getElementById("form-modifica-datos");
            const resultadoformModificacion = document.getElementById("resultado-form-modifica");
            formModificacion.addEventListener("submit", async evento => {
                evento.preventDefault();
                // Valores de campos de formulario a variables para crear atributos de entidad
                const marca = document.getElementById("marcaModifica").value;
                const modelo = document.getElementById("modeloModifica").value;
                const color = document.getElementById("colorModifica").value;
                const precio = parseFloat(document.getElementById("precioModifica").value);
                const patente = document.getElementById("patenteModifica").value;
                const nroChasis = document.getElementById("chasisModifica").value;
                const nroMotor = document.getElementById("motorModifica").value;

                // Genero el objeto entidad con los datos de los campor del formulario
                const entidad = {
                    marca,
                    modelo,
                    color,
                    precio,
                    patente,
                    nroChasis,
                    nroMotor
                };

                console.log(entidad);
                console.log(JSON.stringify(entidad));

                // Envío los datos de formulario a endPoint POST
                const respuesta = await fetch(epModificarAuto + id, {
                    method: "PUT",
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(entidad)
                });

                // Mensaje de retorno del servidor
                // Si no hubo errores, el mensaje desaparece a los 5 segundos
                // Si hubo errores, el mensaje desaparece luego de un minuto
                const tiempoMensajeOk = 5 * 1000;
                const tiempoMensajeErr = 60 * 1000;
                resultadoformModificacion.innerHTML += "<strong>Atención</strong> " + await respuesta.text();
                resultadoformModificacion.style.display = "block";
                // Limpio los campos del formulario
                formModificacion.reset();
                document.getElementById("colorModifica").children[0].selected = true;
                // Espero el intervalo definido según éxito o error y oculto en mensaje
                setTimeout(() => {
                    document.getElementById("resultado-form-modifica").textContent = "";
                    document.getElementById("resultado-form-modifica").style.display = "none";
                    document.querySelector("#modal-formulario-edicion > div > div > div.modal-footer.bg-secondary-subtle > button").click();
                }, respuesta.ok ? tiempoMensajeOk : tiempoMensajeErr);

                // Cierro el Modal desencadenando el evento de cierre del botón
                mostrarTabla();
            });
        });
}

function borrarRegistro(id) {
    const confirmaBorrar = confirm(`¿Desea eliminar el registro actual (id: ${id})?`);
    if (confirmaBorrar) {
        fetch(epBorrarAuto + id, {
            method: "DELETE",
        })
        .then(response => {
            console.log(response)
            document.getElementsByTagName("tbody")[0].innerHTML = "";
            console.log("borro el HTML interior de TBODY (id: " + id + ")");
            mostrarTabla();
            console.log("muestro datos tabla")
        })
    }
}

function mostrarTabla() {
    fetch(epListarAutos)
        .then(response => response.json())
        .then(datos => {
            let htmlFilasTabla = "";
            for(const registro of datos) {
                const precioFormateado = registro.precio.toLocaleString("es-AR", { currency: "ARS", style: "currency" });

                // Defino botones de acciones
                const iconoBtnEditar = `<i class="bi bi-pencil-square"></i>`;
                const iconoBtnBorrar = `<i class="bi bi-trash3-fill"></i>`;
                const btnEditar = `<a class="btn btn-success" title="Click para editar este registro" onclick="modificarRegistro(${registro.id})" data-bs-toggle="modal" data-bs-target="#modal-formulario-edicion">${iconoBtnEditar}</a>`;
                const btnBorrar = `<a class="btn btn-danger" title="Click para borrar este registro" onclick="borrarRegistro(${registro.id})">${iconoBtnBorrar}</a>`;

                // Valores para las celdas de la tabla
                const celdaId = `<td class="text-center">${registro.id}</td>`;
                const celdaMarca = `<td>${registro.marca}</td>`;
                const celdaModelo = `<td>${registro.modelo}</td>`;
                const celdaColor = `<td>${registro.color}</td>`;
                const celdaPrecio = `<td class="text-end">${precioFormateado}</td>`;
                const celdaPatente = `<td class="text-center">${registro.patente}</td>`;
                const celdaNroChasis = `<td class="text-end">${registro.nroChasis}</td>`;
                const celdaNroMotor = `<td class="text-end">${registro.nroMotor}</td>`;
                const celdaAcciones = `<td class="text-center">${btnEditar}&nbsp;${btnBorrar}</td>`;

                // Genero la fila de la tabla con las celdas definidas
                htmlFilasTabla += `<tr>${celdaId}${celdaMarca}${celdaModelo}${celdaColor}${celdaPrecio}${celdaPatente}${celdaNroChasis}${celdaNroMotor}${celdaAcciones}</tr>`;
            }
            // Inserto las filas recuperadas en el cuerpo de la tabla
            document.getElementsByTagName("tbody")[0].innerHTML = htmlFilasTabla;
            document.getElementsByTagName("tfoot")[0].innerHTML = `<tr class="text-end"><td colspan="9">Se recuperaron <strong>${datos.length}</strong> registros</td></tr>`;
        });
}