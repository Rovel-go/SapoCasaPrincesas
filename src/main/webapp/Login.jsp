<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Login</title></head>
<body>
    <h2>Formulario de Login</h2>
    <form action="login" method="post">
        <label>Usuario:</label>
        <input type="text" name="username" required><br><br>
        <label>Contraseña:</label>
        <input type="password" name="password" required><br><br>
        <button type="submit">Ingresar</button>
    </form>
    <p><a href="registro">¿No tienes cuenta? Regístrate aquí</a></p>
</body>
</html>





