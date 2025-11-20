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
        });
});