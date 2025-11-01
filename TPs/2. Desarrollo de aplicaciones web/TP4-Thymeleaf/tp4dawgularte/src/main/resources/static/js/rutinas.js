/**
 * Función que modifica el botón que expande y colapsa el panel que contiene el formulario de creación
 * de autos. Cuando no está desplegado el panel muestra leyenda para crear datos y al desplegar cambia 
 * el texto para indicar cierre de panel
 */
function expandirPanel() {
    const boton = document.getElementById("btn-crear-auto");

    if(boton.getAttribute("class") === "btn btn-success") {
        boton.setAttribute("class", "btn btn-secondary");
        boton.innerHTML = `<h3><i class="bi bi-dash-square"></i> Cerrar panel</h3>`;
    } else {
        boton.setAttribute("class", "btn btn-success");
        boton.innerHTML = `<h3><i class="bi bi-plus-circle"></i> Crear auto</h3>`;
    }
}
