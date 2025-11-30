// Defino servidor, puerto y endpoints de trabajo
const servidor = "localhost";
const puerto = "8080";
const urlBase = `http://${servidor}:${puerto}`;
// Defino rutas de endpoints
const epListarGrados = "/grados";
const epInfoGrados = "/grados/info-grados";
const epInfoTurnos = "/grados/info-turnos";
const epBorrarEdtudiante = "/estudiantes/borrar/";

// Defino URLs de trabajo
const urlGrados = urlBase + epListarGrados;
const urlInfoGrados = urlBase + epInfoGrados;
const urlInfoTurnos = urlBase + epInfoTurnos;
const urlBorrarEstudiante = urlBase + epBorrarEdtudiante;

// Al cargar la página recupero los grados para generar los vectores de turnos y grados con los que se completan los menús desplegables
document.addEventListener("DOMContentLoaded", async () => {
    const infoGrados = await recuperarDatos(urlInfoGrados);
    const infoTurnos = await recuperarDatos(urlInfoTurnos);
    const tituloTurnosGrados = document.getElementById("titulo-turno-grado");

    if(infoGrados && infoTurnos) {
        tituloTurnosGrados.textContent = "Seleccione el grado que desea gestionar desplegando el turno correspondiente"; 
        generarAcordeon(infoTurnos, infoGrados);
    } else {
        tituloTurnosGrados = "No se encontraron datos de turnos ni grados en la base de datos"
    }
});

// Funciones de modificación y eliminación de datos
function editarEstudiante(idEstudiante) {
    // Creo el contenido del formulario de edición de datos para pasar al Modal

    // Creo los botones de acciones a mostrar en el modal
    // Botón GUARDAR
    const btnGuardar = document.createElement("button");
    btnGuardar.type = "button";
    btnGuardar.className = "btn btn-primary";
    btnGuardar.textContent = "Guardar cambios";
    
    // Botón CANCELAR
    const btnCancelar = document.createElement("button");
    btnCancelar.type = "reset";
    btnCancelar.className = "btn btn-secondary";
    btnCancelar.textContent = "Borrar todo";

    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-info-subtle",
        colorTituloModal: "bg-info",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Modificar datos</b>",
        contenidoModal: `<p>Formularo para editar los datos del estudiante ${idEstudiante}</p>`,
        botoneraModal: [btnGuardar, btnCancelar]
    });
}

function eliminarEstudiante(idEstudiante) {
    // Creo los botones de acciones a mostrar en el modal
    // Botón BORRAR
    const btnBorrarEstudiante = document.createElement("button");
    btnBorrarEstudiante.type = "button";
    btnBorrarEstudiante.className = "btn btn-danger";
    btnBorrarEstudiante.textContent = "Borrar datos";
    btnBorrarEstudiante.addEventListener("click", async () => {
        try {
            const response = await fetch(urlBorrarEstudiante + idEstudiante, {
                method: 'DELETE'
            });

            // Si recibo error del servidor muestro modal de aviso de error
            // Si no hay error muestro toast de operación exitosa
            if (!response.ok) {
                mostrarMensajeServidor("Error al intentar borrar el estudiante de ID: " + idEstudiante, "error");
            } else {
                mostrarMensajeServidor("Se ha borrado con éxito el estudiante de ID: " + idEstudiante, "ok");
            }

        } catch (error) {
            console.error("ERROR detectado al intentar borrar el estudiante de ID: " + idEstudiante);
            console.error(error);
            mostrarMensajeServidor("ERROR INESPERADO: " + error.message, "error");
        }
    });
    
    // Botón CANCELAR
    const btnCancelar = document.createElement("button");
    btnCancelar.type = "button";
    btnCancelar.className = "btn btn-secondary";
    btnCancelar.setAttribute("onclick", "cerrarModal()");
    btnCancelar.textContent = "Cancelar";

    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-danger-subtle",
        colorTituloModal: "bg-danger",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Borrar datos</b>",
        contenidoModal: `<p>Formularo para borrar los datos del estudiante ${idEstudiante}</p>`,
        botoneraModal: [btnBorrarEstudiante, btnCancelar]
    });
}

function asistenciasEstudiante(idEstudiante) {
    // Creo el contenido de asistencias a mostar para el idEstudiante recibido
    // Se recuperan los datos del servidor y se genera una tabla con las asistencias

    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-info-subtle",
        colorTituloModal: "bg-info",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Ver asistencias</b>",
        contenidoModal: `<p>Formularo para mostrar las asistencias del estudiante ${idEstudiante}</p>`,
    });
}

function boletinEstudiante(idEstudiante) {
    // Creo el contenido de notas a mostar para el idEstudiante recibido
    // Se recuperan los datos del servidor y se genera una tabla con las notas del boletín
    
    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-warning-subtle",
        colorTituloModal: "bg-warning",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Ver notas</b>",
        contenidoModal: `<p>Formularo para mostrar las notas del boletín del estudiante ${idEstudiante}</p>`,
    });
}

function crearGrado() {
    // Creo los botones de acciones a mostrar en el modal
    // Botón GUARDAR
    const btnGuardar = document.createElement("button");
    btnGuardar.type = "button";
    btnGuardar.className = "btn btn-outline-success";
    btnGuardar.textContent = "Guardar datos";
    
    // Botón CANCELAR
    const btnCancelar = document.createElement("button");
    btnCancelar.type = "reset";
    btnCancelar.className = "btn btn-outline-secondary";
    btnCancelar.textContent = "Borrar datos";

    // Creo el contenido de notas a mostar para el idEstudiante recibido
    // Se recuperan los datos del servidor y se genera una tabla con las notas del boletín
    
    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-success-subtle",
        colorTituloModal: "bg-success",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Crear grado</b>",
        contenidoModal: `<p>Formularo para crear un nuevo grado</p>`,
        botoneraModal: [btnGuardar, btnCancelar]
    });
}

