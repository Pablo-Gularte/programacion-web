// Variables para URLs de peticiones PUT y DELETE
const url = "http://localhost";
const puerto = "9090";
const baseUrl = `${url}:${puerto}`;
const epModificarAuto = `${baseUrl}/auto/editar/`;
const epBorrarAuto = `${baseUrl}/auto/borrar/`;
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
        formBorrarRegistro.method = "DELETE";
        document.body.appendChild(formBorrarRegistro);
        formBorrarRegistro.submit();
        document.body.removeChild(formBorrarRegistro);
    }
}