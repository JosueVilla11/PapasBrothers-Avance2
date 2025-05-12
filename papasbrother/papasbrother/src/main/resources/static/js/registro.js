function validarPassword() {
    var password = document.getElementById("contraseña");
    var mensaje = document.getElementById("passwordHelp");
    if (password.value.length < 8) {
        mensaje.style.display = "block";
    } else {
        mensaje.style.display = "none";
    }
}

function validarFormulario() {
    var nombre = document.getElementById("nombreCompleto").value;
    if (!/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/.test(nombre)) {
        alert("El nombre solo puede contener letras y espacios.");
        return false;
    }
    return true;
}