## Vamos a crear un **LoginServlet** que simula el login de un usuario.

En el caso de que no coincida con unos valores constantes, deberá devolver un código de error 401 como que no está autorizado.

Aquí tienes código que te servirá de ayuda:

```
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    ...

    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");


```

Usa la página **login.html** que contiene un formulario que usa el método post para enviar un login y password de usuario.

Si introducimos mal el login y password se monstrará el error en el navegador:

<img width="289" height="153" alt="image" src="https://github.com/user-attachments/assets/57666532-83bb-435d-9122-e11cbca20333" />


Si coinciden, aparecerá un página html con el mensaje de éxito:

<img width="266" height="133" alt="image" src="https://github.com/user-attachments/assets/898f760f-0d60-4928-bae2-9108531d73a8" />
