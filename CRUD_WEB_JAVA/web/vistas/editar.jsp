<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Hello World!</h1>
            <form action="Controlador" method="post">
                <input type="hidden" name="accion" value="Editar">
                <input type="hidden" name="id" value="${usuario.id}">
                <label>Nombre:</label>
                <input type="text" name="inputName" value="${usuario.nombre}">
                <label>Edad:</label>
                <input type="number" name="inputEdad" value="${usuario.edad}">
                <label>Celular:</label>
                <input type="text" name="inputCel" value="${usuario.celular}">
                <input type="submit" value="Actualizar">
            </form>
        </div>
    </body>
</html>
