// Defino servidor, puerto y endpoints de trabajo
const servidor = "localhost";
const puerto = "9090";
const urlBase = `http://${servidor}:${puerto}`;

// Defino rutas de endpoints
const epListarGrados = "/grados";
const epInfoGrados = "/grados/info-grados";
const epInfoTurnos = "/grados/info-turnos";
const epGradoPorTurnoYNombre = "/grados/";
const epGradoNuevo = "/grados/nuevo";
const epGradoEditado = "/grados/editar/";

const epEstudiantePorId = "/estudiantes/"
const epEstudianteNuevo = "/estudiantes/nuevo";
const epEstudianteEditado = "/estudiantes/editar/";
const epBorrarEdtudiante = "/estudiantes/borrar/";

// Defino URLs de trabajo
const urlGrados = urlBase + epListarGrados;
const urlInfoGrados = urlBase + epInfoGrados;
const urlInfoTurnos = urlBase + epInfoTurnos;
const urlGradoPorTurnoYNombre = urlBase + epGradoPorTurnoYNombre;
const urlGradoNuevo = urlBase + epGradoNuevo;
const urlGradoEditado = urlBase + epGradoEditado;
const urlEstudiantePorId = urlBase + epEstudiantePorId
const urlEstudianteNuevo = urlBase + epEstudianteNuevo;
const urlEstudianteEditado = urlBase + epEstudianteEditado;
const urlBorrarEstudiante = urlBase + epBorrarEdtudiante;

// Defino objeto que contiene los campos de los formularios que se utilizarán para
// crear/modificar datos
const camposDeFormularios = {
    estudiantes: [
        { nombreCampo: "id", tipo: "hidden", requerido: true },
        { nombreCampo: "nombre", leyenda: "Nombre: ", requerido: true },
        { nombreCampo: "apellido", leyenda: "Apellido: ", requerido: true },
        { nombreCampo: "edad", leyenda: "Edad: ", tipo: "number", requerido: true, min: 4, max: 14 },
        { nombreCampo: "dni", leyenda: "DNI: ", requerido: true },
        { nombreCampo: "direccion", leyenda: "Dirección: ", requerido: true },
        { nombreCampo: "nombreMadre", leyenda: "Nombre de la madre: " },
        { nombreCampo: "nombrePadre", leyenda: "Nombre del padre: " },
        { nombreCampo: "hnoEnEscuela", leyenda: "Tiene hermanos en la escuela: ", tipo: "checkbox" },
        { nombreCampo: "regular", leyenda: "Estudiante regular: ", tipo: "checkbox" },
        { nombreCampo: "grado", leyenda: "Grado al que se inscribe: ", tipo: "select", opciones: [] }
    ],
    grados: [
        { nombreCampo: "id_grado", tipo: "hidden" },
        { nombreCampo: "activo", leyenda: "Grado en funcionamiento", tipo: "checkbox" },
        { nombreCampo: "docente", leyenda: "Docente del grado: " },
        { nombreCampo: "nombre", leyenda: "Grado: " },
        { nombreCampo: "turno", leyenda: "Turno: " }
    ]
};

