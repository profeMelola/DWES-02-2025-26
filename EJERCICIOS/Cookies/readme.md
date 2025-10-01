# ¿Qué es una cookie?

<img width="466" height="128" alt="image" src="https://github.com/user-attachments/assets/ac759dcd-bf40-414c-b7e7-f5ee3fd3d652" />

Una cookie son datos del usuario almacenados en el navegador web (lado del cliente) y los servidores la utilizan cuando se comunican con el cliente.

Una cookie es un fichero de datos que una página web le envía a tu ordenador cuando la visitas.

Da igual si estás entrando a la web desde el ordenador o desde el móvil, siempre se solicitará el almacenamiento de la cookie.

Tampoco importa si entras desde un navegador independiente o desde el navegador integrado en alguna herramienta o aplicación, también se solicitará la cookie.

La solicitud de almacenamiento del fichero de información en tu ordenador la hará directamente el servidor de la web a la que entras en el mismo momento en el que accedes a ella.

Por lo general, notarás que se está solicitando la utilización de cookies porque las webs están obligadas a avisarte y a preguntarte cuáles quieres instalar por la GDPR, la normativa que regula la protección de los datos de los ciudadanos que vivan en la Unión Europea.

## Manejo de estados

Una de las características de las peticiones http es que no manejan estado de los datos del request, por eso es la importancia del manejo de sesiones o cookies  HTTP.

Las cookies nos permiten un forma para mantener información del usuario entre peticiones y poder recordarlas.

Existen dos formas de mantener información del usuario, una es usando cookies y otra el objeto HttpSession del API servlet.

## Por qué se usan cookies y los tipos que hay

Las webs actuales funcionan utilizando estos elementos, que también pueden utilizarse para que las empresas publicitarias te espíen para saber tus gustos y venderte mejor publicidad.

Las cookies hacen que las páginas web puedan identificar tu ordenador, y por lo tanto, si vuelves a entrar a ellas podrán recordar quién eres y qué has hecho antes dentro de ellas.

Las cookies suelen utilizarse principalmente para dos finalidades:

- **Recordar accesos:** si no existieran, cada vez que entras en una página tendrías que iniciar sesión en ella. Gracias a las cookies.
  - La página web podrá recordar que eres tú, y por lo tanto podrá permitirte seguir en el perfil con el que iniciases sesión sin tener que volver a escribir tus credenciales.
  - Si colocas muchos archivos en tu cesta de la compra sin tener una cuenta, pero luego te vas, si vuelves a visitar la página muy posiblemente todavía podrá recordar lo que tenías en la cesta de la compra para que no tengas que volver a meterlo.
  - También ayudan a recordar otros datos como que estás buscando vuelos a Los Ángeles o que tu divisa preferida es el euro.

- **Conocer hábitos de navegación:** es la más conflictiva, y precisamente la que ha hecho que tengan tan mala fama.
    - Pueden utilizarlas terceros para enviarte información relacionada a tus intereses.
    - Pueden identificarte como usuario según las páginas que visitas.
    - Pueden saber en qué páginas entras, y por lo tanto, crear un perfil de tus gustos personales.
    - También pueden registrar tus búsquedas en los buscadores.
    - Puedes deshabilitar socios comerciales, que son precisamente las empresas de publicidad.
  
## Tipos:

- Temporales y permanentes.
- Propias o de terceros.
- Técnicas o necesarias.
- De preferencias o de personalización.
- De rendimiento y análisis.
- Publicitarias o de marketing.

# Conceptos fundamentales de Cookies con Java

- Una cookie es un pequeño archivo de texto que el servidor web crea y envía al navegador del cliente.
- Ese archivo contiene pares clave=valor (por ejemplo: usuario=juan), y puede incluir además metadatos como:
  - fecha de caducidad
  - dominio
  - ruta a la que aplica
  - si es segura (HTTPS)
  - si es solo accesible desde el servidor (HttpOnly)

## Ciclo de vida de una cookie

1. Creación en el servidor
2. Envío al navegador: esto añade una cabecera Set-cooke en la respuesta HTTP.
3. Reenvío automático en cada petición: cada vez que el navegador haga una petición al mismo servidor y dentro del dominio/ruta, incluirá en la cabecera HTTP *Cookie: nombreCookie=valorCookie*
4. Lecutra en el servidor
   
