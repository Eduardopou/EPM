function mostrarDesdeDataset(boton) {
    const numero = boton.dataset.numero;
    const descripcion = boton.dataset.descripcion;

    document.getElementById("animalInfo").innerText =
        "Información sobre regla número " + numero + "\n\n" + descripcion;
}

function mostrarInfo(nombreComun, info) {
    document.getElementById("animalInfo").innerText =
        "Información sobre regla número " + nombreComun + "\n" + info;
}

function filtrarTabla() {
    let input = document.getElementById("search").value.toLowerCase();
    let table = document.getElementById("animalTable");
    let tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) { // Omitimos el encabezado
        let td = tr[i].getElementsByTagName("td")[0]; // Primera columna (ID)
        if (td) {
            let texto = td.textContent || td.innerText;
            tr[i].style.display = texto.toLowerCase().includes(input) ? "" : "none";
        }
    }
}
