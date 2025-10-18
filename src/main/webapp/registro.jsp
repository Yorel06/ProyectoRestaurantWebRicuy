
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Registro de Usuario</h2>
        <form action="${pageContext.request.contextPath}/registro" method="post">
            Nombre Completo:<br><input name="nombre"><br>
            Correo:<br><input name="correo"><br>
            Contraseña:<br><input type="password" name="contrasena"><br><br>
            <button type="submit">Registrar</button>
        </form>
        <a href="login.jsp">Ya tengo cuenta</a>
    </body>
</html>
