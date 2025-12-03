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
        {id: "id", leyenda: "Identificador:", config: {tipo: "number", requerido: true, oculto: true}}, 
        {id: "nombre", leyenda: "Nombre:", config: {tipo: "text", requerido: true}}, 
        {id: "apellido", leyenda: "Apellido:", config: {tipo: "text", requerido: true}}, 
        {id: "edad", leyenda: "Edad:", config: {tipo: "number", requerido: true, min: 4, max: 14}}, 
        {id: "dni", leyenda: "DNI:",config: {tipo: "text", requerido: true}}, 
        {id: "direccion", leyenda: "Dirección:", config: {tipo: "text", requerido: true}}, 
        {id: "nombreMadre", leyenda: "Nombre de la madre:", config: {tipo: "text"}}, 
        {id: "nombrePadre", leyenda: "Nombre del padre:", config: {tipo: "text"}}, 
        {id: "hnoEnEscuela", leyenda: "Tiene hermanos en la escuela:", config: {tipo: "checkbox"}}, 
        {id: "regular", leyenda: "Estudiante regular:", config: {tipo: "checkbox"}},
        {id: "grado", leyenda: "Grado al que se inscribe: ", config: {tipo: "select", opciones: []}}
    ],
    grados: []
};

// Al cargar la página recupero los grados para generar los vectores de turnos 
// y grados con los que se completan los menús desplegables
$(document).ready(async () => {
    await mostrarAcordeon();
});

// Funciones de modificación y eliminación de datos
async function crearEstudiante() {
    // Creo los botones de acciones a mostrar en el modal
    // Botón GUARDAR
    const btnGuardar = $("<button>");
    btnGuardar.attr("type", "button");
    btnGuardar.addClass("btn btn-success");
    btnGuardar.text("Guardar cambios");
    btnGuardar.on("click", () => guardarDatos($("#form-editar-datos")));
    
    // Botón CANCELAR
    const btnCancelar = $("<button>");
    btnCancelar.attr("type","reset");
    btnCancelar.addClass("btn btn-secondary");
    btnCancelar.text("Borrar todo");
    btnCancelar.on("click", () => $("form")[0].reset());

    // Recupero los valores de los campos y cargo opciones de menú desplegable
    const valoresCampos = camposDeFormularios.estudiantes;
    valoresCampos.find(reg => reg.id === "grado").opciones = await menuDesplegableGrados();

    // Genero el modal que contiene al formulario de creación de datos
    const contFormulario = {
        enlace: urlGradoNuevo,
        metodo: 'put',
        campos: valoresCampos
    };

    generarModal({
        colorModal: "bg-success-subtle",
        colorTituloModal: "text-bg-success",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Crear estudiante</b>",
        contenidoModal: generarFormulario(contFormulario),
        botoneraModal: [btnGuardar, btnCancelar]
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
    btnCancelar.attr("type","reset");
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
            colorBotoneraModal:"bg-dark-subtle",
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
                mostrarMensajeServidor("Error al intentar borrar el estudiante de ID: " + idEstudiante, "error");
            } else {
                cerrarModal();
                mostrarMensajeServidor("Se ha borrado con éxito el estudiante de ID: " + idEstudiante, "ok");
                mostrarGrado(turno, grado);
            }

        } catch (error) {
            console.error("ERROR detectado al intentar borrar el estudiante de ID: " + idEstudiante);
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
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Borrar datos</b>",
        contenidoModal: `<p>Se va a eliminar el estudiante de ${idEstudiante}. ¿Desea continuar?</p>`,
        botoneraModal: [btnBorrarEstudiante, btnCancelar]
    });
}

