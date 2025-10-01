package es.daw.jakarta.jdbcapp.controllers;

import es.daw.jakarta.jdbcapp.model.Producto;
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
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "ModificarProductosServlet", value = "/productos/modificar")
public class ModificarProductosServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ModificarProductosServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Pensar que este método Get va a recibir diferentes operaciones:
        // insert
        // update
        // delete...

        // Recoger los datos de entrada del request!!!!



        // ------------------------------------------------------
        //List<Producto> productos = null;
        try {
            GenericDAO<Producto,Integer> daoP = new ProductoDAO();

            //productos = daoP.findAll();
            Producto productoNuevo = new Producto(........);
            daoP.save(productoNuevo);

            //productos.forEach(System.out::println);
            //productos.forEach(p -> log.info(p.toString()));

        } catch (SQLException e) {
            log.severe(e.getMessage());
            request.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(request,response);
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

}