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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/productos/crear")
public class CrearProductoServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CrearProductoServlet.class.getName());

    private GenericDAO<Fabricante, Integer> fabricanteDAO;
    private GenericDAO<Producto, Integer> productoDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        /*
            Cuando sobrescribes este método y no llamas al de la superclase,
            el HttpServlet no termina su inicialización interna.
            Concretamente, no se guarda el ServletConfig dentro del servlet, y por tanto,
            getServletConfig() y getServletContext() devuelven null.

            En  los constructores el compilador inserta la llamada al super automáticamente,
            en los métodos sboreescritos no!!!!
         */
        super.init(config);

        try {
            fabricanteDAO = new FabricanteDAO();
            productoDAO = new ProductoDAO();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            throw new ServletException("Error inicializando DAO",e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Fabricante> fabricantes = new ArrayList<>();
        try {
            fabricantes = fabricanteDAO.findAll();
            req.setAttribute("fabricantes", fabricantes);
            getServletContext().getRequestDispatcher("/productos/formularioProducto.jsp").forward(req, resp);
            //req.getRequestDispatcher("/productos/formularioProducto.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.severe(e.getMessage());
            req.setAttribute("error", e.getMessage());
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // DAMOS POR HECHO QUE TODOS LOS PARÁMETROS VAN A VENIR FENOMENAL.. NO HAGO COMPROBACIONES
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        int codigoFabricante = Integer.parseInt(request.getParameter("codigo_fabricante"));

        Producto nuevo = new Producto(codigo, nombre, precio, codigoFabricante);
        try {
            productoDAO.save(nuevo);
        } catch (SQLException e){
            logger.severe(e.getMessage());
//            request.setAttribute("error", e.getMessage());
//            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/productos/ver");
    }

}



