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
        // Forma 1 (recomendado)
        getServletContext().getRequestDispatcher("/productos").forward(req, resp);

        // Forma 2
        request.getRequestDispatcher("/productos").forward(req, resp);

    }
}

```
