// rutas de endpoints
const url = "http://localhost";
const puerto = "8080";
const baseUrl = `${url}:${puerto}`;
const epGuardarAutos = `${baseUrl}/autos`;
const epListarAutos = `${baseUrl}/autos`;
const epListarUnAuto = `${baseUrl}/auto/`;
const epModificarAuto = `${baseUrl}/auto/`;
const epBorrarAuto = `${baseUrl}/auto/`;

const formulario = document.getElementById("form-auto");
const resultadoFormulario = document.getElementById("resultado-form");

// Agrego evento "submit" al formulario para enviar los valores del formulario
formulario.addEventListener("submit", async evento => {
    evento.preventDefault();
    const marca = document.getElementById("marca").value;
    const precio = parseFloat(document.getElementById("precio").value);

    const datosAuto = { marca, precio };

    // Envío los datos de formulario a endPoint POST
    axios.post(epGuardarAutos, datosAuto)
        .then(response => {
            const respuesta = response.data;
            // muestro en pantalla el mensaje de retorno del servidor
            // Si no hubo errores, el mensaje desaparece a los 5 segundos
            // Si hubo errores, el mensaje desaparece luego de un minuto
            const tiempoMensajeOk = 5 * 1000;
            const tiempoMensajeErr = 60 * 1000;
            resultadoFormulario.innerHTML = `<button type="button" class="btn-close" data-bs-dismiss="alert"></button>`;
            resultadoFormulario.innerHTML += "<strong>Atención</strong> " + respuesta;
            resultadoFormulario.style.display = "block";
            document.getElementById("marca").value = "";
            document.getElementById("precio").value = "";
            setTimeout(() => {
                document.getElementById("resultado-form").textContent = "";
                document.getElementById("resultado-form").style.display = "none";
            }, respuesta.includes("Se creo el nuevo auto") ? tiempoMensajeOk : tiempoMensajeErr);

            mostrarDatosTabla();
        });
});

mostrarDatosTabla();

async function modificarRegistro(id) {
    const formModif = document.getElementById("form-modif-datos");
    const resulFormModif = document.getElementById("resultado-form-modif");
    const datosAModificar = await obtenerDatos(id);
    console.log(epListarUnAuto + id);
    console.log(datosAModificar);

    document.getElementById("marca-modif").value = datosAModificar.marca;
    document.getElementById("precio-modif").value = datosAModificar.precio;

    document.getElementById("btn-submit-modif").addEventListener("click", async evento => {
        evento.preventDefault();
        const marca = document.getElementById("marca-modif").value;
        const precio = parseFloat(document.getElementById("precio-modif").value);

        const datosAuto = { marca, precio };

        axios.put(epModificarAuto + id, datosAuto)
            .then(response => {
                const respuesta = response.data;

                const tiempoMensajeOk = 5 * 1000;
                const tiempoMensajeErr = 60 * 1000;

                resulFormModif.textContent = JSON.stringify(respuesta);
                resulFormModif.style.display = "block";
                formModif.reset();
                console.log(respuesta);
                setTimeout(() => {
                    resulFormModif.textContent = "";
                    resulFormModif.style.display = "none";
                    document.querySelector("button.btn.btn-danger").click();
                    mostrarDatosTabla();
                },  typeof respuesta === "object" ? tiempoMensajeOk : tiempoMensajeErr);
            }
        );
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
                mostrarDatosTabla();
                console.log("muestro datos tabla")
            })
    }
}

// Recupero listado completo de datos vía GET
async function mostrarDatosTabla() {
    const datos = await obtenerDatos();
    console.log(datos);
    let filasTablaHtml = "";
    for (let d of datos) {
        const precioFormateado = d.precio.toLocaleString("es-AR", { currency: "ARS", style: "currency" });

        // Defino botones de acciones
        const iconoBtnEditar = `<i class="bi bi-pencil-square"></i>`;
        const iconoBtnBorrar = `<i class="bi bi-trash3-fill"></i>`;
        const btnEditar = `<a class="btn btn-success" title="Click para editar este registro" onclick="modificarRegistro(${d.id})" data-bs-toggle="modal" data-bs-target="#modal-modif-datos">${iconoBtnEditar}</a>`;
        const btnBorrar = `<a class="btn btn-danger" title="Click para borrar este registro" onclick="borrarRegistro(${d.id})">${iconoBtnBorrar}</a>`;

        // Valores para las celdas de la tabla
        const celdaId = `<td class="text-center">${d.id}</td>`;
        const celdaMarca = `<td>${d.marca}</td>`;
        const celdaPrecio = `<td class="text-end">${precioFormateado}</td>`;
        const celdaAcciones = `<td class="text-center">${btnEditar}&nbsp;${btnBorrar}</td>`;

        // Genero la fila de la tabla con las celdas definidas
        filasTablaHtml += `<tr>${celdaId}${celdaMarca}${celdaPrecio}${celdaAcciones}</tr>`;
    }
    // Inserto las filas recuperadas en el cuerpo de la tabla
    document.getElementsByTagName("tbody")[0].innerHTML = filasTablaHtml;
    document.getElementsByTagName("tfoot")[0].innerHTML = `<tr class="text-end"><td colspan="4">Se recuperaron <strong>${datos.length}</strong> registros</td></tr>`;
}

async function obtenerDatos(id) {
    const url = id === undefined ? epListarAutos : epListarUnAuto + id;
    return await axios.get(url)
        .then(response => response.data)
        .catch(error => error);
}