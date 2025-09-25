# PROYECTO CabecerasApp

## Crea el proyecto

Añade a tu proyecto la página **index.html** y sigue las indicaciones del ejercicio 1 y 2.
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cabeceras</title>
</head>
<body>
    <h1>TRABAJANDO CON CABECERAS HTTP</h1>
    <p><a href="cabeceras-request">Obtener información de la cabecera HTTP (request)</a></p>
    <p><a href="productos.xls">Exportar a XLS</a></p>
    <p><a href="productos.html">Exportar a HTML</a></p>
</body>
</html>
```

## EJERCICIO 1: cabeceras de request Http

```
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    resp.setContentType("text/html;charset=UTF-8");

    // Información de la solicitud
    String metodoHttp = req.getMethod();
    String requestUri = req.getRequestURI();
    String requestUrl = req.getRequestURL().toString();
    String contextPath = req.getContextPath();
    String servletPath = req.getServletPath();
    String ipCliente = req.getRemoteAddr();
    String ipLocal = req.getLocalAddr();
    int port = req.getLocalPort();
    String scheme = req.getScheme();
    String host = req.getHeader("host");
    String url = scheme + "://" + host + contextPath + servletPath;
    String url2 = scheme + "://" + ipLocal + ":" + port + contextPath + servletPath;

    try (PrintWriter out = resp.getWriter()) {

        // Encabezado HTML usando literal template
        out.println("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <title>Cabeceras HTTP Request</title>
            </head>
            <body>
                <h1>Cabeceras HTTP Request</h1>
                <ul>
            """);

        // Lista de información básica de la solicitud
        String infoHtml = """
            <li>Metodo HTTP: %s</li>
            <li>Request URI: %s</li>
            <li>Request URL: %s</li>
            <li>Context Path: %s</li>
            <li>Servlet Path: %s</li>
            <li>IP local: %s</li>
            <li>IP cliente: %s</li>
            <li>Puerto local: %d</li>
            <li>Scheme: %s</li>
            <li>Host: %s</li>
            <li>URL: %s</li>
            <li>URL2: %s</li>
            """.formatted(metodoHttp, requestUri, requestUrl, contextPath, servletPath,
                          ipLocal, ipCliente, port, scheme, host, url, url2);

        out.println(infoHtml);

        // Headers HTTP usando streams para mayor legibilidad
        out.println(
            java.util.Collections.list(req.getHeaderNames()).stream()
                .map(name -> "<li>" + name + ": " + req.getHeader(name) + "</li>")
                .reduce("", String::concat)
        );

        // Cierre del HTML
        out.println("""
                </ul>
            </body>
            </html>
            """);
    }
}

```

### AMPLIACIÓN: mejora el proyecto usando JSP.

___

## EJERCICIO 2: cabeceras de Response Http

### Generar informe de productos XLS

Vamos a exportar productos a dos formatos diferentes: html y css
- Usaremos **productos.jsp** para generar el HTML.
- Usaremos la cabecera apropiada:
  ```
              // Poner el mime type adecuado
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment;filename=products.xsl");
  ```

Vamos a exportar los productos a un archivo XSL sencillo, una tabla con datos.

Tenemos que crear un nuevo servlet, controlador, llamado **ProductoXLSServlet**.

Si queremos trabajar con xsl de una forma más compleja necesitaremos utilizar una API específica, por ejemplo, con Apache POI es una API Java para importar y exportar datos para documentos Microsoft(Excel, Word, Project, etc.)...

**Pasos:**

1. Creamos clase Producto.
2. Creamos los servicios ProductoService (interface) y ProductoServiceImpl con datos fijos, no cogidos de una base de datos.
3. Creamos el controlador, ProductoXslServlet.
4. En el navegador ponemos la ruta al servlet con la extensión xsl y por código detecta que va a generar un xsl en vez de un html:
@WebServlet({"/productos.xls", "/productos.html", "/productos"})

OJO!!! No es una extensión real. Es simplemente el nombre que le hemos dados, no hace falta que tenga el .xls, podría haberlo llamado productosXSL….

<img width="404" height="169" alt="image" src="https://github.com/user-attachments/assets/25c48d4f-225e-4df5-99eb-45ff120312de" />

## EJERCICIO 3: mostrar la hora actualizada

<img width="652" height="306" alt="image" src="https://github.com/user-attachments/assets/b40708d2-9413-4717-9421-7ad1f0110cd9" />

Crearemos un nuevo servlet llamado **HoraActualizadaServlet**

En él aprenderemos a usar el siguiente código:

```
        resp.setHeader("refresh", "1");
        LocalTime hora = LocalTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss");

        ...

        hora.format(df)

```
