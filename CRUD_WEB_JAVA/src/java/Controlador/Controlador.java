package Controlador;

import Modelo.Usuarios;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import java.io.PrintWriter;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            UsuariosDAO dao = new UsuariosDAO();
            request.setAttribute("usuarios", dao.listar());
            acceso = listar;
        } else if (action.equalsIgnoreCase("crear")) {
            acceso = crear;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("Crear")) {
            // Capturar datos del formulario
            String nombre = request.getParameter("inputName");
            String edadStr = request.getParameter("inputEdad");
            String celular = request.getParameter("inputCel");

            // Crear objeto usuario
            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setNombre(nombre);

            try {
                nuevoUsuario.setEdad(Integer.parseInt(edadStr)); // Convertir edad a número
            } catch (NumberFormatException e) {
                nuevoUsuario.setEdad(0); // Valor por defecto si hay error
                System.out.println("Error: La edad ingresada no es un número válido.");
            }

            nuevoUsuario.setCelular(celular);

            // Insertar usuario en la base de datos
            boolean insertado = usuariosDAO.crear(nuevoUsuario);

            if (insertado) {
                response.sendRedirect("Controlador?accion=listar"); // 🔹 Redirige correctamente
                return; // 🔹 Termina el método después de redirigir
            } else {
                response.getWriter().println("Error al crear usuario.");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
