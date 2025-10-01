package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.DBConnection;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "ModificarProductosServlet", value = "/productos/modificar")
public class ModificarProductosServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ModificarProductosServlet.class.getName());

    // listar productos
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //List<Producto> productos = new ArrayList<>(); // no es nula, está vacía

        // Leer parámetros....

        try {
            GenericDAO<Producto,Integer> daoP = new ProductoDAO();

            //productos = daoP.findAll();
            Producto nuevoProducto = new Producto();
            daoP.save(nuevoProducto);


        } catch (SQLException e) {
            //throw new RuntimeException(e);
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        // SALIDA
        //request.setAttribute("productos", productos);
        //getServletContext().getRequestDispatcher("/informe.jsp").forward(request,response);
        try(PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ModificarProductosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Insertado producto correctamente!!!</h1>");
            out.println("</body>");
        }


    }



    @Override
    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
            // OBSERVACIÓN....
            // QUIERO REUTILIZAR ERROR.JSP PARA ENVIAR e.getMesage()
        }
    }
}