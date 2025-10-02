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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "ModificarProductosServlet", value = "/productos/modificar")
public class ModificarProductosServlet extends HttpServlet {

    private static final Logger log =  Logger.getLogger(ModificarProductosServlet.class.getName());

    // listar productos
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // -----------------------------
        // Leer parámetros.... cuidadín si no valido los nulos, blancos o cosas no deseadas
        // HACER LAS COMPROBACIONES OPORTUNAS!!!
        String codigo = request.getParameter("codigo") == null || request.getParameter("codigo").isBlank()? "" : request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String codigo_fabricante = request.getParameter("codigo_fabricante");
        String precio = request.getParameter("precio");

        // El mismo Servlet se encarga de insertar, borrar y actualizar
        // Vendrá con valores insert, update, y delate...
        String operacion =  request.getParameter("operacion");

        // Para borrar el código se mete en otro input
        String codigoBorrar = request.getParameter("codigoBorrar");

        try{
            BigDecimal precioDecimal=new BigDecimal(precio);
        }catch(NumberFormatException ex){
            //request.setAttribute("error", ex.getMessage());
            request.setAttribute("error", "Has escrito mal el precio. Debe contener números y decimales con punto!!! ej: 1500.80");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            //response.sendRedirect("/error.jsp");
        }

        log.info("************** PASO!!!");// RARUNO RARUNO... PASA!!!!


        // ----------------------------------------

        try {
            GenericDAO<Producto,Integer> daoP = new ProductoDAO();

            // Damos por hecho que todos los parámetros vienen correctamente....
            Producto nuevoProducto = new Producto(
                    Integer.parseInt(codigo),
                    nombre,
                    new BigDecimal(precio),
                    Integer.parseInt(codigo_fabricante));

            // Depende de la operación hago una cosa u otra
            switch (operacion) {
                case "insert" -> daoP.save(nuevoProducto);
                case "update" -> daoP.update(nuevoProducto);
                case "delete" -> daoP.delete(Integer.parseInt(codigoBorrar));
            }



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
            // Este mensaje depende de la operación realizada!!!! PENDIENTE!!
            out.println("<h1>Insertado producto correctamente!!!</h1>");
            out.println("</body>");
        }


    }



    @Override
    public void destroy() {
        log.info("Destroy!!!!!!!!!!!!!1");
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e); // el server devolvería 500
            // OBSERVACIÓN....
            // QUIERO REUTILIZAR ERROR.JSP PARA ENVIAR e.getMesage()
        }
    }
}