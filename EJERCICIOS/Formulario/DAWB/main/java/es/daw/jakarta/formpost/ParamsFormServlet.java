package es.daw.jakarta.formpost;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/registro")
public class ParamsFormServlet extends HttpServlet {
    private String message;

    /*
    SLF4J:
    Simple Logging Facade for Java es una librería que actúa como interface o abstracción para el registro de logs en aplicaciones Java.
    En lugar de atarte a una implementación específica de logs (como Log4j, java.util.logging, o Logback), SLF4J te permite escribir código que es independiente de la implementación de registro y luego elegir qué framework usar en tiempo de ejecución.
    Logback: Es el backend de logging que implementa los logs en Spring Framework. Spring Boot ya lo configura por defecto.
     */
    // Logger de util
    // https://keepcoding.io/blog/que-es-java-util-logging-logger-como-funciona/
    private static final Logger logger = Logger.getLogger(ParamsFormServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.doPost(req, resp);

        // 1. LECTURA DE PARÁMETROS DEL REQUEST
        /*
         * \s ==> cualquier carácter de espacio en blanco incluyendo (\t, \n, \r)
         * Si ponemos \s simplemente, dará error por secuencia de escape inválida.
         * En java la barra \ se usa para \n \t ....
         * Por eso hay que escapar \s
         *
         */
        // Si no va relleno llegará cadena vacía
        // Cuidadín con los espacios en blanco!!!!
        String username = req.getParameter("username");
        // no está permitido espacios... los quito!!!
        if (username != null) {
            //username = username.replace(" ", "");
            //username = username.trim(); // quita especios iz y derecha... ???
            username = username.replaceAll("\\s","");
        }
        logger.info("username: " + username);
        String password = req.getParameter("password");
        logger.info("password: " + password);
        String email = req.getParameter("email");
        logger.info("email: " + email);
        String pais =  req.getParameter("pais");
        logger.info("pais: " + pais);

        String[] lenguajes = req.getParameterValues("lenguajes");
        if (lenguajes != null) {
            logger.info("lenguajes 1: " + Arrays.toString(lenguajes));
            logger.info("lenguajes 2: " + lenguajes.toString());
            logger.info("lenguajes 3: " + lenguajes); //truño
        }


        String[] roles  = req.getParameterValues("roles");
        if  (roles != null) {
            logger.info("roles: " + Arrays.toString(roles));
        }
        logger.info("roles: " + roles);
        String idioma = req.getParameter("idioma");
        logger.info("idioma: " + idioma);
        String habilitar = req.getParameter("habilitar");
        logger.info("habilitar: " + habilitar);
        String secreto = req.getParameter("secreto");
        logger.info("secreto: " + secreto);

        // 2. COMPROBACIONES PARA GENERAR ERRORES
        /*

            el username es requerido.
            el password no puede ser vacío.
            el email es requerido y debe tener un formato de correo.
            debe seleccionar un idioma.

         */
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
        ArrayList<String> errores = new ArrayList<>();
        //List<String> errores2 = new ArrayList<>();

        // confirmar diferencia isBlank isEmpty...
        if (username.isBlank()) {
            errores.add("El username es obligatorio");
        }
        if (password.isBlank()) {
            errores.add("El password es obligatorio");
        }
        // pendiente utilizar expresión regular para comprobar el password
        if (email.isBlank() || !email.contains("@")) {
            errores.add("El email es obligatorio y debe tener un formato correcto ( una @)");
        }
        if (idioma == null) errores.add("Debes seleccionar un idioma!");

        // 3. GENERAR HTML DE RESPUESTA

        // Devolver mensaje de texto... chimpún
        //resp.getWriter().append("Served at: ").append(req.getContextPath());

        resp.setContentType("text/html");


        try(PrintWriter out = resp.getWriter()){
            // usando literal template
            out.println("""
                    <html>
                    <head>
                        <title>ParamsFormServlet</title>
                    </head>
                    <body>
                        <h1>Informe de datos recibidos del formulario</h1>
                        <ul>
                    """);
            if (!errores.isEmpty()){
//                for (String error : errores) {
//                    out.println("<li>" + error + "</li>");
//                }
                //errores.forEach(error -> out.println("<li>" + error + "</li>"));
                /*
                %s => cadena
                %d => enero
                %.2f =>123.456 -> 123.45
                %c => carácter
                %b => booleano
                 */
                errores.forEach(error -> out.printf("<li>%s</li>\n",error));

            }else{
                // el resto de campos...
//                String lenguajesHtml = Arrays.stream(lenguajes)
//                        .map(lang -> "<li>"+lang+"</li>")
//                        .collect(Collectors.joining("\n"));
                String lenguajesHtml="";
                for (String lenguaje : lenguajes) {
                    //lenguajesHtml += "<li>"+lenguaje+"</li>\n";
                    lenguajesHtml = lenguajesHtml.concat("<li>"+lenguaje+"</li>\n"); // ERROR!!! NO SE MUESTRA EL CONTENIDO!!!! VAMOS A DEBUGEAR
                }

//                String rolesHtml=Arrays.stream(roles)
//                        .map(rol -> "<li>"+rol+"</li>\n")
//                        .collect(Collectors.joining("\n")); /7??????????

                String rolesHtml="";
                for (String role : roles) {
                    rolesHtml = rolesHtml.concat("<li>"+role+"</li>\n");
                }

                // Si no hay errores pinto items en la lista con los valores de los campos
                String htmlBody = """
                        <li>Username: %s</li>
                        <li>Password: %s</li>
                        <li>Email: %s</li>
                        <li>Lenguajes: <ul>%s</ul></li>
                        <li>Roles:
                            <ul>%s</ul>
                        </li>
                        <li>Idioma: %s</li>
                        <li>Habilitar: %s</li>
                        <li>Secreto: %s</li>
                        """.formatted(username,password,email,lenguajesHtml,rolesHtml,idioma,habilitar,secreto); // faltaba idioma

                out.println(htmlBody);

            }
            out.println("""
                            </ul>
                            <p><a href="index.jsp">volver</a></p>
                        </body>
                    </html>
                    """);
        }// end del try



    }
}