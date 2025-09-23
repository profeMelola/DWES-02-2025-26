# Jakarta Server Pages (JSP)

https://jakarta.ee/specifications/pages/

## ¿Qué es JSP?

**Jakarta Server Pages (JSP)** es una tecnología basada en Java que permite crear páginas web dinámicas.  
Se ejecuta en el **servidor** (dentro de un contenedor como Tomcat, Jetty, GlassFish, etc.) y genera **HTML** que se envía al navegador del cliente.


- Combina **HTML + Java** en un mismo archivo.
- Se traduce a un **servlet Java** por el servidor de aplicaciones.
- Facilita separar la lógica de presentación del código Java.

Los archivos JSP se crean dentro de la carpeta webapp. 

---

## Estructura Básica de un JSP

Un archivo JSP suele tener la extensión `.jsp`.

Ejemplo mínimo:

```jsp
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hola JSP</title>
</head>
<body>
    <h1>¡Hola desde JSP!</h1>
    <p>La hora actual es: <%= new java.util.Date() %></p>
</body>
</html>
```

---

## Elementos clave en JSP

### Expresiones 

```
<p>El resultado de 2 + 3 es: <%= 2 + 3 %></p>
```
  
### Scriplets

```
<%
    int contador = 5;
    out.println("El contador vale: " + contador);
%>

```

### Declaraciones

```
<%! 
    int suma(int a, int b) {
        return a + b;
    }
%>

<p>La suma de 4 y 7 es: <%= suma(4, 7) %></p>

```

---

## Directivas de JSP

### Directiva page

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

```

### Directiva include

```
<%@ include file="header.jsp" %>

```

### Directiva taglib

```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

```
---

## Acciones JSP

<jsp:include> → incluye contenido dinámicamente.

<jsp:forward> → redirige a otra página.

<jsp:useBean> → trabaja con JavaBeans.

```
<jsp:useBean id="usuario" class="com.ejemplo.Usuario" scope="session" />
<jsp:setProperty name="usuario" property="nombre" value="Ana" />
<p>Bienvenida, <jsp:getProperty name="usuario" property="nombre" />!</p>

```

--- 
## Declaración de Objetos Implicitos: JSP proporciona objetos implícitos para interactuar con la solicitud, respuesta, sesión y contexto de aplicación:

Al igual que en los servlets desde JSP también es posible acceder a la petición request y otros objetos implícitos.

- **request:** Representa la solicitud del cliente.
- **response:** Representa la respuesta al cliente.
- **session:** Representa la sesión del usuario.
- **application:** Representa el contexto de la aplicación.
- **out:** Representa el objeto de escritura de la respuesta.
- **config:** Representa la configuración del servlet.
- **pageContext:** Proporciona un contexto de página más amplio.

--- 

## Expresiones EL en JSP

### ${param}

param es un mapa implícito (Map<String, String>) disponible en EL.

Cada clave es el nombre de un parámetro del request (lo que envía un formulario o query string).

### ${paramValues}

paramValues es otro mapa implícito (Map<String, String[]>).

Sirve cuando un parámetro puede tener varios valores, por ejemplo en un <select multiple> o en varios checkboxes con el mismo name.

Devuelve un array de Strings (String[]).

### Otros

<img width="662" height="462" alt="image" src="https://github.com/user-attachments/assets/a6406c1d-84b3-4b8b-865a-258efd90f5e2" />

---

## Buenas prácticas

- Evitar lógica compleja en JSP → usar Servlets o Beans.
- Usar JSTL y Expresiones EL (${...}) en lugar de scriptlets.
- Separar:
  - Presentación → JSP
  - Lógica de negocio → Java (servlets, servicios).
  - Usar codificación UTF-8 siempre para evitar problemas de caracteres.
