<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cookie</title>
</head>
<body>
<%
    String color = "#ffffff";
    final String COOKIE_NAME = "colorFondo"; // pendiente final static en JSP ?????

    // PENDIENTE CAMBIAR EL CÓDIGO PARA ENCONTRAR LA COOKIE DE DIFERENTES FORMAS.
    // WHILE... EVITAR BREAK
    // LIST Y CONTAINS....
    if (request.getCookies() != null){
        for (Cookie cookie : request.getCookies()){
            if (cookie.getName().equals(COOKIE_NAME)){
                color = cookie.getValue();
                break;
            }
        }
    }

%>
<h1><%= "Bienvenido!" %>
</h1>
<p>Color de fondo actual: <strong><%=color%></strong></p>
<p>¿Quieres cambiar el color de fondo?</p>
<br/>
<ul>
    <li><a href="color.jsp">Si, cambiar color</a></li>
    <li><a href="preferencias">Borrar la preferencia</a></li>
</ul>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>