// Definimos un objeto cuya calve es el tipo de entrada posible de los campos del formulario
// y el valor correspondiente es una función que maneja la creación del campo.
const manejadorTipoEntradaFormulario = {
    'default': function (campo, valorActual) {
        // Por defecto se define el tipo de campo <input> y se le asignan atributos comunes mínimos
        return $("<input>", {
            type: campo.tipo || "text",
            name: campo.nombreCampo,
            class: "form-control"
        }).val(valorActual);
    },
    'textarea': function (campo, valorActual) {
        return $("<textarea>", {
            name: campo.nombreCampo,
            class: "form-control"
        }).val(valorActual);
    },
    'checkbox': function (campo, valorActual) {
        return $("<input>", {
            type: "checkbox",
            name: campo.nombreCampo,
            class: "form-check-input"
        })
        .prop("checked", !!valorActual)
        .prop("disabled", !!campo.deshabilitado);
    },
    'hidden': function (campo, valorActual) {
        return $("<input>", {
            type: "hidden",
            name: campo.nombreCampo,
            value: valorActual
        });
    },
    'select': function (campo, valorActual) {
        const select = $("<select>", {
            name: campo.nombreCampo,
            class: "form-control"
        });

        // Opción inicial por defecto
        select.append($("<option>", {
            value: "",
            text: "-- Seleccione un grado --",
            selected: !valorActual, 
            disabled: true
        }));

        // Agrego las opciones que vienen en el campo
        $.each(campo.opciones, function(i, opcion) {
            const seleccionada = (opcion.value === valorActual);
            select.append($("<option>", {
                value: opcion.nombreCampo,
                text: opcion.valor,
                selected: seleccionada
            }));
        });
        return select;
    },
    'radio': function (campo, valorActual) {
        // Devuelve el contenedor de todo el grupo de radios
        const $radioGroup = $("<div>", { class: "radio-group-container my-3" }); 
        const nombreCampo = campo.nombreCampo; 

        // Título del grupo
        $radioGroup.append($("<div>").text(campo.leyenda || nombreCampo).addClass("fw-bold mb-1"));

        $.each(campo.options, function(i, option) {
            const activa = (option.value === valorActual);
            
            const $radioDiv = $("<div>", { class: "form-check form-check-inline" });
            
            const $campoEntrada = $("<input>", {
                type: "radio",
                id: `${nombreCampo}_${option.value}`,
                name: nombreCampo, // CLAVE: Mismo nombre para el grupo
                value: option.value,
                class: "form-check-input",
                checked: activa
            });

            const $etiqueta = $("<label>", {
                for: `${nombreCampo}_${option.value}`,
                class: "form-check-label",
                text: option.text
            });

            $radioDiv.append($campoEntrada, $etiqueta);
            $radioGroup.append($radioDiv);
        });

        return $radioGroup; // Devuelve el contenedor de grupo
    }
};

// Al cargar la página recupero los grados para generar los vectores de turnos 
// y grados con los que se completan los menús desplegables
$(document).ready(async () => {
    await mostrarAcordeon();
});

// Funciones de modificación y eliminación de datos
async function crearEstudiante() {
    // Recupero los valores de los campos y cargo opciones de menú desplegable
    const estructuraCampos = camposDeFormularios.estudiantes.filter(reg => reg.nombreCampo !== "id");
    // Agrego los valores del menú desplegable
    estructuraCampos.find(reg => reg.nombreCampo === "grado").opciones = await menuDesplegableGrados();
    // Marco el campo "regular" como activo y desabilitado
    estructuraCampos.find(reg => reg.nombreCampo === "regular").activo = true;
    estructuraCampos.find(reg => reg.nombreCampo === "regular").deshabilitado = true;

    // Genero el modal que contiene al formulario de creación de datos
    const estructuraFormulario = {
        id: "#form-editar-datos",
        enlace: urlGradoNuevo,
        metodo: 'put',
        campos: estructuraCampos
    };

    const formCrearEstudiante = generarFormulario(estructuraFormulario);

    // Agrego los botones al formulario

    generarModal({
        colorModal: "bg-success-subtle",
        colorTituloModal: "text-bg-success",
        colorBotoneraModal: "bg-dark-subtle",
        tituloModal: "<b>Crear estudiante</b>",
        contenidoModal: formCrearEstudiante,
        // botoneraModal: [btnGuardar, btnCancelar]
    });
}

function crearGrado() {
    alert("Esta es la función de creación de grados");
}

