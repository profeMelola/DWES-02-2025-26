# Parámetros en request Http

En el servlet con request.getParameter("..."):
- Si el campo estaba en el formulario con name pero vacío → devuelve cadena vacía ("").
- Si el campo no se envió en absoluto (porque no tenía name, o era un checkbox no marcado, o un radio button no seleccionado) → devuelve null.

<img width="1265" height="297" alt="image" src="https://github.com/user-attachments/assets/444f7785-5c9a-4b9c-b56c-3633c2c9f005" />

___

<img width="1111" height="827" alt="image" src="https://github.com/user-attachments/assets/c719b6e5-c0c1-468c-b496-1f56246a8c76" />

<img width="784" height="581" alt="image" src="https://github.com/user-attachments/assets/6ff31d43-be66-4336-84cf-4948da2da3d3" />

___

<img width="98" height="138" alt="image" src="https://github.com/user-attachments/assets/1317ed0e-fb38-4f3a-8047-40a36806f9ff" />  **Entonces...¿Por qué en nuestra index2.jsp del ejercicio del formulario tenemos el siguiente código?**

```
    // si no viaje el username en el request será null
    String usernameVal = request.getParameter("username") != null?request.getParameter("username"):"";
```
___
# sendRedirect vs Dispatcher forward

Podemos redirigir de diferentes maneras:

## Redirigir a una URL externa o cambiar la URL en el navegador

```
@WebServlet("/redirigir")
public class RedirigirServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}
```

## Unir el request actual a otro servlet o jsp. Seguimos con el mismo request. No cambia la URL

```
@WebServlet("/despachar")
public class DespacharServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forma 1: la ruta siempre debe ser absoluta respecto al contexto de la aplicación 
        getServletContext().getRequestDispatcher("/productos").forward(req, resp);

        // Forma 2: la ruta puede ser relativa al servlet actual. Si empieza con / será absoluta y si no empieza con / es relativa al path del servlet actual
        request.getRequestDispatcher("/productos").forward(req, resp);

    }
}

```
# Optional


Optional<T> es un contenedor que puede:

- tener un valor de tipo T
- o estar vacío (Optional.empty()).

Fue introducido en Java 8 para evitar el uso directo de null y el clásico error de NullPointerException (NPE).

En vez de:

```
Producto p = service.findById(10L);
if (p != null) {
    System.out.println(p.getNombre());
}

```

Usas:

```
service.findById(10L)
       .ifPresent(p -> System.out.println(p.getNombre()));

```

Fomenta un código más robusto porque obligas en código a decidir qué hacer si no hay objeto:
- .orElse(defaultValue)
```
String nombre = service.findById(2L)
                       .map(Producto::getNombre)
                       .orElse("Desconocido");

```
- .orElseThrow(...)

```
Producto p = service.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

```

- .ifPresent(...)


___

# Maven scope

Valores típicos de <scope> en Maven:

## compile (por defecto)

- Es el valor que se aplica si no pones <scope>.
- Significa que la dependencia está disponible en:
    - Compilación (javac)
    - Ejecución (runtime)
    - Test
- Ejemplo: librerías que tu aplicación necesita siempre, como spring-core.

## provided
- La dependencia se necesita para compilar y testear, pero no se empaqueta en el JAR/WAR porque el entorno de ejecución ya la provee.
- Ejemplo típico: servlet-api en proyectos web, ya que el servidor de aplicaciones (Tomcat, WildFly, etc.) la proporciona.

## runtime
- No se necesita para compilar, pero sí para ejecutar y testear.
- Ejemplo: un driver JDBC (mysql-connector-java), que no afecta a la compilación pero sí lo necesitas al correr.

## test
- Solo está disponible en el classpath de test.
- Ejemplo: junit, mockito.

## system, import...
Obsoletas
