// Al cargar la página recupero los grados para generar los vectores de turnos y grados con los que se completan los menús desplegables
document.addEventListener("DOMContentLoaded", () => {
    const servidor = "localhost";
    const puerto = "8080";
    const epGrados = "/grados";
    const urlGrados = `http://${servidor}:${puerto}${epGrados}`;

    fetch(urlGrados)
        .then(response => response.json())
        .then(grados => {
            const selectGrados = document.querySelector("#botonera-grados select");
            const selectTurnos = document.querySelector("#botonera-turnos select");

            const turnos = new Set(grados.map(g => g.turno));
            // Cargo los turnos en el menú desplegable correspondiente
            turnos.forEach(t => {
                const option = document.createElement("option");
                option.value = t;
                option.textContent = t;
                selectTurnos.appendChild(option);
            });
            // Agrego un escuchador de eventos al menú despelgable de turnos para que cuando cambie el turno seleccionado
            // se carguen los grados correspondientes en el menú desplegable de grados.
            selectTurnos.addEventListener("change", () => {
                // Limpio el menú de grados
                selectGrados.innerHTML = '<option selected hidden disabled>Seleccione un grado</option>';
                const turnoSeleccionado = selectTurnos.value;
                // Filtro los grados correspondientes al turno seleccionado
                const gradosFiltrados = grados.filter(g => g.turno === turnoSeleccionado);
                // Cargo los grados en el menú desplegable correspondiente
                gradosFiltrados.forEach(g => {
                    const option = document.createElement("option");
                    option.value = g.id;
                    option.textContent = g.nombreGrado;
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
                    tabla.classList.add("table", "table-striped", "table-bordered");
                    // Creo el encabezado de la tabla
                    const thead = document.createElement("thead");
                    const encabezado = document.createElement("tr");
                    ["ID", "Nombre", "Apellido", "Edad", "Acciones"].forEach(texto => {
                        const th = document.createElement("th");
                        th.textContent = texto;
                        encabezado.appendChild(th);
                    });
                    thead.appendChild(encabezado);
                    tabla.appendChild(thead);
                    // Creo el cuerpo de la tabla
                    const tbody = document.createElement("tbody");
                    gradoSeleccionado.estudiantes.forEach(estudiante => {
                        const fila = document.createElement("tr");
                        
                        // Celda ID, Nombre, Apellido, Edad
                        const celdaId = document.createElement("td");
                        celdaId.textContent = estudiante.id;
                        fila.appendChild(celdaId);

                        const celdaNombre = document.createElement("td");
                        celdaNombre.textContent = estudiante.nombre;
                        fila.appendChild(celdaNombre);
                        
                        const celdaApellido = document.createElement("td");
                        celdaApellido.textContent = estudiante.apellido;
                        fila.appendChild(celdaApellido);
                        
                        const celdaEdad = document.createElement("td");
                        celdaEdad.textContent = estudiante.edad;
                        fila.appendChild(celdaEdad);
                        
                        // Celda de acciones con botones Editar y Eliminar
                        const celdaAcciones = document.createElement("td");
                        const botonEditar = document.createElement("button");
                        botonEditar.textContent = "Editar";
                        botonEditar.setAttribute('onclick', `editarEstudiante(${estudiante.id})`);
                        botonEditar.className = "btn btn-primary btn-sm me-2";
                        celdaAcciones.appendChild(botonEditar);
                        
                        const botonEliminar = document.createElement("button");
                        botonEliminar.textContent = "Eliminar";
                        botonEliminar.setAttribute('onclick', `eliminarEstudiante(${estudiante.id})`);
                        botonEliminar.className = "btn btn-danger btn-sm";
                        celdaAcciones.appendChild(botonEliminar);
                        fila.appendChild(celdaAcciones);
                        
                        // Agrego la fila a la tabla
                        tbody.appendChild(fila);
                    });
                    tabla.appendChild(tbody);
                    tituloDatosGrados.textContent = `Estudiantes de ${gradoSeleccionado.nombreGrado} (${gradoSeleccionado.turno})`;
                    contenedorTablaGrados.appendChild(tituloDatosGrados);
                    contenedorTablaGrados.appendChild(division);
                    contenedorTablaGrados.appendChild(tabla);
                } else {
                    contenedorTablaGrados.textContent = "No hay estudiantes registrados en este grado.";
                }
            });
        });
});