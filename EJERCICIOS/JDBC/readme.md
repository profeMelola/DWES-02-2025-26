# Persistencia vía JDBC + Servlet +JSP

<img width="608" height="336" alt="image" src="https://github.com/user-attachments/assets/e9e78784-52c7-4846-9a03-074087ee0ac4" />


## H2

Muy usado en entornos Java para pruebas y desarrollo.

Soporta modo en memoria o en fichero.

Integra bien con frameworks Java (Spring, Hibernate, Jakarta EE).

Tiene consola web para ver y ejecutar queries (http://localhost:8082).

### Añadir dependencia en pom.xml

```
<!-- https://mvnrepository.com/artifact/com.h2database/h2 -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.3.232</version>
    <scope>test</scope>
</dependency>
```

