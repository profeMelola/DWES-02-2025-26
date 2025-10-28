# Filtrar el listado por autor y/o por rango de años (ej.: 1960–1970).

## Opción 1. En la vista usar inputs type number

**En la vista JSP:**

```
<label>Año desde: <input type="number" name="yearFrom" min="0" max="2100"></label>
<label>hasta: <input type="number" name="yearTo" min="0" max="2100"></label>

```

Te devuelve directamente el año como número (request.getParameter("yearFrom") → "1960").

Fácil de validar en el servidor, pero el usuario puede escribir cualquier número (no fuerza un año real, aunque puedes limitar con min/max).

## Opción 2. En la vista usar inputs type date

**En la vista:**

```
<label>Desde: <input type="date" name="fromDate"></label>
<label>Hasta: <input type="date" name="toDate"></label>

```

Trabajamos con fechas completas.

**Clase de utilidades:**

```
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    /**
     * Parsea una cadena con formato ISO yyyy-MM-dd a LocalDate.
     * Devuelve null si la cadena es nula, vacía o inválida.
     */
    public static LocalDate parseIsoDate(String s) {
        if (s == null || s.isBlank()) return null;
        try {
            return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Convierte un LocalDate a texto ISO yyyy-MM-dd (para precargar en formularios).
     */
    public static String formatIsoDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ISO_LOCAL_DATE) : "";
    }
}

```

**En el servlet:**

```
import java.time.LocalDate;
import com.example.library.util.Utils;

// ...

LocalDate fromDate = Utils.parseIsoDate(request.getParameter("fromDate"));
LocalDate toDate   = Utils.parseIsoDate(request.getParameter("toDate"));

Integer fromYear = (fromDate != null) ? fromDate.getYear() : null;
Integer toYear   = (toDate != null)   ? toDate.getYear()   : null;

List<Book> books = bookDao.findAll();

if (fromYear != null) {
    books = books.stream()
            .filter(b -> b.getPublicationDate() != null &&
                         b.getPublicationDate().getYear() >= fromYear)
            .toList();
}

if (toYear != null) {
    books = books.stream()
            .filter(b -> b.getPublicationDate() != null &&
                         b.getPublicationDate().getYear() <= toYear)
            .toList();
}


```

## Opción 3. En la vista con un select

**En la vista:**

```
<form method="get" action="/books">
  <label>Desde:
    <select name="yearFrom">
      <option value="">--Desde--</option>
      <option value="1950">1950</option>
      <option value="1960">1960</option>
      <option value="1970">1970</option>
      <option value="1980">1980</option>
      <option value="1990">1990</option>
      <option value="2000">2000</option>
      <option value="2010">2010</option>
      <option value="2020">2020</option>
    </select>
  </label>

  <label>Hasta:
    <select name="yearTo">
      <option value="">--Hasta--</option>
      <option value="1950">1950</option>
      <option value="1960">1960</option>
      <option value="1970">1970</option>
      <option value="1980">1980</option>
      <option value="1990">1990</option>
      <option value="2000">2000</option>
      <option value="2010">2010</option>
      <option value="2020">2020</option>
    </select>
  </label>

  <button type="submit">Filtrar</button>
</form>

```

**En el servlet:**

```
String yearFromParam = request.getParameter("yearFrom");
String yearToParam   = request.getParameter("yearTo");

Integer yearFrom = (yearFromParam != null && !yearFromParam.isBlank())
        ? Integer.parseInt(yearFromParam)
        : null;

Integer yearTo = (yearToParam != null && !yearToParam.isBlank())
        ? Integer.parseInt(yearToParam)
        : null;


List<Book> books = bookDao.findAll();

if (yearFrom != null) {
    books = books.stream()
            .filter(b -> b.getPublicationDate() != null &&
                         b.getPublicationDate().getYear() >= yearFrom)
            .toList();
}

if (yearTo != null) {
    books = books.stream()
            .filter(b -> b.getPublicationDate() != null &&
                         b.getPublicationDate().getYear() <= yearTo)
            .toList();
}

```