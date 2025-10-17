package es.daw.jakarta.bibliotecajdbc.controllers;

import es.daw.jakarta.bibliotecajdbc.model.Autor;
import es.daw.jakarta.bibliotecajdbc.model.Libro;
import es.daw.jakarta.bibliotecajdbc.repository.AutorDAO;
import es.daw.jakarta.bibliotecajdbc.repository.GenericDAO;
import es.daw.jakarta.bibliotecajdbc.repository.LibroDAO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;

@WebServlet({"/libros/crear","/libros/editar","/libros/borrar","/autores/crear","/autores/editar","/autores/borrar"})
public class ModificarCrearServlet extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        String tipo = "";
        if(path.contains("libros")) {
            tipo = "libros";
        } else if (path.contains("autores")) {
            tipo = "autores";
        }

        if(path.contains("borrar")) {
            String idParam = req.getParameter("id");
            if(idParam != null) {
                try {
                    int id = Integer.parseInt(idParam);
                    if(path.contains("libros")) {
                        daoLibro.delete(id);
                        resp.sendRedirect(req.getContextPath() + "/libros/listar");
                        return;
                    } else if(path.contains("autores")) {
                        daoAutor.delete(id);
                        resp.sendRedirect(req.getContextPath() + "/autores/listar");
                        return;
                    }
                } catch(NumberFormatException | SQLException e) {
                    throw new ServletException("Error al borrar: " + e.getMessage(), e);
                }
            }
        }


        if(path.contains("editar")) {
            if(path.contains("libros")) {

                String idParam = req.getParameter("id");
                String titulo = req.getParameter("titulo");
                String autorIdParam = req.getParameter("autor");
                String fechaPubParam = req.getParameter("fechaPublicacion");
                Libro libro = null;
                try {
                    Date fecha = null;
                    if(fechaPubParam != null && !fechaPubParam.isBlank()) {
                        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date parsed = df.parse(fechaPubParam);
                        fecha = new Date(parsed.getTime());
                    }
                    libro = new Libro(
                            titulo,
                            autorIdParam != null && !autorIdParam.isBlank() ? java.math.BigInteger.valueOf(Long.parseLong(autorIdParam)) : null,
                            fecha,
                            idParam != null && !idParam.isBlank() ? java.math.BigInteger.valueOf(Long.parseLong(idParam)) : null
                    );
                } catch(NumberFormatException e) {
                    throw new ServletException("Parámetros numéricos inválidos para libro", e);
                } catch (ParseException e) {
                    throw new ServletException("Parámetro de fecha inválido para libro", e);
                }
                req.setAttribute("libroEditar", libro);
            } else if(path.contains("autores")) {
                String idParam = req.getParameter("id");
                String nombre = req.getParameter("nombre");
                Autor autor = null;
                try {
                    autor = new Autor(
                            nombre,
                            idParam != null && !idParam.isBlank() ? BigInteger.valueOf(Long.parseLong(idParam)) : null
                    );
                } catch(NumberFormatException e) {
                    throw new ServletException("Parámetro id inválido para autor", e);
                }
                req.setAttribute("autorEditar", autor);
            }
        }

        if("libros".equals(tipo)) {
            try {
                // no sabia que no hacia falta crear una lista, etc...
                req.setAttribute("listaAutores", daoAutor.selectAll());
            } catch (SQLException e) {
                throw new ServletException("Error cargando autores: "+e.getMessage(), e);
            }
        }

        req.setAttribute("tipo",tipo);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        try {
            if(path.contains("autores")) {
                String idParam = req.getParameter("autorId");
                String nombre = req.getParameter("nombre");
                Autor autor = new Autor(nombre, (idParam!=null && !idParam.isBlank()) ? BigInteger.valueOf(Long.parseLong(idParam)) : null);
                if(autor.getId_autor() != null) {
                    daoAutor.update(autor);
                } else {
                    daoAutor.insert(autor);
                }
                resp.sendRedirect(req.getContextPath()+"/autores/listar");
            } else if(path.contains("libros")) {
                String idParam = req.getParameter("libroId");
                String titulo = req.getParameter("titulo");
                String autorId = req.getParameter("autor");
                String fechaParam = req.getParameter("fechaPublicacion");
                Date fecha = null;
                if(fechaParam != null && !fechaParam.isBlank()) {
                    try {
                        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date parsed = df.parse(fechaParam);
                        fecha = new Date(parsed.getTime());
                    } catch (ParseException pe) {
                        throw new ServletException("Fecha de publicación con formato inválido, use dd/MM/yyyy", pe);
                    }
                }
                Libro libro = new Libro(titulo,
                        (autorId!=null && !autorId.isBlank()) ? BigInteger.valueOf(Long.parseLong(autorId)) : null,
                        fecha,
                        (idParam!=null && !idParam.isBlank()) ? BigInteger.valueOf(Long.parseLong(idParam)) : null);
                if(libro.getId_libro() != null) {
                    daoLibro.update(libro);
                } else {
                    daoLibro.insert(libro);
                }
                resp.sendRedirect(req.getContextPath()+"/libros/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ruta POST no soportada");
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error procesando formulario: "+e.getMessage(), e);
        }
    }
}
