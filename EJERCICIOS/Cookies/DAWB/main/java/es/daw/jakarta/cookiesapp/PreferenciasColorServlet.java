package es.daw.jakarta.cookiesapp;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "PreferenciasColorServlet", value = "/preferencias")
public class PreferenciasColorServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Borra la preferencia... cookie

    }

}