async function editarEstudiante(idEstudiante) {
    // Creo los botones de acciones a mostrar en el modal
    // Botón GUARDAR
    const btnGuardar = $("<button>");
    btnGuardar.attr("type", "button");
    btnGuardar.addClass("btn btn-primary");
    btnGuardar.text("Guardar cambios");
    btnGuardar.on("click", () => guardarDatos($("#form-editar-datos")));

    // Botón CANCELAR
    const btnCancelar = $("<button>");
    btnCancelar.attr("type", "reset");
    btnCancelar.addClass("btn btn-secondary");
    btnCancelar.text("Borrar todo");
    btnCancelar.on("click", () => $("form")[0].reset());

    // Recupero los datos a editar del servidor y los vuelco al formulario
    const respServidor = await recuperarDatosAxios(urlEstudiantePorId + idEstudiante, "get");
    if (!respServidor.error) {
        // Vuelco los valores del servidor al objeto que usará el formulario
        const valoresCampos = camposDeFormularios.estudiantes;
        valoresCampos.find(reg => reg.id === "grado").opciones = await menuDesplegableGrados();
        console.log(valoresCampos);
        const contenidoFormulario = {
            enlace: urlEstudianteEditado + idEstudiante,
            metodo: 'patch',
            campos: valoresCampos
        };

        // Invoco a la función que genera el Modal y lo muestra en pantalla
        generarModal({
            colorModal: "bg-info-subtle",
            colorTituloModal: "text-bg-info",
            colorBotoneraModal: "bg-dark-subtle",
            tituloModal: "<b>Modificar estudiante</b>",
            contenidoModal: generarFormulario(contenidoFormulario),
            botoneraModal: [btnGuardar, btnCancelar]
        });
    } else {
        mostrarMensajeServidor(`Error ${respServidor.code} detectado: ${respServidor.details}`, "error");
    }
}

function eliminarEstudiante(idEstudiante, turno, grado) {
    // Creo los botones de acciones a mostrar en el modal
    // Botón BORRAR
    const btnBorrarEstudiante = $("<button>");
    btnBorrarEstudiante.attr("type", "button");
    btnBorrarEstudiante.addClass("btn btn-danger");
    btnBorrarEstudiante.text("Borrar datos");
    btnBorrarEstudiante.on("click", async () => {
        try {
            const response = await fetch(urlBorrarEstudiante + idEstudiante, {
                method: 'DELETE'
            });

            // Si recibo error del servidor muestro modal de aviso de error
            // Si no hay error muestro toast de operación exitosa
            if (!response.ok) {
                mostrarMensajeServidor("Error al intentar borrar el estudiante de nombreCampo: " + idEstudiante, "error");
            } else {
                cerrarModal();
                mostrarMensajeServidor("Se ha borrado con éxito el estudiante de nombreCampo: " + idEstudiante, "ok");
                mostrarGrado(turno, grado);
            }

        } catch (error) {
            console.error("ERROR detectado al intentar borrar el estudiante de nombreCampo: " + idEstudiante);
            console.error(error);
            mostrarMensajeServidor("ERROR INESPERADO: " + error.message, "error");
        }
    });

    // Botón CANCELAR
    const btnCancelar = $("<button>");
    btnCancelar.attr("type", "button");
    btnCancelar.addClass("btn btn-secondary");
    btnCancelar.on("click", cerrarModal);
    btnCancelar.text("Cancelar");

    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-danger-subtle",
        colorTituloModal: "bg-danger",
        colorBotoneraModal: "bg-dark-subtle",
        tituloModal: "<b>Borrar datos</b>",
        contenidoModal: `<p>Se va a eliminar el estudiante de ${idEstudiante}. ¿Desea continuar?</p>`,
        botoneraModal: [btnBorrarEstudiante, btnCancelar]
    });
}

function generarModal(opciones) {
    // Creo el objeto Modal a partir de la estructura presente en el código HTML y lo personalizo para mostrar
    // el formulario de modificación de datos
    const modal = new bootstrap.Modal($("#modal-datos"));

    // Color de fondo a todo el Modal
    $("div.modal-content").attr("class", `modal-content ${opciones.colorModal}`);
    $("div.modal-header").attr("class", `modal-header ${opciones.colorTituloModal}`);
    $("div.modal-footer").attr(`modal-footer ${opciones.colorBotoneraModal}`);

    // Agrego el título al Modal
    $("#titulo-modal").html(opciones.tituloModal);

    // Agrego el contenido al Modal
    $("div.modal-body").html(opciones.contenidoModal);

    // Agrego los botones al Modal
    if (opciones.botoneraModal) {
        $("div.modal-footer").html("");
        for (const boton of opciones.botoneraModal) {
            $("div.modal-footer").append(boton);
        }
    } else {
        $("div.modal-footer").html(`<button type="button" class="btn btn-secondary" onclick="cerrarModal()">Cerrar</button>`);
    }
    modal.show();
}

function cerrarModal() {
    const modal = bootstrap.Modal.getInstance($("#modal-datos"));
    modal.hide();
}

