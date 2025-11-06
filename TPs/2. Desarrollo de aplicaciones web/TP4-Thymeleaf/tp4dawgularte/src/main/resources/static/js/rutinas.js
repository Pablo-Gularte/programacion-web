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