package es.daw.jakarta.formpost.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.data.repository.Param;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/registro")
public class ParamsFormServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> PARAMS FORM SERVLET VÍA GET</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");

        String[] lenguajes = req.getParameterValues("lenguajes");

        String[] roles = req.getParameterValues("roles");

        String idioma = req.getParameter("idioma");

        String habilitar = req.getParameter("habilitar");

        String secreto = req.getParameter("secreto");



        /*
         * Generalmente una mejor práctica. Principios de diseño orientado a interfaces
         * Tu código dependerá de la interfaz (List) en lugar de la implementación concreta (ArrayList).
         * Esto facilita cambiar la implementación a otra clase que implemente la interfaz List
         * (como LinkedList, CopyOnWriteArrayList, etc.) sin necesidad de modificar mucho el código.
         * Solo puedes utilizar los métodos definidos en la interfaz List.
         * Si necesitas acceder a métodos específicos de ArrayList, tendrías que hacer un casting.
         */
        /*
         * ArrayList si necesitas acceso rápido por índice o si tienes pocas modificaciones en la lista.
         * LinkedList si haces muchas inserciones o eliminaciones en el medio de la lista o en los extremos, o si tu lista cambia de tamaño con mucha frecuencia.
         */
        ArrayList<String> errores = new ArrayList<String>();
        //List<String> errores2 = new ArrayList<String>();

        // PENDIENTE REALIZAR LAS VALIDACIONES
       /*
        el username es requerido.
        el password no puede ser vacío.
        el email es requerido y debe tener un formato de correo.
        debe seleccionar un idioma.
         */
        // PENDIENTE!!! diferencia entre isBlank y isEmpty (controla nulos?)
        // OBSERVACIÓN!!! POR QUÉ ME SALE EN ROJO isBlank''''
        if (username.isBlank()) {
            errores.add("El username es obligatorio");
        }
        if (password.isBlank()) {
            errores.add("El password es obligatorio");
        }

        // pendiente añadir a erroes los mensajes de error según proceda

        // 3. GENERAR HTML DE RESPUESTA
        try (PrintWriter out = resp.getWriter()) {
//            out.println("<html><body>");
//            out.println("<h1> PARAMS FORM SERVLET VÍA GET</h1>");
//            out.println("</body></html>");
            out.println("""
                    <html>
                    <head>
                        <title>ParamsFormServlet</title>
                    </head>
                    <body>
                        <h1>Informe de datos recibidos del formulario</h1>
                        <ul>
                    """);

            if (!errores.isEmpty()) {
                    // SI HAY ERRORES PINTO LA LISTA DE ERRORES
                                    // un li por cada mensaje de error
                    //           for (String error: errores){
                    //               //out.println("<li>"+error+"</li>");
                    //               out.printf("<li>%s</li>\n", error);
                    //           }


                errores.forEach(err -> out.printf("<li>%s</li>\n", err));
            }else{
                // PENDIENTE
                // SI NO HAY ERRORES PINTO LA LISTA DE VALORES RECIBIDOS...
                // li li li li
            }

            out.println("""
                            </ul>
                            <p><a href="index.jsp">volver</a></p>
                        </body>
                    </html>
                    """);



        }


    }
}