function crearGradoEstudiante(tipoDato, id = null) {
    // Creo los botones de acciones a mostrar en el modal
    // Botón GUARDAR
    const btnGuardar = $("<button>");
    btnGuardar.attr("type", "button");
    btnGuardar.addClass("btn btn-outline-success");
    btnGuardar.text("Guardar datos");
    
    // Botón CANCELAR
    const btnCancelar = $("<button>");
    btnCancelar.attr("type", "reset");
    btnCancelar.addClass("btn btn-outline-secondary");
    btnCancelar.text("Borrar datos");

    // Defino los estilos y leyendas
    const fondo = "bg-success-subtle";
    const titulo = "text-bg-success";
    const botonera = "bg-dark-subtle";
    const tituloLeyenda = `<b>${id === null ? 'Crear' : 'Modificar'} ${tipoDato}</b>`;
    const botones = [btnGuardar, btnCancelar];

    // Creo el contenido del modal en función de tipoDato (grado o estudiante).
    // Si id = null el formulario es de creacion, si no es de edición.
    let contenidoFormulario = {};
    if (id) {
        contenidoFormulario = {
        enlace: urlGradoEditado,
        metodo: 'patch',
        campos: ''
    };
    } else {
        contenidoFormulario = {
        enlace: urlGradoNuevo,
        metodo: 'post',
        campos: ''
    };

    }
    console.log("invoco generarFormulario() desde crearGradoEstudiante()");
    const contenidoGrado = generarFormulario(contenidoFormulario);
    const contenidoEstudiante = `<p>Formularo para crear un nuevo ${tipoDato}</p>`
    // Se recuperan los datos del servidor y se genera una tabla con las notas del boletín
    
    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: fondo,
        colorTituloModal: titulo,
        colorBotoneraModal:botonera,
        tituloModal: tituloLeyenda,
        contenidoModal: tipoDato === 'grado' ? contenidoGrado : contenidoEstudiante,
        botoneraModal: botones
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

function generarFormulario(contenido) {
    console.log("Se invoca generarFormulario()");
    console.log(contenido);
    const formulario = $("#form-editar-datos");
    const campos = [];

    for(const campo of contenido.campos) {
        // Creo el div contenedor de campos
        const divContenedor = $("<div>");
        divContenedor.addClass("mb-3");

        // Creo etiqueta de campo
        const etiqueta = $("<label>");
        etiqueta.attr("for", campo.id);
        etiqueta.text(campo.leyenda);


        if (campo.id === 'hnoEnEscuela') {
            campos.push(
                `<div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="${campo.id}">
                    <label class="form-check-label" for="${campo.id}">${campo.leyenda}</label>
                </div>`
            );
        } else if (campo.id === "regular") {
            campos.push(
                `<div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="${campo.id}" checked disabled>
                    <label class="form-check-label" for="${campo.id}">${campo.leyenda}</label>
                </div>`
            );
        } else if (campo.id === "grado") {
            const opciones = campo.opciones.map( g => `<option value="${g.id}">${g.valor}</option>`).join("");  
            campos.push(  
                `<div class="mb-3">  
                    <label for="${campo.id}" class="form-label">${campo.leyenda}</label>  
                    <select class="form-select" id="${campo.id}" required>  
                        <option value="" selected disabled>Seleccione el grado</option>  
                        ${opciones}  
                    </select>  
                </div>`  
            );
        } else {
            const tipoCampo = (campo.id === 'regular' || campo.id === 'id') ? 'hidden' : campo.id === 'edad' ? 'number' : 'text';
            const requerido = !(campo.id === 'nombreMadre' || campo.id === 'nombrePadre') ? 'required' : '';

            campos.push(
                `<div class="mb-3">
                        <label for="${campo.id}" class="form-label">${campo.leyenda}</label>
                        <input type="${tipoCampo}" class="form-control" id="${campo.id}" ${requerido}>
                    </div>`
            );
        }
    }
    formulario.html("");
    formulario.append(campos);

    return formulario;
}

async function guardarDatos(datos) {
    // Construyo el objeto de datos a enviar al servidor
    const campos = [...$(datos).find("input"), ...$(datos).find("select")];  
    const datosParaServidor = {};  
    for(const c of campos) {  
        if (c.id === "grado" && c.value !== "") {  
            datosParaServidor[c.id] = { id: c.value };  
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
    const colDatosGrados = datosGrados.map( g => ({
            id: g.id,
            valor: g.nombre.leyendaUI + " - " + g.turno.leyendaUI
        }));
    return colDatosGrados;
}