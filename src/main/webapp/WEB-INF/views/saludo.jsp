<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Saludo</title>
</head>
<body>
    <h1>Hola, ${param.nombre}!</h1> 
    
    <form action="/saludo" method="get">
        <label for="nombre">Ingresa tu nombre:</label>
        <input type="text" name="nombre" id="nombre">
        <button type="submit">Enviar</button>
    </form>
</body>
</html>

