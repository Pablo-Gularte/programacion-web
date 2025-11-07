const modalError = document.getElementById("modal-mensaje-error");
const toastOk = document.getElementById("toast-mensaje-ok");

// Si se insertó el modal de error, activarlo.
if(modalError) {
    const modal = new bootstrap.Modal(modalError);
    const segundos = 5;
    const tiempoEspera = 1000 * segundos; 
    modal.show();
    setInterval(() => {
        modal.hide();
    }, tiempoEspera);
}

// Si se insertó el toast de mensaje ok, activarlo
if(toastOk) {
    const toast = new bootstrap.Toast(toastOk);
    toast.show();
}

function borrarRegistro(id) {
    if(confirm(`¿Desea eliminar el auto de ID ${id}?`)) {
        // Creo un formulario temporal para enviar al controlafor una petición POST
        // con el id del registro a borrar
        const frmBorrar = document.createElement("form");
        frmBorrar.action = "/autos/borrar/" + id;
        frmBorrar.method = "post";
        document.body.appendChild(frmBorrar);
        frmBorrar.submit();
        document.body.removeChild(frmBorrar);
    } 
}

function resetearFormulario() {
    document.getElementById("marca").value = "";
    document.getElementById("precio").value = "";
}