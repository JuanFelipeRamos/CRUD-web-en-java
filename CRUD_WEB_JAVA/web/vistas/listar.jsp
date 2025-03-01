<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuarios"%>
<%@page import="ModeloDAO.UsuariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            <table border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Edad</th>
                        <th>Celular</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        UsuariosDAO dao = new UsuariosDAO();
                        List<Usuarios> list = dao.listar();
                        Iterator<Usuarios> iter = list.iterator();
                        Usuarios us = null;

                        while (iter.hasNext()) {
                            us = iter.next();
                    %>
                    <tr>
                        <td><%= us.getId()%></td>
                        <td><%= us.getNombre()%></td>
                        <td><%= us.getEdad()%></td>
                        <td><%= us.getCelular()%></td>
                        <td>
                            <a href="Controlador?accion=editar&id=<%= us.getId()%>">️Editar</a> | 
                            <a href="Controlador?accion=eliminar&id=<%= us.getId()%>" style="color: red;">Quitar</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </body>
</html>