function mostrarMensajeServidor(mensaje, tipoMensaje) {
    const panelExito = $("#panel-mensaje-ok > div.toast-body");

    if (tipoMensaje === "ok") {
        panelExito.text(mensaje);
        const toast = new bootstrap.Toast(panelExito);
        $("#panel-mensaje-ok").show();
        toast.show();
        setTimeout(() => {
            $("#panel-mensaje-ok").hide();
        }, 4500);
    } else {
        alert(mensaje);
    }

}

/**
 * Procedimiento que genera el acordeón de turnos que contiene a sus respectivos grados, agrupados por ciclo.
 * 
 * @param {Turno} turnos objetos de tipo ENUM Turno que contiene en nombre y leyenda gráfica de cada turno.
 * @param {NombreGrado} grados objetos de tipo ENUM NombreGrado que contiene en nombre, ciclo y leyenda gráfica de cada grado.
 */
async function mostrarAcordeon() {
    const grados = await recuperarDatosFetch(urlInfoGrados, "GET");
    const turnos = await recuperarDatosFetch(urlInfoTurnos, "GET");

    // Oculto la tabla de datos y visualizo el acordeón
    const contenedorTabla = $("#tabla-datos-grados");
    contenedorTabla.hide();
    const contenedorAccordion = $("#accordion-turnos-grados");
    contenedorAccordion.show();

    // Defino título de panel de visualización
    const tituloPanelVisualizacion = $("#titulo-panel-visualizacion");

    // Si hay datos de turnos y grados genero y muestor el acordeón
    if (turnos && grados) {
        tituloPanelVisualizacion.text("Seleccione el grado que desea gestionar desplegando el turno correspondiente");


        // Genero el vector de ciclos para agrupar los grados en el curpo del acordeón
        const ciclos = Array.from(new Set(grados.map(g => g.cicloAsociado.leyendaUI)));

        const htmlItem = turnos.map(turno => {
            const resp =
                `<div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button text-bg-success collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapse-${turno.valor}" aria-expanded="false" aria-controls="collapse-${turno.valor}">
                            ${turno.leyendaUI}
                        </button>
                    </h2>
                    <div id="collapse-${turno.valor}" class="accordion-collapse collapse" data-bs-parent="#accordion-turnos-grados">
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
                                // const enlace = `/grados/${turno.valor}/${g.valor}`;
                                const tipo = "button";
                                const claseCss = `g-col-4 btn btn-sm btn-outline-success mb-3 ${i === 0 ? '' : 'ms-3'}`;
                                const titulo = `Click para ver ${g.leyendaUI.toLowerCase()} de ${turno.leyendaUI.toLowerCase()}`;
                                const leyenda = g.leyendaUI;
                                const evento = `mostrarGrado('${turno.valor}', '${g.valor}')`;
                                const salida = `<button type="${tipo}" class="${claseCss}" title="${titulo}" onclick="${evento}">${leyenda}</button>`;

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

        contenedorAccordion.html(htmlItem);
    } else {
        // Como no hay datos de turnos o grados muestro cartel de que no se encontraron datos
        tituloPanelVisualizacion.text("No se encontraron datos de turnos ni grados en la base de datos");
        contenedorAccordion.hide();
    }
}

async function mostrarGrado(turno, grado) {
    // Defino título de panel de visualización
    const tituloPanelVisualizacion = $("#titulo-panel-visualizacion");

    // Oculto el acordeón y visualizo la tabla de datos
    const contenedorTabla = $("#tabla-datos-grados");
    contenedorTabla.show();
    const contenedorAccordion = $("#accordion-turnos-grados");
    contenedorAccordion.hide();

    if (turno && grado) {
        // Recupero los datos del grado
        const url = `${urlGradoPorTurnoYNombre}${turno}/${grado}`;
        const datosGrado = await recuperarDatosFetch(url, "GET");

        // Creo elementos de tabla
        // Encabezado de tabla
        const tabla = $("#tabla");
        tabla.addClass("table table-striped table-hover table-bordered table-success");
        const encabezadoTabla = $("<thead>");
        const filaEncabezado = $("<tr>");
        filaEncabezado.addClass("text-center");
        const encabezados = [];
        encabezados.push("ID");
        encabezados.push("Nombre");
        encabezados.push("Apellido");
        encabezados.push("Edad");
        encabezados.push("DNI");
        encabezados.push("Acciones");
        encabezados.forEach(c => filaEncabezado.append(`<th class="text-bg-success">${c}</th>`));
        encabezadoTabla.append(filaEncabezado);

        // Cuerpo de tabla
        const cuerpoTabla = $("<tbody>");
        if (datosGrado.estudiantes.length > 0) {
            // Genero las filas de datos de estudiantes
            datosGrado.estudiantes.forEach(estudiante => {
                const filaCuerpo = $("<tr>");

                // Botones de acciones
                const btnEditar = `<button type="button" class="btn btn-info" onclick="editarEstudiante(${estudiante.id})" title="Click para editar estudiante">Editar</button>`;
                const btnBorrar = `<button type="button" class="btn btn-danger ms-3" onclick="eliminarEstudiante(${estudiante.id}, '${turno}', '${grado}')" title="Click para borrar estudiante">Borrar</button>`;
                const btnBoletin = `<a href="/boletines/estudiante/${estudiante.id}" class="btn btn-warning ms-3" title="Click para ver las notas">Boletín</a>`;
                const btnAsistencia = `<a href="/asistencias/estudiante/${estudiante.id}" class="btn btn-secondary ms-3" title="Click para ver las asistencias">Asistencia</a>`;
                const botones = `${btnEditar} ${btnBorrar} ${btnBoletin} ${btnAsistencia}`;

                // Datos de estudiante
                filaCuerpo.append(`<td class="text-center">${estudiante.id}</td>`);
                filaCuerpo.append(`<td>${estudiante.nombre}</td>`);
                filaCuerpo.append(`<td>${estudiante.apellido}</td>`);
                filaCuerpo.append(`<td class="text-center">${estudiante.edad}</td>`);
                filaCuerpo.append(`<td class="text-center">${Number(estudiante.dni).toLocaleString("es-AR")}</td>`);
                filaCuerpo.append(`<td class="text-center">${botones}</td>`);

                // Agrego fila a cuerpo de tabla
                cuerpoTabla.append(filaCuerpo);
            });
        } else {
            cuerpoTabla.append(`<tr><td colspan="${encabezados.length}" class="text-center">No hay datos de estudiantes para mostrar</td></tr>`);
        }


        tabla.html("");
        tabla.append(encabezadoTabla);
        tabla.append(cuerpoTabla);

        // Genero botón para volver al acordeón de turnos
        const btnVolver = $("#btnVolver");
        btnVolver.addClass("btn btn-outline-secondary col-2 mb-3");
        btnVolver.attr("onclick", "mostrarAcordeon()");
        btnVolver.text("Volver");

        // Genero el título del panel
        tituloPanelVisualizacion.text(`Datos correspondientes a ${datosGrado.nombre.leyendaUI} de ${datosGrado.turno.leyendaUI}`);
    } else {
        tituloPanelVisualizacion.text("No se encontraron datos para el grado indicado");
        contenedorTabla.hide();
    }
}

function generarFormulario(estructuraFormulario, valoresCampos = {}) {
    const formulario = $(estructuraFormulario.id);
    formulario.empty();

    generarCampos(formulario, estructuraFormulario.campos, valoresCampos);

    // Genero los botones y los agrego al formulario
    const btnEnviar = $("<button>", {
        type: "submit",
        class: "btn btn-primary",
        text: valoresCampos ? "Guardar cambios" : "Crear"
    });

    const btnLimpiar = $("<button>", {
        type: "reset",
        class: "btn btn-secondary",
        text: "Limpiar"
    });

    // Agrego botones al formulario
    formulario.append(btnEnviar, btnLimpiar);

    // Agrego comportamiento a btnEnviar
    formulario.on("submit", function (e) {
        e.preventDefault();
        console.log(`Se envía formulario. Tipo de envío: ${valoresCampos ? "edición" : "creación"}`);
        console.log("valoresCampos");
        console.log(valoresCampos);
    });

    // Devuelvo el formulario completo
    return formulario;
}

function generarCampos(formulario, campos, valores = null) {
    $.each(campos, function (i, campo) {
        console.log("campo");
        console.log(campo);

        const tipoCampo = campo.tipo || "text";
        const valorActual = valores[campo.nombreCampo] || "";

        // A partir del tipo de campo buscola función manejadora correspondiente y la "cargo" en una variable
        const crearCampo = manejadorTipoEntradaFormulario[tipoCampo] || manejadorTipoEntradaFormulario["default"];

        // Genero el campo según el tipo de entrada definido y manejado porla función
        const campoEntrada = crearCampo(campo, valorActual);

        // ----------------------------------------------------
        // Aplico comportamiento común a los campos de entrada.
        // ----------------------------------------------------
        // Marcar como requerido si corresponde
        if (campo.requerido) {
            campoEntrada.prop("required", true);
        }

        // Si el campo tiene definidos valores mínimos y/o máximos los agrego al <input> para los tipos válidos
        const tiposValidos = ['number', 'range', 'date', 'time', 'datetime-local'];
        if (tiposValidos.includes(campo.tipo)) {
            if (campo.min != null) {
                campoEntrada.attr("min", campo.min);
            }
            if (campo.max != null) {
                campoEntrada.attr("max", campo.max);
            }
        }

        // Si el campo de entrada es oculo o de tipo "radio", lo agrego al formulario y salteo el envoltorio con etiqueta
        if (campo.tipo === "radio" || campo.tipo === "hidden") {
            formulario.append(campoEntrada);
            return;
        }

        // Para el resto de los casos, agrego etiqueta y envuelvo con un <div>
        const etiqueta = $("<label>").text(campo.leyenda);
        const divContenedor = $("<div>", { class: "mb-3" });

        // Si el campo es requerido le agrego indicador visual a la etiqueta
        if (campo.requerido) {
            etiqueta.append($("<span>", { text: " *", style: "color: red;" }));
        }

        // Si el campo es de tiupo "checkbox" agrego la clase correspondiente al contenedor
        divContenedor.toggleClass("form-check", tipoCampo === "checkbox");

        // Ensamblo etiqueta y campo de entrada en el orden correspondiente según se trate de
        // tipo de entrada "checkbox" o no
        if (tipoCampo === "checkbox") {
            formulario.append(divContenedor.append(campoEntrada, etiqueta));
        } else {
            formulario.append(divContenedor.append(etiqueta, campoEntrada));
        }
    });
}

async function guardarDatos(datos) {
    // Construyo el objeto de datos a enviar al servidor
    const campos = [...$(datos).find("input"), ...$(datos).find("select")];
    const datosParaServidor = {};
    for (const c of campos) {
        if (c.id === "grado" && c.value !== "") {
            datosParaServidor[c.id] = { nombreCampo: c.value };
        } else if (c.id === "regular") {
            datosParaServidor[c.id] = true;
        } else {
            datosParaServidor[c.id] = c.type === 'checkbox' ? c.checked : c.value;
        }
    }
    // Envío los datos vía POST
    const respServidor = await recuperarDatosAxios(urlEstudianteNuevo, "post", datosParaServidor);

    // Manejo la salida
    let mensaje;
    let tipoMensaje;
    if (!respServidor.error) {
        cerrarModal();
        mensaje = `Se creó exitosamente estudiante ${respServidor.nombre} ${respServidor.apellido}`;
        tipoMensaje = "ok";
    } else {
        mensaje = `Error ${respServidor.status} detectado: ${respServidor.details}`;
        tipoMensaje = "error";
    }
    mostrarMensajeServidor(mensaje, tipoMensaje);
}

/**
 * Función genérica para realizar peticiones HTTP con la API Fetch.
 * @param {string} url - La URL del recurso.
 * @param {string} accion - El método HTTP (GET, POST, PATCH, DELETE).
 * @param {object} [datos=null] - Los datos a enviar en el cuerpo de la petición.
 * @returns {Promise<object|null>} Los datos de respuesta del servidor (JSON parseado) o null en caso de error.
 */
async function recuperarDatosFetch(url, accion, datos = null) {
    const httpMethod = accion.toUpperCase();
    const esMetodoValido = Array.from(['GET', 'POST', 'PATCH', 'PUT', 'DELETE']).includes(httpMethod);

    // Si se recibe un método HTTP inválido se lanza una excepción
    if (!esMetodoValido) throw `El método ${httpMethod} no es válido`;

    const config = {
        method: httpMethod,
        headers: {
            // Define envío y recepción de datos en formato JSON
            'Content-Type': 'application/json'
        }
    };

    // Si hay datos, se añaden al cuerpo y se serializan a JSON
    if (datos !== null && esMetodoValido) {
        config.body = JSON.stringify(datos);
    }

    try {
        const response = await fetch(url, config);

        // 1. Manejo de Errores HTTP (4xx y 5xx)
        if (!response.ok) {
            // Intentar leer el cuerpo del error si existe
            let errorDetails = null;
            try {
                // Asumimos que el cuerpo del error es JSON
                errorDetails = await response.json();
            } catch (e) {
                // Si falla el parseo, solo usamos el texto o un valor nulo
                errorDetails = await response.text() || null;
            }

            console.error(`Error ${response.status} en la petición ${httpMethod} a ${url}`);
            console.error("Detalles:", errorDetails);

            // Retornamos el objeto de error para manejarlo
            return {
                error: true,
                status: response.status,
                details: errorDetails
            };
        }

        // 2. Manejo de Respuesta Exitosa (2xx)

        // Si la respuesta es 204 No Content (típico de DELETE), no intentamos parsear
        if (response.status === 204) {
            return {}; // Devolver un objeto vacío para indicar éxito sin contenido
        }

        // Parsear la respuesta como JSON
        const responseData = await response.json();
        return responseData;

    } catch (error) {
        console.error("Error de red o al procesar la respuesta:", error);
        return null;
    }
}

/**
 * Función genérica para realizar peticiones HTTP con Axios.
 * @param {string} url - La URL del recurso.
 * @param {string} method - El método HTTP (GET, POST, PATCH, DELETE).
 * @param {object} [data=null] - Los datos a enviar en el cuerpo de la petición (para POST/PATCH).
 * @returns {Promise<object|null>} Los datos de respuesta del servidor (JSON parseado) o null en caso de error.
 */
async function recuperarDatosAxios(url, method, data = null) {
    // Convertir el método a minúsculas, que es como Axios lo espera
    const httpMethod = method.toLowerCase();
    const esMetodoValido = Array.from(['get', 'post', 'patch', 'put', 'delete']).includes(httpMethod);

    try {
        // Si se recibe un método HTTP inválido se lanza una excepción
        if (!esMetodoValido) throw `El método ${httpMethod} no es válido`;

        // Axios ya serializa automáticamente los objetos JS a JSON
        const config = {
            method: httpMethod,
            url: url,
            // 'data' solo se incluye si es POST, PATCH, o DELETE (opcionalmente)
            data: data
        };
        // Ejecutar la petición con la configuración
        const response = await axios(config);

        // Axios devuelve los datos parseados en response.data si el estado es 2xx
        return response.data;

    } catch (error) {
        // Manejo de errores detallado (incluyendo códigos HTTP 4xx/5xx)
        if (error.response) {
            // Error de respuesta del servidor (código fuera de 2xx)
            console.error(`Error ${error.response.status} en la petición ${method} a ${url}`);
            console.error("Detalles:", error.response.data);

            // Retornamos el objeto de error para manejarlo en el código llamante si es necesario
            return {
                error: true,
                status: error.response.status,
                details: error.response.data
            };
        } else if (error.request) {
            // Error de red (no se recibió respuesta)
            console.error(`Sin respuesta para la petición ${method} a ${url}`);
        } else {
            // Error de configuración de Axios
            console.error(`Error de configuración de Axios: ${error.message}`);
        }
        return null; // Retorna null si la petición falló o si hubo un error de red/configuración.
    }
}

// Esta función recupera los datos de los grados y genera el par ID/VALOR
// para generar el menú desplegable correspondiente para el formulario
// de creación/edición de estudiantes.
async function menuDesplegableGrados() {
    const datosGrados = await recuperarDatosAxios(urlGrados, "GET");
    const colDatosGrados = datosGrados.map(g => ({
        nombreCampo: g.id,
        valor: g.nombre.leyendaUI + " - " + g.turno.leyendaUI
    }));
    return colDatosGrados;
}