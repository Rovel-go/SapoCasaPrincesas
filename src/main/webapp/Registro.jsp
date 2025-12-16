<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Registro</title></head>
<body>
    <h2>Formulario de Registro</h2>
    <form action="registro" method="post">
        <label>Usuario:</label>
        <input type="text" name="username" required><br><br>
        <label>Contraseña:</label>
        <input type="password" name="password" required><br><br>
        <button type="submit">Registrar</button>
    </form>
    <p><a href="login">¿Ya tienes cuenta? Inicia sesión</a></p>
</body>
</html>





