// Al cargar la página recupero los grados para generar los vectores de turnos y grados con los que se completan los menús desplegables
document.addEventListener("DOMContentLoaded", () => {
    const servidor = "localhost";
    const puerto = "9090";
    const epGrados = "/grados";
    const urlGrados = `http://${servidor}:${puerto}${epGrados}`;

    fetch(urlGrados)
        .then(response => response.json())
        .then(grados => {
            const selectGrados = document.querySelector("#botonera-grados select");
            const selectTurnos = document.querySelector("#botonera-turnos select");
            const LEYENDA_TURNOS = {
                "MAÑANA": "Turno Mañana",
                "TARDE": "Turno Tarde",
                "JORNADA_COMPLETA": "Jornada Completa"
            };
            const LEYENDA_GRADOS = {
                "PRIMERO": "Primer Grado",
                "SEGUNDO": "Segundo Grado",
                "TERCERO": "Tercer Grado",
                "CUARTO": "Cuarto Grado",
                "QUINTO": "Quinto Grado",
                "SEXTO": "Sexto Grado",
                "SEPTIMO": "Séptimo Grado"
            };

            const turnos = new Set(grados.map(g => g.turno));
            // Cargo los turnos en el menú desplegable correspondiente
            turnos.forEach(t => {
                const option = document.createElement("option");
                option.value = t;
                option.textContent = LEYENDA_TURNOS[t];
                selectTurnos.appendChild(option);
            });
            // Agrego un escuchador de eventos al menú despelgable de turnos para que cuando cambie el turno seleccionado
            // se carguen los grados correspondientes en el menú desplegable de grados.
            selectTurnos.addEventListener("change", () => {
                // Limpio tabla de datos y menú de grados
                document.querySelector("#tabla-datos-grados").innerHTML = "";
                selectGrados.innerHTML = '<option selected hidden disabled>Seleccione un grado</option>';
                const turnoSeleccionado = selectTurnos.value;
                // Filtro los grados correspondientes al turno seleccionado
                const gradosFiltrados = grados.filter(g => g.turno === turnoSeleccionado);
                // Cargo los grados en el menú desplegable correspondiente
                gradosFiltrados.forEach(g => {
                    const option = document.createElement("option");
                    option.value = g.id;
                    option.textContent = LEYENDA_GRADOS[g.nombre];
                    selectGrados.appendChild(option);
                });
                // Habilito el menú desplegable de grados
                if (selectGrados.style.display === "none") selectGrados.style.display = "block";
            });

            // Agrego escuchador de eventos al menu desplegable de grados para que al seleccionar el grado se cargue
            // la tabla de estudiantes filtrando la colección "grados" recuperada del servidor, por grado y turno seleccionados
            selectGrados.addEventListener("change", () => {
                const gradoSeleccionadoId = parseInt(selectGrados.value);
                const turnoSeleccionado = selectTurnos.value;
                const gradoSeleccionado = grados.find(g => g.id === gradoSeleccionadoId && g.turno === turnoSeleccionado);
                const contenedorTablaGrados = document.querySelector("#tabla-datos-grados");
                // Creo el encabezdo de la tabla y una división para separar visualmente
                const tituloDatosGrados = document.createElement("h2");
                tituloDatosGrados.className = "pt-4";
                const division = document.createElement("hr");

                // Limpio la tabla y encabezado de datos del grado
                contenedorTablaGrados.innerHTML = "";
                // Si hay grado seleccionado con estudfiantes muestro la tabla, sino muestro mensaje de que no hay estudiantes
                if (gradoSeleccionado && gradoSeleccionado.estudiantes.length > 0) {
                    // Creo la tabla con los estudiantes del grado seleccionado
                    const tabla = document.createElement("table");
                    tabla.classList.add("table", "table-hover", "table-bordered", "table-striped");
                    // Creo el encabezado de la tabla
                    const thead = document.createElement("thead");
                    const encabezado = document.createElement("tr");
                    ["ID", "Nombre", "Apellido", "Edad", "DNI", "Acciones"].forEach(texto => {
                        const th = document.createElement("th");
                        th.textContent = texto;
                        th.className = "text-center text-bg-success";
                        encabezado.appendChild(th);
                    });
                    thead.appendChild(encabezado);
                    tabla.appendChild(thead);
                    // Creo el cuerpo de la tabla
                    const tbody = document.createElement("tbody");
                    tbody.className = "table-success";
                    gradoSeleccionado.estudiantes.forEach(estudiante => {
                        const fila = document.createElement("tr");
                        
                        // Celda ID, Nombre, Apellido, Edad, DNI
                        const celdaId = document.createElement("td");
                        celdaId.textContent = estudiante.id;
                        celdaId.className = "text-center";
                        fila.appendChild(celdaId);

                        const celdaNombre = document.createElement("td");
                        celdaNombre.textContent = estudiante.nombre;
                        fila.appendChild(celdaNombre);
                        
                        const celdaApellido = document.createElement("td");
                        celdaApellido.textContent = estudiante.apellido;
                        fila.appendChild(celdaApellido);
                        
                        const celdaEdad = document.createElement("td");
                        celdaEdad.textContent = estudiante.edad;
                        celdaEdad.className = "text-center";
                        fila.appendChild(celdaEdad);

                        const celdaDni = document.createElement("td");
                        celdaDni.textContent = Number(estudiante.dni).toLocaleString("es-AR");
                        celdaDni.className = "text-center";
                        fila.appendChild(celdaDni);
                        
                        // Celda de acciones con botones Editar y Eliminar
                        const celdaAcciones = document.createElement("td");
                        celdaAcciones.className = "text-center";
                        const botonEditar = document.createElement("button");
                        botonEditar.textContent = "Editar";
                        botonEditar.setAttribute("title", "Click para editar estudiante");
                        botonEditar.setAttribute('onclick', `editarEstudiante(${estudiante.id})`);
                        botonEditar.className = "btn btn-primary btn-sm me-2 mb-2";
                        celdaAcciones.appendChild(botonEditar);
                        
                        const botonEliminar = document.createElement("button");
                        botonEliminar.textContent = "Borrar";
                        botonEliminar.setAttribute("title", "Click para borrar estudiante");
                        botonEliminar.setAttribute('onclick', `eliminarEstudiante(${estudiante.id})`);
                        botonEliminar.className = "btn btn-danger btn-sm me-2 mb-2";
                        celdaAcciones.appendChild(botonEliminar);
                        fila.appendChild(celdaAcciones);

                        const botonAsistencias = document.createElement("button");
                        botonAsistencias.textContent = "Asistencias";
                        botonAsistencias.setAttribute("title", "Click para ver las asistencias del estudiante");
                        botonAsistencias.setAttribute('onclick', `asistenciasEstudiante(${estudiante.id})`);
                        botonAsistencias.className = "btn btn-info btn-sm me-2 mb-2";
                        celdaAcciones.appendChild(botonAsistencias);
                        fila.appendChild(celdaAcciones);

                        const botonBoletin = document.createElement("button");
                        botonBoletin.textContent = "Boletín";
                        botonBoletin.setAttribute("title", "Click para ver las notas del estudiante");
                        botonBoletin.setAttribute('onclick', `boletinEstudiante(${estudiante.id})`);
                        botonBoletin.className = "btn btn-warning btn-sm mb-2";
                        celdaAcciones.appendChild(botonBoletin);
                        fila.appendChild(celdaAcciones);
                        
                        // Agrego la fila a la tabla
                        tbody.appendChild(fila);
                    });
                    tabla.appendChild(tbody);
                    tituloDatosGrados.textContent = `Estudiantes de ${LEYENDA_GRADOS[gradoSeleccionado.nombre]} (${LEYENDA_TURNOS[gradoSeleccionado.turno]})`;
                    contenedorTablaGrados.appendChild(tituloDatosGrados);
                    contenedorTablaGrados.appendChild(division);
                    contenedorTablaGrados.appendChild(tabla);
                } else {
                    contenedorTablaGrados.innerHTML = `<div class="alert alert-info border border-2 border-primary mt-4">Aún no hay estudiantes registrados en este grado.</div>`;
                }
            });
        });
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
    const btnGuardar = document.createElement("button");
    btnGuardar.type = "button";
    btnGuardar.className = "btn btn-danger";
    btnGuardar.textContent = "Borrar datos";
    
    // Botón CANCELAR
    const btnCancelar = document.createElement("button");
    btnCancelar.type = "reset";
    btnCancelar.className = "btn btn-secondary";
    btnCancelar.textContent = "Cancelar";

    // Invoco a la función que genera el Modal y lo muestra en pantalla
    generarModal({
        colorModal: "bg-danger-subtle",
        colorTituloModal: "bg-danger",
        colorBotoneraModal:"bg-dark-subtle",
        tituloModal: "<b>Borrar datos</b>",
        contenidoModal: `<p>Formularo para borrar los datos del estudiante ${idEstudiante}</p>`,
        botoneraModal: [btnGuardar, btnCancelar]
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
        document.querySelector("div.modal-footer").innerHTML = `<button type="button" class="btn btn-secondary" onclick="document.querySelector('button.btn-close').click();">Cerrar</button>`;
    }
    modal.show();
}