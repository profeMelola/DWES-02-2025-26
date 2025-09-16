# Ciclo de vida: método init de un servlet

<img width="633" height="332" alt="image" src="https://github.com/user-attachments/assets/2e04f4a8-e069-4186-a491-9b06ddea5f20" />

---

Vamos a crear un contador que se incremente cada vez que el Servlet recibe una petición Http.


<img width="191" height="105" alt="image" src="https://github.com/user-attachments/assets/1ba0a865-2782-4ae0-a935-70552bfdcfe7" />

## Contador global

Si queremos llevar un contador global de todas las solicitudes hay que tener cuidado con accesos concurrentes porque doGet puede ser llamado por múltiples hilos al mismo tiempo.

**AtomicInteger** evita problemas de concurrencia.

```
private AtomicInteger peticiones = new AtomicInteger(5);

int valorActual = peticiones.incrementAndGet();

```




