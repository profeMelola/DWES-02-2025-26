# Ejemplo: login de usuario con JakartaEE

## Arquitectura 3 capas a nivel lógico.

Esta arquitectura divide la aplicación en diferentes capas físicas o lógicas, donde cada capa tiene una responsabilidad específica. 

Las capas típicas incluyen la capa de presentación, la capa de lógica de negocio y la capa de acceso a datos. 

Cada capa se comunica con la capa adyacente a través de interfaces bien definidas. 

**Separación en las capas a nivel lógico:**
- **Presentación:** JSP
- **Lógica de negocio:** Servlet -> lógica
- **Datos:** Clase DB -> acceso a datos

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

## Arquitectura 3 capas a nivel físico.


___

## Patrón MVC con JSP + Servlets

<img width="620" height="622" alt="image" src="https://github.com/user-attachments/assets/399e4c86-8b7d-463c-bca1-21a10f088003" />

No es una arquitectura, es un patrón de diseño del backend.

- **Modelo (Usuario):** solo representa los datos del negocio.
  - **DAO (UsuarioDAO + UsuarioDAOImpl):** define y encapsula el acceso a datos (puedes cambiar JDBC por JPA sin que el resto cambie).
- **Controlador (LoginController):** recibe la petición, crea el modelo, llama al DAO y decide la vista.
- **Vista (JSPs):** solo muestra información, sin lógica.

### 1. Modelo 

#### 1.1. Entidad de negocio
```
// Usuario.java
public class Usuario {
    private String nombre;
    private String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    public String getNombre() { return nombre; }
    public String getPassword() { return password; }
}

```
#### 1.2. Interfaz DAO
```
// UsuarioDAO.java
public interface UsuarioDAO {
    boolean validar(Usuario usuario);
}
```

#### 1.3. Implementación DAO (persistencia concreta con JDBC)
```
// UsuarioDAOImpl.java
public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public boolean validar(Usuario usuario) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tienda", "root", "")) {
            
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM usuarios WHERE nombre=? AND pass=?");
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getPassword());
            
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
    private UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String nombre = request.getParameter("user");
        String pass = request.getParameter("pass");

        Usuario usuario = new Usuario(nombre, pass);

        if (usuarioDAO.validar(usuario)) {
            request.setAttribute("usuario", usuario.getNombre());
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

