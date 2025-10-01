package es.daw.jakarta.jdbcapp.repository;

import es.daw.jakarta.jdbcapp.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAO implements GenericDAO<Producto,Integer>{
    private Connection conn;

    public ProductoDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }

    @Override
    public void save(Producto entity) throws SQLException {
        // Si queremos controlar que el código del nuevo producto (entity) no esté duplicado antes de insertar...
        // ???????? PA MAÑANA!!!!

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
    public Optional<Producto> findById(Integer id) throws SQLException {

//        String sql = "SELECT * FROM producto WHERE codigo = ?";
//
//        try(PreparedStatement ps = conn.prepareStatement(sql)){
//            ps.setInt(1,id);
//            ......
//        }
        return Optional.empty();

    }

    @Override
    public List<Producto> findAll() throws SQLException {
        //return List.of();

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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

    // PENDIENTE!!! Puedo crear los métodos que me de la gana como findByName...
}