function crearEstudiante() {
    // Creo los botones de acciones a mostrar en el modal
    // Botón GUARDAR
    const btnGuardar = document.createElement("button");
    btnGuardar.type = "button";
    btnGuardar.className = "btn btn-outline-success";
    btnGuardar.textContent = "Guardar datos";
    
    // Botón CANCELAR
    const btnCancelar = document.createElement("button");
    btnCancelar.type = "reset";
    btnCancelar.className = "btn btn-outline-secondary";
    btnCancelar.textContent = "Borrar datos";

    // Creo el contenido de notas a mostar para el idEstudiante recibido
    // Se recuperan los datos del servidor y se genera una tabla con las notas del boletín
    
    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-success-subtle",
        colorTituloModal: "bg-success",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Crear estudiante</b>",
        contenidoModal: `<p>Formularo para crear un nuevo estudiante</p>`,
        botoneraModal: [btnGuardar, btnCancelar]
    });
}

function generarModal(opciones) {
    // Creo el objeto Modal a partir de la estructura presente en el código HTML y lo personalizo para mostrar
    // el formulario de modificación de datos
    const modal = new bootstrap.Modal(document.getElementById("modal-datos"));

    // Color de fondo a todo el Modal
    document.querySelector("div.modal-content").className = "modal-content " + opciones.colorModal;
    document.querySelector("div.modal-header").className = "modal-header " + opciones.colorTituloModal;
    document.querySelector("div.modal-footer").className = "modal-footer " + opciones.colorBotoneraModal;

    // Agrego el título al Modal
    document.getElementById("titulo-modal").innerHTML = opciones.tituloModal

    // Agrego el contenido al Modal
    document.querySelector("div.modal-body").innerHTML = opciones.contenidoModal

    // Agrego los botones al Modal
    if (opciones.botoneraModal) {
        document.querySelector("div.modal-footer").innerHTML = "";
        for (const boton of opciones.botoneraModal) {
            document.querySelector("div.modal-footer").appendChild(boton);
        }
    } else {
        document.querySelector("div.modal-footer").innerHTML = `<button type="button" class="btn btn-secondary" onclick="cerrarModal()">Cerrar</button>`;
    }
    modal.show();
}

function cerrarModal() {
    const modal = bootstrap.Modal.getInstance(document.getElementById("modal-datos"));
    modal.hide();
}

function mostrarMensajeServidor(mensaje, tipoMensaje) {
    const panelError = document.querySelector("#panel-mensaje-error");
    const panelExito = document.querySelector("#panel-mensaje-ok > div.toast-body");

    if (tipoMensaje === "ok") {
        panelExito.textContent = mensaje;
        const toast = new bootstrap.Toast(panelExito);
        toast.show();
    } else {
        panelError.textContent = mensaje;
        const modal = new bootstrap.Modal(panelError);
        const segundos = 5;
        const tiempoEspera = 1000 * segundos; 
        modal.show();
        setInterval(() => {
            modal.hide();
        }, tiempoEspera);
    }

}

async function recuperarDatos(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            console.error(`Error al hacer fetch: ${response.status} ${response.statusText}`);
            return null;
        }
        const respuesta = await response.text();
        return JSON.parse(respuesta);
    } catch (e) {
        console.error("Error al obtener datos del servidor:", error);
        return null;
    }
}

function generarAcordeon(turnos, grados) {
    const accordion = document.getElementById("accordion-turnos-grados");
    const ciclos = Array.from(new Set(grados.map(g => g.cicloAsociado.leyendaUI)));

    const htmlItem = turnos.map((turno, i) => {
        const resp = 
            `<div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button text-bg-success" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapse-${turno.nombre}" aria-expanded="true" aria-controls="collapse-${turno.nombre}">
                        ${turno.leyendaUI}
                    </button>
                </h2>
                <div id="collapse-${turno.nombre}" class="accordion-collapse collapse ${i === 0 ? 'show' : ''}" data-bs-parent="#accordion-turnos-grados">
                    <div class="accordion-body" style="background-color: white;">
                        <div class="row">
                            ${ciclos.map(ciclo => {
                                const salida = `
                                    <div class="col px-0 me-4" style="border: 1px lightgreen solid;">
                                        <h6 class="text-center bg-success-subtle py-2">
                                            ${ciclo}
                                        </h6>
                                        <div class="grid text-center">
                                            ${grados
                                                .filter(grado => grado.cicloAsociado.leyendaUI === ciclo)
                                                .map((g, i) => {
                                                    const enlace = `/grados/${turno.nombre}/${g.nombre}`;
                                                    const claseCss = `g-col-4 btn btn-sm btn-outline-success mb-3 ${i === 0 ? '' : 'ms-3'}`;
                                                    const titulo = `Click para ver ${g.leyendaUI.toLowerCase()} de ${turno.leyendaUI.toLowerCase()}`;
                                                    const leyenda = g.leyendaUI;
                                                    const salida = `<a href="${enlace}" class="${claseCss}" title="${titulo}">${leyenda}</a>`;

                                                    return salida;
                                                }).join("")
                                            }
                                        </div>
                                    </div>`;
                                return salida;
                            }).join("")}
                        </div>
                    </div>
                </div>
            </div>`;
        return resp;
    }).join("");

    accordion.innerHTML = htmlItem;

}