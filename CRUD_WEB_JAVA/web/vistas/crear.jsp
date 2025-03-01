<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Crear usuario</h1>
            <form action="Controlador" method="POST">
                <input type="hidden" name="accion" value="Crear">  <!-- 🔹 Se añade esto -->

                Nombre: <br>
                <input type="text" name="inputName" required><br>

                Edad: <br>
                <input type="number" name="inputEdad" required><br>

                Celular: <br>
                <input type="text" name="inputCel" required><br>

                <input type="submit" value="Crear"><br>

                <a href="Controlador?accion=listar">Regresar</a>
            </form>
        </div>
    </body>
</html>
