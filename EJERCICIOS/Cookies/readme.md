# ¿Qué es una cookie?

<img width="933" height="255" alt="image" src="https://github.com/user-attachments/assets/ac759dcd-bf40-414c-b7e7-f5ee3fd3d652" />

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


## **Para crear una cookie y enviarla al cliente:**

```
// Crear una cookie
Cookie cookie = new Cookie("usuario", "nombreDeUsuario");
// Establecer tiempo de expiración en 7 días
cookie.setMaxAge(7 * 24 * 60 * 60); 
response.addCookie(cookie);

```

Si no se especifica el setMaxAge, la cookie será permanente.

Por defecto, el dominio de la cookie será el obtenido del contexto del servlet.

Para limitar que la cookie queda limitada a toda la app y no se comparte con otras apps: cookie.setPath(request.getContextPath());

<img width="250" height="300" alt="image" src="https://github.com/user-attachments/assets/6857a2c7-9201-4891-b0d8-9dcfb225833d" />


## **Para leer la cookie, el servlet recibiría todas las cookies en el HttpServletRequest:**

```
Cookie[] cookies = request.getCookies();
for (Cookie c : cookies) {
    if (c.getName().equals("usuario")) {
        String nombreUsuario = c.getValue();
        // Realizar alguna acción con el valor de la cookie
    }
}

```
