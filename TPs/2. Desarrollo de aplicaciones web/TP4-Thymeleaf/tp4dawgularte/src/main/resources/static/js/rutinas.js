/**
 * Función que modifica el botón que expande y colapsa el panel que contiene el formulario de creación
 * de autos. Cuando no está desplegado el panel muestra leyenda para crear datos y al desplegar cambia 
 * el texto para indicar cierre de panel
 */
function expandirPanel() {
    const boton = document.getElementById("btn-crear-auto");

    if (boton.getAttribute("class") === "btn btn-success") {
        boton.setAttribute("class", "btn btn-secondary");
        boton.setAttribute("title", "Click para cerrar el panel de creación de autos");
        boton.innerHTML = `<h3><i class="bi bi-dash-square"></i> Cerrar panel</h3>`;
    } else {
        boton.setAttribute("class", "btn btn-success");
        boton.setAttribute("title", "Click para desplegar el panel de creación de autos");
        boton.innerHTML = `<h3><i class="bi bi-plus-circle"></i> Crear auto</h3>`;
    }
}

/**
 * Lógica para mostrar temporalmente los mensajes de éxito/error recibidos desde el servidor
 */
document.addEventListener("DOMContentLoaded", () => {
    const msjServidor = document.getElementById('mensaje-servidor');
    if (msjServidor) {
        new bootstrap.Toast(msjServidor, {
            delay: 5000 // Se oculta después de 5 segundos
        }).show();
    }
});

/**
 * Petición DELETE vía API fetch
 */
function borrarRegistro(id) {
    const confirmaBorrar = confirm(`¿Desea eliminar el registro actual (id: ${id})?`);
    if (confirmaBorrar) {
        const formBorrarRegistro = document.createElement("form");
        formBorrarRegistro.action="/autos/borrar/" + id;
        formBorrarRegistro.method = "post";
        document.body.appendChild(formBorrarRegistro);
        formBorrarRegistro.submit();
        document.body.removeChild(formBorrarRegistro);
    }
}

function abrirModal(id) {
    const modal = new bootstrap.Modal(document.getElementById('modal-formulario'));
    const tituloModal = document.getElementById("titulo-modal");
    const formulario = document.getElementById("formulario-datos");
    const btnFormulario = document.getElementById("btn-formulario");
    // Cargo las opciones según se trate de creación o modificación de datos
    if (id === 0) {
        tituloModal.textContent = 'Crear nuevo auto';
        formulario.action = "/autos/guardar/";
        btnFormulario.textContent = "Crear auto";
    } else {
        tituloModal.textContent = 'Modificar datos del auto';
        formulario.action = "/autos/editar/" + id;
        const campoOculto = document.createElement("input");
        campoOculto.type = "hidden";
        campoOculto.value = id;
        campoOculto.setAttribute("th:field", "*{id}");
        formulario.append(campoOculto);
        btnFormulario.textContent = "Modificar auto";

        // Creo formulario temporal para lanzar petición POST que recupere los datos del auto a modificar
        const tmpForm = document.createElement("form");
        tmpForm.method = "get";
        tmpForm.action = "/autos/listar/" + id;
        document.body.appendChild(tmpForm);
        tmpForm.submit();
        document.body.removeChild(tmpForm);
    }

    modal.show();
}