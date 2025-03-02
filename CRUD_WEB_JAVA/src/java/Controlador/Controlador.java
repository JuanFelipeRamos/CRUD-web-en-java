package Controlador;

import Modelo.Usuarios;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    String listar = "vistas/listar.jsp";
    String crear = "vistas/crear.jsp";
    String editar = "vistas/editar.jsp";

    Usuarios usuarios = new Usuarios();
    UsuariosDAO usuariosDAO = new UsuariosDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {

            System.out.println("Ejecutando listar en Controlador...");
            System.out.println("Cantidad de usuarios encontrados: " + usuariosDAO.listar().size());

            request.setAttribute("usuarios", usuariosDAO.listar());
            acceso = listar;
        } else if (action.equalsIgnoreCase("crear")) {
            acceso = crear;
        } else if (action.equalsIgnoreCase("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("usuario", usuariosDAO.list(id));
            acceso = editar;
        } else if (action.equalsIgnoreCase("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuariosDAO.elimiar(id);
            response.sendRedirect("Controlador?accion=listar");
            return;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("Crear")) {
            String nombre = request.getParameter("inputName");
            String edadStr = request.getParameter("inputEdad");
            String celular = request.getParameter("inputCel");

            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEdad(Integer.parseInt(edadStr));
            nuevoUsuario.setCelular(celular);

            usuariosDAO.crear(nuevoUsuario);
            response.sendRedirect("Controlador?accion=listar");
        } else if (action.equalsIgnoreCase("Editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("inputName");
            int edad = Integer.parseInt(request.getParameter("inputEdad"));
            String celular = request.getParameter("inputCel");

            Usuarios usuarioEditado = new Usuarios();
            usuarioEditado.setId(id);
            usuarioEditado.setNombre(nombre);
            usuarioEditado.setEdad(edad);
            usuarioEditado.setCelular(celular);

            usuariosDAO.editar(usuarioEditado);
            response.sendRedirect("Controlador?accion=listar");
        }
    }
}
