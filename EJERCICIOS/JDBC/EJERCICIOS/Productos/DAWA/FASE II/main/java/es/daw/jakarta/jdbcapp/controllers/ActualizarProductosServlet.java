package es.daw.jakarta.jdbcapp.controllers;


import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;
import es.daw.jakarta.jdbcapp.repository.FabricanteDAO;
import es.daw.jakarta.jdbcapp.repository.GenericDAO;
import es.daw.jakarta.jdbcapp.repository.ProductoDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet("/productos/actualizar")
public class ActualizarProductosServlet extends HttpServlet {

    private GenericDAO<Producto,Integer> daoP;

    private GenericDAO<Fabricante,Integer> daoF;
    //private FabricanteDAO daoF2;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            daoP = new ProductoDAO();

            daoF = new FabricanteDAO();
            //daoF2 = new FabricanteDAO();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            throw new ServletException("Error al inicializar los DAO",e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        if (codigo == null || codigo.isBlank()) {
            // En vez de lanzar error.jsp con un mensaje personalizado
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Falta el parámetro con el código del producto a actualizar");
        }

        try {
            Optional<Producto> productoOpt= daoP.findById(Integer.parseInt(codigo));
            if (productoOpt.isEmpty()){
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"El producto no existe");
                return;
            }

            //productoOpt.isPresent()... ya no hace falta, puedo hacer el get directamente
            Producto producto = productoOpt.get();
            request.setAttribute("producto", producto);

            List<Fabricante> fabricantes = daoF.findAll();
            request.setAttribute("fabricantes", fabricantes);

            getServletContext().getRequestDispatcher("/productos/formularioProducto.jsp").forward(request, response);

        } catch (SQLException e) {
            // Cambiamos la redirección a error.jsp
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String nombre =  request.getParameter("nombre");
            BigDecimal precio = new BigDecimal(request.getParameter("precio"));
            int codigoFabricante = Integer.parseInt(request.getParameter("codigo_fabricante"));

            daoP.update(new Producto(codigo,nombre,precio,codigoFabricante));

        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
        }

        // Redirigir de nuevo a la lista actualizada
        response.sendRedirect(request.getContextPath() + "/productos/ver");
    }
}