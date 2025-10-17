<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigInteger" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.model.Autor" %>
<%@ page import="es.daw.jakarta.bibliotecajdbc.model.Libro" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Formulario</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      body {
        background: linear-gradient(135deg, #f0f4f8, #e6ebef);
        font-family: "Segoe UI", sans-serif;
      }
      .form-card {
        border: none;
        border-radius: 1rem;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        margin-top: 3rem;
      }
      .form-title {
        color: #0d6efd;
        font-weight: 700;
        text-align: center;
        margin-bottom: 1.5rem;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-12 col-md-8 col-lg-6">
          <div class="card form-card">
            <div class="card-body">
              <% if(request.getAttribute("tipo").equals("autores")){%>
              <h2 class="form-title">Formulario Autor</h2>
              <form method="post" action="<%= request.getContextPath() %>/autores/crear">
                <div class="mb-3">
                  <label for="autorId" class="form-label">ID (solo lectura)</label>
                  <%
                      Autor autorEditar = (Autor) request.getAttribute("autorEditar");
                      String autorIdVal = (autorEditar != null && autorEditar.getId_autor()!=null) ? autorEditar.getId_autor().toString() : "";
                  %>
                  <input type="text" class="form-control" id="autorId" name="autorId" value="<%= autorIdVal %>" readonly />
                </div>
                <div class="mb-3">
                  <label for="nombre" class="form-label">Nombre</label>
                  <input type="text" class="form-control" id="nombre" name="nombre" value="<%= (autorEditar != null && autorEditar.getNombre()!=null) ? autorEditar.getNombre() : "" %>" required />
                </div>
                <div class="d-grid gap-2">
                  <button type="submit" class="btn btn-primary">Guardar</button>
                  <a
                    href="<%= request.getContextPath() %>/autores/listar"
                    class="btn btn-secondary"
                    >Cancelar</a
                  >
                </div>
              </form>
              <%} else if(request.getAttribute("tipo").equals("libros")){ %>
              <h2 class="form-title">Formulario Libro</h2>
              <form method="post" action="<%= request.getContextPath() %>/libros/crear">
                <div class="mb-3">
                  <label for="libroId" class="form-label">ID (solo lectura)</label>
                  <%
                      Libro libroEditar = (Libro) request.getAttribute("libroEditar");
                      String libroIdVal = (libroEditar != null && libroEditar.getId_libro()!=null) ? libroEditar.getId_libro().toString() : "";
                  %>
                  <input type="text" class="form-control" id="libroId" name="libroId" value="<%= libroIdVal %>" readonly />
                </div>
                <div class="mb-3">
                  <label for="titulo" class="form-label">Título</label>
                  <input type="text" class="form-control" id="titulo" name="titulo" value="<%= (libroEditar != null && libroEditar.getTitulo()!=null) ? libroEditar.getTitulo() : "" %>" required />
                </div>
                <div class="mb-3">
                  <label for="autor" class="form-label">Autor</label>
                  <select class="form-select" id="autor" name="autor" required>
                    <option value="">-- Seleccione --</option>
          <%
            List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores");
            BigInteger autorSel = (libroEditar != null) ? libroEditar.getId_autor() : null;
            if(listaAutores != null) {
              for(Autor a : listaAutores) {
          %>
            <option value="<%= a.getId_autor() %>" <%= (autorSel != null && autorSel.equals(a.getId_autor())) ? "selected" : "" %>><%= a.getNombre() %></option>
          <%
              }
            }
          %>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="fechaPublicacion" class="form-label"
                    >Fecha de Publicación</label
                  >
                  <%
                      String fechaVal = "";
                      if(libroEditar != null && libroEditar.getFechaPublicacion() != null) {
                          java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                          fechaVal = sdf.format(libroEditar.getFechaPublicacion());
                      }
                  %>
                  <input type="date" class="form-control" id="fechaPublicacion" name="fechaPublicacion" value="<%= fechaVal %>" />
                </div>
                <div class="d-grid gap-2">
                  <button type="submit" class="btn btn-primary">Guardar</button>
                  <a
                    href="<%= request.getContextPath() %>/libros/listar"
                    class="btn btn-secondary"
                    >Cancelar</a
                  >
                </div>
              </form>
              <%} else {%>
              <div class="alert alert-warning text-center">
                Tipo de formulario no reconocido.
              </div>
              <%}%>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
