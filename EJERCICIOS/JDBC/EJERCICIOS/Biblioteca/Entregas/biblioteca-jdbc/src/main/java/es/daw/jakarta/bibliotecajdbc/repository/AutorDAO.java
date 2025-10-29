package es.daw.jakarta.bibliotecajdbc.repository;

import es.daw.jakarta.bibliotecajdbc.model.Autor;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAO implements GenericDAO<Autor, BigInteger> {
    private Connection conn;
    public AutorDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }
    @Override
    public Optional<Autor> findByID(BigInteger id) throws SQLException{
        Optional<Autor> autor = Optional.empty();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM AUTOR WHERE ID = ?")) {
            ps.setInt(1, id.intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                autor = Optional.of(new Autor(
                        rs.getString("NOMBRE"),
                        BigInteger.valueOf(rs.getInt("ID"))
                ));
            }
            return autor;
        }
    }

    @Override
    public List<Autor> selectAll() throws SQLException {
        List<Autor> autores = new ArrayList<>();
    try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM AUTOR")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
        autores.add(new Autor(
            rs.getString("NOMBRE"),
            BigInteger.valueOf(rs.getInt("ID"))
        ));
            }
            return autores;
        }
    }

    @Override
    public void insert(Autor autor) throws SQLException {

    }

    @Override
    public void update(Autor autor) throws SQLException {
        if(autor.getId_autor() == null) {
            throw new SQLException("ID de autor nulo, no se puede actualizar");
        }
        try(PreparedStatement ps = conn.prepareStatement("UPDATE AUTOR SET NOMBRE = ? WHERE ID = ?")) {
            ps.setString(1, autor.getNombre());
            ps.setInt(2, autor.getId_autor().intValue());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM AUTOR WHERE ID = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
