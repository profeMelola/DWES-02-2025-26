# Ciclo de vida: método init de un servlet

<img width="633" height="332" alt="image" src="https://github.com/user-attachments/assets/2e04f4a8-e069-4186-a491-9b06ddea5f20" />

---

Vamos a crear un contador que se incremente cada vez que el Servlet recibe una petición Http.


<img width="191" height="105" alt="image" src="https://github.com/user-attachments/assets/1ba0a865-2782-4ae0-a935-70552bfdcfe7" />

## Contador global

En un Servlet, cada petición HTTP puede ser atendida por un hilo distinto al mismo tiempo.
Si intentas llevar un contador global con una variable int o Integer, podrían pasar cosas así:
- Hilo A lee el valor actual (digamos 5).
- Hilo B lee el mismo valor (5) antes de que A lo actualice.
- Ambos incrementan y escriben, el valor termina en 6 en lugar de 7. (condición de carrera).

Si queremos llevar un contador global de todas las solicitudes hay que tener cuidado con accesos concurrentes porque doGet puede ser llamado por múltiples hilos al mismo tiempo.

**AtomicInteger** evita problemas de concurrencia.

```
private AtomicInteger peticiones = new AtomicInteger(5);

int valorActual = peticiones.incrementAndGet();

```

Si varios hilos llaman al método a la vez, no se pierde ningún incremento: si estaba en 5 y llegan 3 peticiones simultáneamente, terminará en 8 siempre

## Contador por usuario

### Usar la sesión HTTP (HttpSession)

Cada usuario tiene su propia sesión, que vive mientras el usuario esté navegando.

Podrías guardar el contador dentro de la sesión:

```
HttpSession session = request.getSession();
AtomicInteger contador = (AtomicInteger) session.getAttribute("contador");

if (contador == null) {
    contador = new AtomicInteger(0);
    session.setAttribute("contador", contador);
}

int valor = contador.incrementAndGet();
response.getWriter().println("Contador de este usuario: " + valor);

```
