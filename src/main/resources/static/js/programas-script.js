function mostrarDesdeDataset(boton) {
    const nombre = boton.dataset.nombre;
    const descripcion = boton.dataset.descripcion;

    const programaInfo = document.getElementById("programaInfo");
    programaInfo.innerText = `Nombre del programa: ${nombre}\n\nDescripción: ${descripcion}`;
}

function filtrarTabla() {
    const input = document.getElementById("search").value.toLowerCase();
    const table = document.getElementById("programasTable");
    const tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) {
        const td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            const texto = td.textContent || td.innerText;
            tr[i].style.display = texto.toLowerCase().includes(input) ? "" : "none";
        }
    }
}
