# Parámetros en request Http

En el servlet con request.getParameter("..."):
- Si el campo estaba en el formulario con name pero vacío → devuelve cadena vacía ("").
- Si el campo no se envió en absoluto (porque no tenía name, o era un checkbox no marcado, o un radio button no seleccionado) → devuelve null.

<img width="1265" height="297" alt="image" src="https://github.com/user-attachments/assets/444f7785-5c9a-4b9c-b56c-3633c2c9f005" />

___

<img width="1111" height="827" alt="image" src="https://github.com/user-attachments/assets/c719b6e5-c0c1-468c-b496-1f56246a8c76" />

<img width="784" height="581" alt="image" src="https://github.com/user-attachments/assets/6ff31d43-be66-4336-84cf-4948da2da3d3" />

___

<img width="50" height="50" alt="image" src="https://github.com/user-attachments/assets/a3b824e6-7b54-4b9f-a2a9-bc602d371d24" /> Entonces...¿Por qué en nuestra index2.jsp del ejercicio del formulario tenemos el siguiente código?

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
