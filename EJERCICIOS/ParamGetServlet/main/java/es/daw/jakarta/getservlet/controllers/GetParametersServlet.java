package es.daw.jakarta.getservlet.controllers;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "get-params", value = "/get-params")
@WebServlet("/get-params") // mapping
public class GetParametersServlet extends HttpServlet {

    // Es un identificador único de versión para una clase que implementa Serializable
    // Verificar que la versión de la clase sea compatible durante el proceso de serialización/deserialización
    // Contenedores web serializan los servlets
    // Guardar el estado del Servlet en disco o enviarlo entre JVMs (por ejemplo, en sesiones distribuidas o reinicios del servidor).
    private static final long serialVersionUID = 1L; // 1L significa “versión 1 de esta clase”.	

    private static final int LIMIT = 100;

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("**************** servlet init ***************************");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("************** estoy en GET!!!!");

        // -------------------- RECOGIDA DE DATOS (REQUEST) ---------------------------------

        String saludo = request.getParameter("saludo");
        String nombre = request.getParameter("nombre");

        // Controlar si llega nulo
        //int codigo = Integer.parseInt(request.getParameter("codigo"));
        String codigo = request.getParameter("codigo");

        //String message = "";  // mostrar mensaje en html según condición
        String message;

        // --------------------- LÓGICA ---------------------------

        // Devolver mensaje indicando que el código no puede ser nulo.
        if (codigo == null) {
            message = "El código no puede ser nulo";
        } else {

            try {
                int code = Integer.parseInt(codigo); // lanza la excepción NumberFormatException si el texto no es "numérico"
                // Controlar la excepción NumberFormatException
                // message = "El código es numérico: "+code;

                // Si el código es mayor que 100 devolver: has superado el limite establecido a 100

                //message = (code > LIMIT)?"Has superado el límite establecido a "+LIMIT:"";
                // Si no, devolver: El saludo enviado es: %s %s con código %s
                if (code > LIMIT)
                    message = "Has superado el límite establecido a "+LIMIT;
                else{
                    message = "El saludo enviado es: %s %s con código %s";
                    message = String.format(message,  saludo, nombre, code);
                }



            } catch (NumberFormatException e) {
                message = "El código no se puede convertir a entero, ten cuidadín!";
            }

        }


        // -------------------- RESPONSE ----------------------
        response.setContentType("text/html");

        // try con recursos...
        try(PrintWriter out = response.getWriter()){
            out.println("<html><body>");
            out.println("<h1> PAramsGetServlet!!!</h1>");
            //out.println("<h1> El saludo enviado es: " + saludo+ " "+nombre+" con codigo "+codigo+"</h1>");
            out.println("<h2>" + message + "</h2>");
            out.println("</body></html>");
        }


        // no tengo que hacer más!!!!
    }

}