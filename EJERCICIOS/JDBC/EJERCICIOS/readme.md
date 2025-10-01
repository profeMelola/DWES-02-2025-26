# MI PRIMER APP JDBC

## Añadir dependencia H2 en pom.xml

Vamos a usar H2 como base de datos relacional.

```
<!-- https://mvnrepository.com/artifact/com.h2database/h2 -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>2.3.232</version>
</dependency>
```

## Estructura del proyecto

Nuestro proyecto tendrá una estrucutra como la siguiente:

![alt text](image.png)


## H2 en memoria

La base de datos se va a crear en memoria cuando despleguemos nuestro proyecto. No existirá físicamente.

En el directorio **resources** de nuestro proyecto crearemos el archivo **JDBC.properties**:

```
# Propiedad de conexión a base de datos
#url=jdbc:h2:~/productos;AUTO_SERVER=TRUE
url=jdbc:h2:mem:productos;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:schema.sql'
user=sa
password=

```
- mem: → le indicas a H2 que cree la BD en memoria.
- DB_CLOSE_DELAY=-1 → hace que la BD no se borre en cuanto se cierre la última conexión (muy útil en web apps).
- RUNSCRIPT FROM 'classpath:schema.sql' → ejecuta automáticamente un script SQL al levantar la conexión.
- Ese schema.sql (o import.sql) debe estar en tu classpath (por ejemplo en src/main/resources).

## Montar BD y tablas con H2 Console (si no trabajamos en memoria...)

```
java -cp h2-2.2.x.jar org.h2.tools.Server
```

El .jar lo tienes en ~/.m2/repository/com/h2database/h2/... si usas Maven, o donde lo hayas descargado.

Abre en navegador http://localhost:8082

Conecta usando:

- JDBC URL: jdbc:h2:~/productosdb;AUTO_SERVER=TRUE
- User: sa
- Password: (vacío)
- Copia y pega tu script SQL y ejecútalo (productos.sql).

Si todo va bien, deberías tener dos tablas: fabricante y producto con los inserts cargados.

El fichero productosdb.mv.db estará en tu carpeta de usuario (ej. C:\Users\tuUsuario\productosdb.mv.db o /home/tuUsuario/productosdb.mv.db).

AUTO_SERVER=TRUE → permite que distintos procesos (ej. Tomcat y H2 console) accedan al mismo fichero.

El archivo JDBC.properties contendría:


```
# Propiedad de conexión a base de datos
url=jdbc:h2:~/productosdb;AUTO_SERVER=TRUE
user=sa
password=
```


## JDBC - Trabajando con la base de datos PRODUCTOS. Práctica guiada: tienda CRUD DAO

<img width="767" height="657" alt="image" src="https://github.com/user-attachments/assets/7e1fa7a9-7a38-4213-beca-31984db79dc8" />

Vamos a realizar las cuatro operaciones básicas contra una tabla usando la base de datos tienda.

[ C ] Create: daremos de alta un producto indicando todos los campos necesarios (nombre, precio, fabricante).

[ R ] Read: obtendremos el listado de todos los productos.

[ U ] Update: actualizaremos el nombre de un producto.

[ D ] Delete: borraremos un producto por id.

### Configuración de la conexión a H2

Usa la clase **DBConnection** que se proporciona.


### Servlets (Controllers)

Tendremos dos servlets o controladores:

#### 1. SERVLET ENCARGADO DE LISTAR LOS PRODUCTOS

![alt text](image-1.png)

#### 2. SERVLET ENCARGADO DE OPERACIONES DE MODIFICACIÓN (INSERT, UPDATE, DELETE) DE PRODUCTOS

   <img width="410" height="250" alt="image" src="https://github.com/user-attachments/assets/b317d000-5e33-4248-8401-f511d6beba1c" />


### Vistas (JSP)

Utiliza las JSP que se proporcionan:
- index.jsp
- informe.jsp
- error.jsp

# MEJORAS

## Mostrar el nombre de fabricante, no el código
## FILTRAR PRODUCTOS POR NOMBRE
## USAR EL FILTRO POR ID. SI NO EXISTE CÓMO TRATARLO!!!
## HACER EL CRUD DE FABRICANTE
## EN INDEX.JSP QUE SALGA LA LISTA DE FABRICANTES DINÁMICAMENTE. OBTENIDAS DE LA BD
