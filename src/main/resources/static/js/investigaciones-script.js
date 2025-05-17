function mostrarDesdeDataset(boton) {
    const numero = boton.dataset.numero;
    const descripcion = boton.dataset.descripcion;

    document.getElementById("nombreInvestigador").innerText =
        "Información sobre investigación número " + numero + "\n\n" + descripcion;
}

function filtrarTabla() {
    let input = document.getElementById("search").value.toLowerCase();
    let table = document.getElementById("investigacionesTable");
    let tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) {
        let td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            let texto = td.textContent || td.innerText;
            tr[i].style.display = texto.toLowerCase().includes(input) ? "" : "none";
        }
    }
}
