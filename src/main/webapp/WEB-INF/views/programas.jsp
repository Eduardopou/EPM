<%@page import="com.example.demo.models.ProgramasModel"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cañón de Santa Elena - Programas</title>
    <style>

    </style>
</head>
<body>

<header>
    <h1>Cañón de Santa Elena</h1>
    <p>Área Natural Protegida - México</p>
</header>

<nav>
    <a href="/">Inicio</a>
    <a href="/animales">Fauna</a>
    <a href="/programa">Programas</a>
    <a href="/reglas">Reglamento</a>
    <a href="/investigaciones">Investigaciones</a>
</nav>

<div class="content">
    <!-- Tabla de Programas -->
    <div class="table-container">
        <h2>Programas del Cañón</h2>
        <input type="text" id="search" class="search-bar" placeholder="🔍 Buscar programa..." onkeyup="filtrarTabla()">

        <div class="table-wrapper">
            <table id="programasTable">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Acción</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ProgramasModel> listaProgramas = (List<ProgramasModel>) request.getAttribute("listaProgramas");
                    if (listaProgramas != null) {
                        for (ProgramasModel programa : listaProgramas) {
                %>
                <tr>
                    <td><%= programa.getId() %></td>
                    <td>
                        <button class="info-button"
                                data-nombre="<%= programa.getNombre().replace("\"", "&quot;") %>"
                                data-descripcion="<%= programa.getDescripcion().replace("\"", "&quot;") %>"
                                onclick="mostrarDesdeDataset(this)">
                            Mostrar Información
                        </button>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="2">No hay programas disponibles.</td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Información del programa -->
    <div class="info-container">
        <h2>Información del Programa</h2>
        <p id="programaInfo">Selecciona un programa para ver su información.</p>
    </div>
</div>

<footer>
    &copy; 2025 Cañón de Santa Elena. Todos los derechos reservados.
</footer>

<script>
    function mostrarDesdeDataset(boton) {
        const nombre = boton.dataset.nombre;
        const descripcion = boton.dataset.descripcion;

        document.getElementById("programaInfo").innerText =
            "Nombre del programa: " + nombre + "\n\nDescripción: " + descripcion;
    }

    function filtrarTabla() {
        let input = document.getElementById("search").value.toLowerCase();
        let table = document.getElementById("programasTable");
        let tr = table.getElementsByTagName("tr");

        for (let i = 1; i < tr.length; i++) {
            let td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                let texto = td.textContent || td.innerText;
                tr[i].style.display = texto.toLowerCase().includes(input) ? "" : "none";
            }
        }
    }
</script>

</body>
</html>