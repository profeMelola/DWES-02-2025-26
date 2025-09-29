# MI PRIMER APP JDBC

## Montar BD y tablas con H2 Console

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


## JDBC - Trabajando con la base de datos TIENDA. Práctica guiada: tienda CRUD DAO

<img width="767" height="657" alt="image" src="https://github.com/user-attachments/assets/7e1fa7a9-7a38-4213-beca-31984db79dc8" />

Vamos a realizar las cuatro operaciones básicas contra una tabla usando la base de datos tienda.

[ C ] Create: daremos de alta un producto indicando todos los campos necesarios (nombre, precio, fabricante).

[ R ] Read: obtendremos el listado de todos los productos.

[ U ] Update: actualizaremos el nombre de un producto.

[ D ] Delete: borraremos un producto por id.

### Configuración de la conexión a H2

1. Fichero JDBC.properties que estará en webapp del proyecto:
```
# Propiedad de conexión a base de datos
url=jdbc:h2:~/productosdb;AUTO_SERVER=TRUE
user=sa
password=
```
2. Indicar el driver de conexión adecuado: En la clase DBConnection del paquete package es.daw.jakarta.bd: 
```
Class.forName("org.h2.Driver");
```

### Servlets (Controllers)

Tendremos dos servlets o controladores:

#### 1. SERVLET ENCARGADO DE LISTAR LOS PRODUCTOS
  <img width="715" height="560" alt="image" src="https://github.com/user-attachments/assets/6d45b8b8-e305-4be6-9f3d-7b93cde07f6c" />

#### 2. SERVLET ENCARGADO DE OPERACIONES DE MODIFICACIÓN (INSERT, UPDATE, DELETE) DE PRODUCTOS
   <img width="820" height="500" alt="image" src="https://github.com/user-attachments/assets/b317d000-5e33-4248-8401-f511d6beba1c" />
