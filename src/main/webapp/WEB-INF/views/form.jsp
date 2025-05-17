<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulario de Usuario</title>
</head>
<body>
    <h2>Registrar Usuario</h2>
    <form action="guardarUsuario" method="post">
        <label>Nombre:</label>
        <input type="text" name="name" required>
        <br>
        <label>Email:</label>
        <input type="email" name="email" required>
        <br>
        <button type="submit">Guardar</button>
    </form>
</body>
</html>
