# Ejemplo: login de usuario con JakartaEE

## Arquitectura 3 capas

Separación en las capas:
- Presentación: JSP
- Lógica de negocio: Servlet -> lógica
- Datos: Clase DB -> acceso a datos

### 1. Presentación (JSP)
```
<!-- login.jsp -->
<form action="LoginServlet" method="post">
  Usuario: <input type="text" name="user"><br>
  Contraseña: <input type="password" name="pass"><br>
  <input type="submit" value="Entrar">
</form>
```

### 2. Lógica de negocio (Servlet)
```
// LoginServlet.java
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (DBUtils.checkUser(user, pass)) {
            response.getWriter().println("Bienvenido " + user);
        } else {
            response.getWriter().println("Error en usuario o contraseña");
        }
    }
}
```

### 3. Capa de datos (clase utilitaria)
```
// DBUtils.java
public class DBUtils {
    public static boolean checkUser(String user, String pass) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tienda", "root", "")) {
            
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM usuarios WHERE nombre=? AND pass=?");
            stmt.setString(1, user);
            stmt.setString(2, pass);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

```

___

## Patrón MVC con JSP + Servlets

Se afina la separación interna:
- Modelo = UsuarioDAO (gestiona datos)
- Controlador = LoginController (decide qué vista mostrar)
- Vista = JSP (bienvenido.jsp, error.jsp)

### 1. Modelo (gestiona datos y lógica de BD)
```
// UsuarioDAO.java
public class UsuarioDAO {
    public static boolean validar(String user, String pass) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tienda", "root", "")) {
            
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM usuarios WHERE nombre=? AND pass=?");
            stmt.setString(1, user);
            stmt.setString(2, pass);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
```

### 2. Controlador (Servlet que coordina)
```
// LoginController.java
@WebServlet("/login")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        if (UsuarioDAO.validar(user, pass)) {
            request.setAttribute("usuario", user);
            request.getRequestDispatcher("bienvenido.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

```

### 3. Vista (JSP)
```
<!-- bienvenido.jsp -->
<h1>Bienvenido ${usuario}</h1>

<!-- error.jsp -->
<p>Usuario o contraseña incorrectos</p>

```

