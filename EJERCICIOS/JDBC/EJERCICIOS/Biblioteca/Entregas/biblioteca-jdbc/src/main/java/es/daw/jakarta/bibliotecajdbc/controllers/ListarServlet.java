package es.daw.jakarta.bibliotecajdbc.controllers;

import java.io.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import es.daw.jakarta.bibliotecajdbc.model.Autor;
import es.daw.jakarta.bibliotecajdbc.model.Libro;
import es.daw.jakarta.bibliotecajdbc.repository.AutorDAO;
import es.daw.jakarta.bibliotecajdbc.repository.GenericDAO;
import es.daw.jakarta.bibliotecajdbc.repository.LibroDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet({"/autores/listar","/libros/listar"})
public class ListarServlet extends HttpServlet {

    private GenericDAO<Libro, BigInteger> daoLibro;
    private GenericDAO<Autor,BigInteger> daoAutor;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

       try {
           daoLibro = new LibroDAO();
           daoAutor = new AutorDAO();
       } catch(SQLException e) {
           e.printStackTrace();
           throw new ServletException(e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request,
                     HttpServletResponse response) throws IOException , ServletException{
        String path = request.getServletPath();
        List<Autor> autores = null;
        try {
            autores = daoAutor.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("autores",autores);

        if(path.equals("/autores/listar")){

                request.setAttribute("tipo","autores");
                getServletContext().getRequestDispatcher("/lista.jsp").forward(request,response);
                return;

        } else if (path.equals("/libros/listar")){
            try {
                List<Libro> libros = daoLibro.selectAll();
                request.setAttribute("tipo","libros");
                request.setAttribute("libros",libros);
                getServletContext().getRequestDispatcher("/lista.jsp").forward(request,response);
                return;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void destroy() {
    }
}