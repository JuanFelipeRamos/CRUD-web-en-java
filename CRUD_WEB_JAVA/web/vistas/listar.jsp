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

            <table border="1">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Edad</th>
                        <th>Celular</th>
                    </tr>
                </thead>

                <%
                    UsuariosDAO dao = new UsuariosDAO();
                    List<Usuarios> list = dao.listar();
                    out.println("Cantidad de usuarios: " + list.size());
                    Iterator<Usuarios> iter = list.iterator();
                    Usuarios us = null;

                    while (iter.hasNext()) {
                        us = iter.next();
                %>


                <tbody>
                    <tr>
                        <td><%= us.getId()%></td>
                        <td><%= us.getNombre()%></td>
                        <td><%= us.getEdad()%></td>
                        <td><%= us.getCelular()%></td>

                <a>Editar</a>
                <a>Quitar</a>
                </td>
                </tr>
                <% }%>
                </tbody>
            </table>
        </div>
    </body>
</html>
