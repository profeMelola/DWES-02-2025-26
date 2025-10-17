<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.model.Autor" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.model.Libro" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.utils.Utils" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca JDBC - Lista</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f0f4f8, #e6ebef);
            font-family: 'Segoe UI', sans-serif;
        }
        .hero { text-align: center; padding: 2.5rem 1rem 1.5rem; }
        .hero h1 { font-weight: 700; color: #0d6efd; }
        .hero .lead { color: #6c757d; }
        .card { border: none; border-radius: 1rem; box-shadow: 0 4px 12px rgba(0,0,0,0.08); }
        thead th { background: #0d6efd; color: #fff; border-color: #0b5ed7 !important; }
        .btn-edit { --bs-btn-color:#fff; --bs-btn-bg:#0d6efd; --bs-btn-border-color:#0d6efd; --bs-btn-hover-bg:#0b5ed7; --bs-btn-hover-border-color:#0a58ca; }
        .btn-delete { --bs-btn-color:#fff; --bs-btn-bg:#dc3545; --bs-btn-border-color:#dc3545; --bs-btn-hover-bg:#bb2d3b; --bs-btn-hover-border-color:#b02a37; }
        .empty { color:#6c757d; }
    </style>
</head>
<body>
<div class="container mt-5">
    <%
        String tipo = (String) request.getAttribute("tipo");
        String h1 = "";
        if ("autores".equals(tipo)) h1 = "🧑‍💻 Lista de Autores";
        else if ("libros".equals(tipo)) h1 = "📖 Lista de Libros";
    %>

    <section class="hero">
        <h1><%= h1 %></h1>
        <p class="lead">Administra y revisa los <%= "autores".equals(tipo) ? "autores" : ("libros".equals(tipo) ? "libros" : "registros") %></p>
    </section>
<!--
    Me emocioné al querer pasar todo por URL GET al editar en vez de form input hiddens, y por eso he tenido que usar:

    - URLEncoder.encode(cadena, "UTF-8")
        espacios, acentos, símbolos o caracteres reservados de URL.Para que no rompa la url

    - Formateo de fechas (SimpleDateFormat)
        Un objeto Date no se puede insertar directamente en una URL así que usando ->  SimpleDateFormat.
-->
    <% if ("autores".equals(tipo)) { %>
        <div class="row mb-3 justify-content-end">
            <div class="col-auto">
                <a href="<%= request.getContextPath() %>/autores/crear" class="btn btn-success">
                    <i class="bi bi-plus-lg"></i> Crear Autor
                </a>
            </div>
        </div>
        <%
            List<Autor> autores = (List<Autor>) request.getAttribute("autores");
        %>
        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8">
                <div class="card">
                    <div class="card-body p-0">
                        <% if (autores != null && !autores.isEmpty()) { %>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover align-middle mb-0">
                                <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col" class="text-center">Editar</th>
                                    <th scope="col" class="text-center">Borrar</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (Autor a : autores) { %>
                                    <tr>
                                        <td><%= a.getId_autor() %></td>
                                        <td><%= a.getNombre() %></td>
                                        <td class="text-center">
                                            <a class="btn btn-sm btn-edit" href="<%=request.getContextPath()%>/autores/editar?id=<%= a.getId_autor() %>&nombre=<%= a.getNombre() != null ? URLEncoder.encode(a.getNombre(), "UTF-8") : "" %>">Editar</a>
                                        </td>
                                        <td class="text-center">
                                            <a class="btn btn-sm btn-delete" href="<%=request.getContextPath()%>/autores/borrar?id=<%= a.getId_autor() %>" onclick="return confirm('¿Seguro que quieres borrar este autor?');">Borrar</a>
                                        </td>
                                    </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                        <% } else { %>
                            <div class="p-4 text-center empty">No hay autores disponibles.</div>
                        <% } %>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <a class="btn btn-secondary" href="<%= request.getContextPath() %>">Volver</a>
                </div>
            </div>
        </div>
    <% } else if ("libros".equals(tipo)) { %>
        <div class="row mb-3 justify-content-end">
            <div class="col-auto">
                <a href="<%= request.getContextPath() %>/libros/crear" class="btn btn-success">
                    <i class="bi bi-plus-lg"></i> Crear Libro
                </a>
            </div>
        </div>
        <%
            List<Libro> libros = (List<Libro>) request.getAttribute("libros");
            List<Autor> autoresLibros = (List<Autor>) request.getAttribute("autores");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        %>
        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8">
                <div class="card">
                    <div class="card-body p-0">
                        <% if (libros != null && !libros.isEmpty()) { %>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover align-middle mb-0">
                                <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Título</th>
                                    <th scope="col">Autor</th>
                                    <th scope="col">Fecha de Publicación</th>
                                    <th scope="col" class="text-center">Editar</th>
                                    <th scope="col" class="text-center">Borrar</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (Libro libro : libros) { %>
                                    <tr>
                                        <td><%= libro.getId_libro() %></td>
                                        <td><%= libro.getTitulo() %></td>
                                        <td><%= Utils.obtenerNombreAutor(autoresLibros, libro.getId_autor()) %></td>
                                        <td><%= libro.getFechaPublicacion() != null ? dateFormat.format(libro.getFechaPublicacion()) : "—" %></td>
                                        <td class="text-center">
                                            <a class="btn btn-sm btn-edit" href="<%=request.getContextPath()%>/libros/editar?id=<%= libro.getId_libro() %>&titulo=<%= libro.getTitulo() != null ? URLEncoder.encode(libro.getTitulo(), "UTF-8") : "" %>&autor=<%= libro.getId_autor() %>&fechaPublicacion=<%= libro.getFechaPublicacion() != null ? URLEncoder.encode(dateFormat.format(libro.getFechaPublicacion()), "UTF-8") : "" %>">Editar</a>
                                        </td>
                                        <td class="text-center">
                                            <a class="btn btn-sm btn-delete" href="<%=request.getContextPath()%>/libros/borrar?id=<%= libro.getId_libro() %>" onclick="return confirm('¿Seguro que quieres borrar este libro?');">Borrar</a>
                                        </td>
                                    </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                        <% } else { %>
                            <div class="p-4 text-center empty">No hay libros disponibles.</div>
                        <% } %>
                    </div>
                </div>
                <div class="text-center mt-4">
                    <a class="btn btn-secondary" href="<%= request.getContextPath() %>">Volver</a>
                </div>
            </div>
        </div>
    <% } else { %>
        <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8">
                <div class="alert alert-warning text-center">Tipo de lista no reconocido.</div>
                <div class="text-center mt-3">
                    <a class="btn btn-secondary" href="<%= request.getContextPath() %>">Volver</a>
                </div>
            </div>
        </div>
    <% } %>

    <footer class="text-center mt-5">
        <hr>
        <p class="small text-muted">Listado de <%= "autores".equals(tipo) ? "autores" : ("libros".equals(tipo) ? "libros" : "registros") %> • Servlets + JSP + JDBC</p>
    </footer>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
