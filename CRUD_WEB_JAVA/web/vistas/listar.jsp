<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuarios"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Usuarios</h1>
            <a href="Controlador?accion=crear">Crear nuevo usuario</a>

            <% if (request.getAttribute("usuarios") != null) {%>
            <% } else { %>
            <p>No se encontró la lista de usuarios.</p>
            <% }%>

            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Edad</th>
                    <th>Celular</th>
                    <th>Acciones</th>
                </tr>
                <c:forEach var="usuario" items="${usuarios}">
                    <tr>
                        <td>${usuario.id}</td>
                        <td>${usuario.nombre}</td>
                        <td>${usuario.edad}</td>
                        <td>${usuario.celular}</td>
                        <td>
                            <a href="Controlador?accion=editar&id=${usuario.id}">Editar</a> |
                            <a href="Controlador?accion=eliminar&id=${usuario.id}" onclick="return confirm('¿Seguro que deseas eliminar?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </body>
</html>
