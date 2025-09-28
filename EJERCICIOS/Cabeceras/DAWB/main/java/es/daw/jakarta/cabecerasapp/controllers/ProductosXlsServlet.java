package es.daw.jakarta.cabecerasapp.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/productos.xls","productos.html"})
public class ProductosXlsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // Pendiente de completar


    }


    public void destroy() {
        // cerrar recursos...
        // ...
    }
}