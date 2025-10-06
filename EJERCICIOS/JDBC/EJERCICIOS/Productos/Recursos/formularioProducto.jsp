<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.daw.jakarta.jdbcapp.model.Fabricante" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 600px;
            margin: 3rem auto;
            padding: 2rem;
            border-radius: 1rem;
            background: #fff;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-container">
        <h2 class="text-center text-primary mb-4">➕ Nuevo Producto</h2>

        <%
            List<Fabricante> fabricantes = (List<Fabricante>) request.getAttribute("fabricantes");
        %>

        <form action="crear" method="post">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre del producto</label>
                <input type="text" id="nombre" name="nombre" class="form-control" required placeholder="Ej: Portátil Lenovo">
            </div>

            <div class="mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" id="precio" name="precio" class="form-control" required placeholder="Ej: 599.99">
            </div>

            <div class="mb-3">
                <label for="fabricante" class="form-label">Fabricante</label>
                <select id="fabricante" name="codigo_fabricante" class="form-select" required>
                    <option value="">-- Selecciona un fabricante --</option>
                    <% 
                        if (fabricantes != null && !fabricantes.isEmpty()) {
                            for (Fabricante f : fabricantes) { 
                    %>
                        <option value="<%= f.getCodigo() %>"><%= f.getNombre() %></option>
                    <% 
                            }
                        } else { 
                    %>
                        <option disabled>No hay fabricantes disponibles</option>
                    <% } %>
                </select>
            </div>

            <div class="d-flex justify-content-between">
                <a href="<%= request.getContextPath() %>/productos/listar" class="btn btn-secondary">⬅️ Cancelar</a>
                <button type="submit" class="btn btn-success">💾 Guardar producto</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
