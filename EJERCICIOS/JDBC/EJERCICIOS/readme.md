# MI PRIMER APP JDBC

## Montar BD y tablas con H2 Console

```
java -cp h2-2.2.x.jar org.h2.tools.Server
```

El .jar lo tienes en ~/.m2/repository/com/h2database/h2/... si usas Maven, o donde lo hayas descargado.

Abre en navegador http://localhost:8082

Conecta usando:

- JDBC URL: jdbc:h2:~/productosdb
- User: sa
- Password: (vacío)
- Copia y pega tu script SQL y ejecútalo (productos.sql).

## Montar entorno

```
private static final String JDBC_URL = "jdbc:h2:~/productosdb;AUTO_SERVER=TRUE";
private static final String JDBC_USER = "sa";
private static final String JDBC_PASS = "";

```

~/productosdb → crea un fichero productosdb.mv.db en tu carpeta de usuario.

AUTO_SERVER=TRUE → permite que distintos procesos (ej. Tomcat y H2 console) accedan al mismo fichero.
