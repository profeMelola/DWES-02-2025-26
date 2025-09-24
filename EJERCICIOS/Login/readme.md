## Ejercicio 5.2: vamos a crear un **LoginServlet** que simula el login de un usuario.

En el caso de que no coincida con unos valores constantes, deberá devolver un código de error 401 como que no está autorizado.

Aquí tienes código que te servirá de ayuda:

```
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    ...

    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");


```

Crea una página **login.html** con un formulario que use el método post para enviar un login y password de usuario.

Si introducimos mal el login y password se monstrará el error en el navegador:



Si coinciden, aparecerá un página html con el mensaje de éxito:


