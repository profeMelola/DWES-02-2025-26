<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Get Params</title>
</head>
<body>
<h1><%= "Get Parameters" %>
</h1>

<br/>

    <p><a href="get-params">Sin parámetros</a></p>
    <p><a href="get-params?saludo=Hola">.... get-params?saludo=Hola</a></p>
    <p><a href="get-params?saludo=Hola&nombre=melola&codigo=666">... get-params?saludo=Hola&nombre=melola&codigo=666</a></p>
    <p><a href="get-params?saludo=Hola&nombre=melola&codigo=66">... get-params?saludo=Hola&nombre=melola&codigo=66</a></p>
    <p><a href="get-params?saludo=Hola&nombre=melola&codigo=mil">.... get-params?saludo=Hola&nombre=melola&codigo=mil</a></p>
</body>
</html>