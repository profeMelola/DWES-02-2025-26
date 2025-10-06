# Chuleta de Interfaces Funcionales y Lambdas en Java

## Qué es una interfaz funcional

Una **interfaz funcional** tiene **un único método abstracto**.  

Se puede implementar con **expresiones lambda** o **referencias a métodos**.

```
@FunctionalInterface
interface Operacion {
    int aplicar(int a, int b);
}
```

## Principales interface funcionales de java.util.function

| Interface             | Método principal      | Parámetros | Devuelve        | Ejemplo                      | Uso típico                       |
| --------------------- | --------------------- | ---------- | --------------- | ---------------------------- | -------------------------------- |
| **Predicate<T>**      | `boolean test(T t)`   | 1          | `boolean`       | `p -> p.getPrecio() > 100`   | Filtrar con `filter()`           |
| **Function<T,R>**     | `R apply(T t)`        | 1          | otro tipo (`R`) | `p -> p.getNombre()`         | Transformar con `map()`          |
| **Consumer<T>**       | `void accept(T t)`    | 1          | nada            | `p -> System.out.println(p)` | Ejecutar acción (`forEach()`)    |
| **Supplier<T>**       | `T get()`             | 0          | `T`             | `() -> new Producto()`       | Proveer valores (sin entrada)    |
| **UnaryOperator<T>**  | `T apply(T t)`        | 1          | mismo tipo      | `x -> x * 2`                 | Modificar valores del mismo tipo |
| **BinaryOperator<T>** | `T apply(T t1, T t2)` | 2          | mismo tipo      | `(a,b) -> a + b`             | Combinar valores (`reduce()`)    |


## Ejemplo completo con Streams

```
productos.stream()
    .filter(p -> p.getPrecio() > 100)       // Predicate
    .map(Producto::getNombre)               // Function
    .forEach(System.out::println);          // Consumer

```

## Resumen

| Tipo                | Función     | Método     | Ejemplo común       |
| ------------------- | ----------- | ---------- | ------------------- |
| `Predicate<T>`      | Filtrar     | `test()`   | `filter()`          |
| `Function<T,R>`     | Transformar | `apply()`  | `map()`             |
| `Consumer<T>`       | Acción      | `accept()` | `forEach()`         |
| `Supplier<T>`       | Proveer     | `get()`    | `Stream.generate()` |
| `UnaryOperator<T>`  | Modificar   | `apply()`  | `map()`             |
| `BinaryOperator<T>` | Reducir     | `apply()`  | `reduce()`          |

---

## Composición de Predicados

La interfaz `Predicate<T>` permite **combinar condiciones** de forma legible usando métodos por defecto:

| Método | Descripción | Ejemplo |
|---------|--------------|----------|
| `and()` | Verdadero si **ambos** predicados son verdaderos | `p -> p.getPrecio() > 100 && p.getPrecio() < 500` <br> → `precioMayor100.and(precioMenor500)` |
| `or()` | Verdadero si **alguno** es verdadero | `esPortatil.or(esTablet)` |
| `negate()` | Invierte el resultado (NO lógico) | `esCaro.negate()` |

---

### Ejemplo práctico

```java
Predicate<Producto> precioMayor100 = p -> p.getPrecio() > 100;
Predicate<Producto> precioMenor500 = p -> p.getPrecio() < 500;
Predicate<Producto> esBarato = precioMenor500.and(precioMayor100.negate());

productos.stream()
    .filter(precioMayor100.and(precioMenor500)) // entre 100 y 500
    .forEach(p -> System.out.println(p.getNombre()));
