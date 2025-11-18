const urlGrados = "http://localhost:8080/grados";
recuperarDatos(urlGrados)
    .then(grados => {
        const turnosGrados = new Set(grados.map(g => g.turno));
        const nombresGrados = new Set(grados.map(g => g.nombreGrado));

        // Cargo los grados en el combo de grados
        nombresGrados.forEach(nombre => {
            const opcion = document.createElement("option");
            opcion.value = nombre;
            opcion.text = nombre;
            document.getElementById("sel-grado").appendChild(opcion);
        });

        // Cargo los turno en el combno de turnos
        turnosGrados.forEach(turno => {
            const opcion = document.createElement("option");
            opcion.value = turno;
            opcion.text = turno;
            document.getElementById("sel-turno").appendChild(opcion);
        });
    });

async function recuperarDatos(urlGrados) {
    const respuesta = await fetch(urlGrados);
    const datos = await respuesta.json();
    return datos;
}