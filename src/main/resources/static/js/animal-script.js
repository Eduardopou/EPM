

function filtrarTabla() {
    let input = document.getElementById("search").value.toLowerCase();
    let table = document.getElementById("animalTable");
    let tr = table.getElementsByTagName("tr");

    for (let i = 1; i < tr.length; i++) {
        let td = tr[i].getElementsByTagName("td")[2];
        if (td) {
            let texto = td.textContent || td.innerText;
            tr[i].style.display = texto.toLowerCase().includes(input) ? "" : "none";
        }
    }
}

function generarInfoDesdeGemini(button) {
    const nombreAnimal = button.getAttribute("data-nombre");
    const spinner = document.getElementById("loading");
    const info = document.getElementById("animalInfo");
    const image = document.getElementById("animalImage");

    spinner.style.display = "flex";
    info.textContent = "";
    image.style.display = "none";

    fetch("/api/gemini/generar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            prompt: `Dame una descripcion de maximo 300 palabras del animal llamado ${nombreAnimal} desde un enfoque biologico pero para un publico mas general.` +
                'Se conciso y no respondas nada mas que la informacion. Separa los subtitulos y puntos clave con salto de linea.'
        })
    })
        .then(response => {
            if (!response.ok) throw new Error("Error al llamar a Gemini");
            return response.text();
        })
        .then(data => {
            const html = marked.parse(data); // Convierte Markdown a HTML
            info.innerHTML = html;
        })
        .catch(error => {
            info.textContent = "Error al generar información.";
            console.error(error);
        })
        .finally(() => {
            spinner.style.display = "none";
        });

    // Obtener la URL de imagen directamente desde el botón (añadir atributo en HTML)
    const imagenUrl = button.getAttribute("data-imagen");
    if (imagenUrl) {
        image.src = imagenUrl;
        image.style.display = "block";
    } else {
        image.style.display = "none";
    }
}