## **Para crear una cookie y enviarla al cliente:**

```
// Crear una cookie
Cookie cookie = new Cookie("usuario", "nombreDeUsuario");
// Establecer tiempo de expiración en 7 días
cookie.setMaxAge(7 * 24 * 60 * 60); 
response.addCookie(cookie);

```

Si no se especifica el setMaxAge, la cookie será permanente.

Esto añade una cabecera Set-cooke en la respuesta HTTP.

Por defecto, el dominio de la cookie será el obtenido del contexto del servlet.

Para limitar que la cookie queda limitada a toda la app y no se comparte con otras apps: cookie.setPath(request.getContextPath());

<img width="250" height="300" alt="image" src="https://github.com/user-attachments/assets/6857a2c7-9201-4891-b0d8-9dcfb225833d" />


## **Para leer la cookie, el servlet recibiría todas las cookies en el HttpServletRequest:**

```
Cookie[] cookies = request.getCookies();
if (cookies != null){
  for (Cookie c : cookies) {
      if (c.getName().equals("usuario")) {
          String nombreUsuario = c.getValue();
          // Realizar alguna acción con el valor de la cookie
          // out.println("Bienvenido de nuevo " + c.getValue());
      }
  }
}

```

<img width="651" height="446" alt="image" src="https://github.com/user-attachments/assets/2ef74556-46c6-4925-955f-d73626515b3b" />

## **Para borrar una cookie**

```
Cookie cookie = new Cookie("usuario", "");
cookie.setMaxAge(0); // Se borra inmediatamente
cookie.setPath("/"); // Debe coincidir con el path de la cookie original
response.addCookie(cookie);

```
- Crear una nueva cookie con el mismo nombre que la cookie que quieres borrar.
- Asignarle un maxAge igual a 0 (cero segundos de vida).
- (Opcional) Asegurarte de que el path es el mismo que tenía la cookie original.
- Enviarla al navegador con response.addCookie(cookie).

# EJERCICIO: recordar color de fondo con cookies

Crea y implementa un proyecto que:

1. Muestre un formulario para elegir un color de fondo de la página principal.
2. Guarde la elección en una cookie enviada desde el servidor al navegador.
3. En visitas posteriores, la página recuerde el color y aplique ese fondo automáticamente a la página principal sin volver a pedirlo.
4. Permitir cambiar el color elegido y/o borrar la cookie.

```
src/
 └─ main/
     ├─ java/
     │   └─ es.daw.cookies/
     │        └─ PreferenciasColorServlet.java
     └─ webapp/
         ├─ index.jsp        (bienvenida y pregunta si quiere cambiar el color de fondo)
         └─ color.jsp        (formulario para cambiar el color)

```

## Pasos

### 1.
<img width="235" height="168" alt="image" src="https://github.com/user-attachments/assets/79e1a79d-2692-4c10-b718-423a33eb0571" />

### 2.
<img width="298" height="331" alt="image" src="https://github.com/user-attachments/assets/f44e1ca2-0a4b-4da8-aff0-b16de294dfe8" />

### 3.
<img width="313" height="147" alt="image" src="https://github.com/user-attachments/assets/28549e04-d5a1-4f90-b3c0-3bf55410a248" />

### 4.
<img width="240" height="188" alt="image" src="https://github.com/user-attachments/assets/3489cb78-c9a6-4611-876e-54137f00d2b2" />



## JSESSIONID

Esa cookie **JSESSIONID** no la estás creando tú, la crea automáticamente el contenedor web (Tomcat, WildFly, etc.) para gestionar las sesiones HTTP.

<img width="1309" height="692" alt="image" src="https://github.com/user-attachments/assets/939f6db3-5bd0-4fa0-ad90-a1d63878a28f" />

- Es un identificador único que el servidor asigna a cada cliente cuando empieza una sesión.
- Permite que el servidor sepa que varias peticiones HTTP vienen del mismo usuario/navegador (porque HTTP por sí solo es un protocolo sin estado).
- Se guarda en el navegador como una cookie y en el servidor se asocia a un objeto HttpSession.
