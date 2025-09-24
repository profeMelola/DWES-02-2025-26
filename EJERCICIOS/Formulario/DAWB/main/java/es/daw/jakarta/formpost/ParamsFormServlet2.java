package es.daw.jakarta.formpost;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/registro2")
public class ParamsFormServlet2 extends HttpServlet {
    private String message;

    /*
    SLF4J:
    Simple Logging Facade for Java es una librería que actúa como interface o abstracción para el registro de logs en aplicaciones Java.
    En lugar de atarte a una implementación específica de logs (como Log4j, java.util.logging, o Logback), SLF4J te permite escribir código que es independiente de la implementación de registro y luego elegir qué framework usar en tiempo de ejecución.
    Logback: Es el backend de logging que implementa los logs en Spring Framework. Spring Boot ya lo configura por defecto.
     */
    // Logger de util
    // https://keepcoding.io/blog/que-es-java-util-logging-logger-como-funciona/
    private static final Logger logger = Logger.getLogger(ParamsFormServlet2.class.getName());

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

        // FASE II: utilizamos un MAP para cargar los errores
        Map<String,String> errores = new HashMap<String,String>(); // PENDIENTE REVISAR TIPOS DE MAP....!!!

        // confirmar diferencia isBlank isEmpty...
        if (username.isBlank()) {
            errores.put("username","El username es obligatorio");
        }
        if (password.isBlank()) {
            errores.put("password","El password es obligatorio");
        }
        // pendiente utilizar expresión regular para comprobar el password
        if (email.isBlank() || !email.contains("@")) {
            errores.put("email","El email es obligatorio y debe tener un formato correcto ( una @)");
        }
        if (idioma == null) errores.put("idioma","Debes seleccionar un idioma!");

        // 3. GENERAR HTML DE RESPUESTA

        // Añado el Map de errores en el objeto request
        req.setAttribute("errores", errores);
        // Redirigir la salida a la página JSP
        getServletContext().getRequestDispatcher("/index2.jsp").forward(req, resp);



    }
}