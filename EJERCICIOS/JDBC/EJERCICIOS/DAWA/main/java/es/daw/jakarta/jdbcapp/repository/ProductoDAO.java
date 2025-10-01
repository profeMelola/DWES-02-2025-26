package es.daw.jakarta.jdbcapp.repository;

import es.daw.jakarta.jdbcapp.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAO implements GenericDAO<Producto, Integer>{
    private Connection conn;

    public ProductoDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void save(Producto entity) throws SQLException {

        // Si queremos controlar que el código del nuevo producto (entity) no esté duplicado antes de insertar...
        // Forma 1: Cargo todos los productos y busco si hay un producto con el identificador...
        // Forma 2: Uso el método findById. Si no encuentra producto, es que no existe y por tanto puedo usar el código. Si devuelve una
        // fila, el producto existe y redirijo a error.jsp con el mensaje de que el código está duplicado.
        // Forma 3: no controlo, me dará un sqlexception de primary key duplated!!! lo gestiono y devuelvo error.jsp (ASÍ LO HACEMOS AHORA)
        String sql = "insert into producto (codigo,nombre, precio,codigo_fabricante) values (?,?,?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, entity.getCodigo());
            ps.setString(2, entity.getNombre());
            ps.setBigDecimal(3, entity.getPrecio());
            ps.setInt(4, entity.getCodigo_fabricante());

            ps.executeUpdate();
        }



    }

    @Override
    public Optional<Producto> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        //return List.of();
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        //String sql = "SELECT * FROM producto WHERE codigo = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            //ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                productos.add(
                       new Producto(
                               rs.getInt("codigo"),
                               rs.getString("nombre"),
                               rs.getBigDecimal("precio"),
                               rs.getInt("codigo_fabricante")
                       )
                );
            }
        }
        return productos;
    }

    @Override
    public void update(Producto entity) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
