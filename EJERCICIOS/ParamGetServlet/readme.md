# Ejemplo Servlet con pasos de parámetros por Get

## Página index.html

<img width="913" height="362" alt="image" src="https://github.com/user-attachments/assets/05f486fb-c176-4bf3-99d5-8d1b5c5f392f" />


## Diferentes páginas html resultado

**Si el código es superior a 100:**

<img width="818" height="323" alt="image" src="https://github.com/user-attachments/assets/92fb37fe-a01c-4fc9-a224-fd4f432262cd" />

---

**Si el código no es superior a 100:**

<img width="841" height="310" alt="image" src="https://github.com/user-attachments/assets/e1820fda-2f8a-43f9-88b2-9995859825c5" />



## Código de ayuda:

**En el servlet:**

```
  // Entrada
  String param = request.getParameter("saludo");

  // Salida
  out.println("       <h2>El saludo enviado es:"+param+"</h2>");
```

**En la página html**

```
    <h1>Mi primera app web</h1>
    <p><a href="url-get?saludo=Hola caracola">Ejecutar el ParamsGetServlet (url-get?saludo=Hola caracola)</a></p>
    <p><a href="url-get?saludo=Hola&nombre=melola&codigo=666">Ejecutar el ParamsGetServlet con 3 params(url-get?saludo=Hola&nombre=melola&codigo=666)</a></p>
    <p><a href="url-get?saludo=Hola&nombre=melola&codigo=66">Ejecutar el ParamsGetServlet con 3 params(url-get?saludo=Hola&nombre=melola&codigo=66)</a></p>
```

---
## Ampliación

Corrige el error que sale al hacer clic en el primer enlace de la página index.html.

![image](https://github.com/user-attachments/assets/6e7c680b-a336-4d27-9727-e9611f18818d)


En vez de que salga el error, que el servlet devuelva un mensaje indicando que el código no puede ser nulo.

