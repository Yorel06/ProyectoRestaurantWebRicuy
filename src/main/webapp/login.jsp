
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Iniciar Sesión</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            Correo:<br><input name="correo"><br>
            Contraseña:<br><input type="password" name="contrasena"><br><br>
            <button type="submit">Ingresar</button>
        </form>
        <a href="registro.jsp">Crear cuenta nueva</a>
    </body>
</html>
