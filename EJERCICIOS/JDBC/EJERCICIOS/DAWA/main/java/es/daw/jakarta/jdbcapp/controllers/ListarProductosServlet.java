package es.daw.jakarta.jdbcapp.controllers;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import es.daw.jakarta.jdbcapp.repository.DBConnection;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListarProductosServlet", value = "/productos/ver")
public class ListarProductosServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ListarProductosServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // NO PUEDO CREAR OBJETOS DBCONNECTION PORQUE SU CONSTRUCTOR ES PRIVADO
        //DBConnection db = new DBConnection();

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            log.info("* CONNECTION: " + con);
        } catch (SQLException e) {
            log.severe("* ERROR: " + e);

            throw new RuntimeException(e);
        }


    }

}