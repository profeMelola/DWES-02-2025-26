package es.daw.jakarta.bibliotecajdbc.model;

import java.math.BigInteger;
import java.util.Objects;

public class Autor {

    private String nombre;
    private BigInteger id_autor;

    public Autor(String nombre, BigInteger id_autor) {
        this.nombre = nombre;
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public BigInteger getId_autor() {
        return id_autor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_autor(BigInteger id_autor) {
        this.id_autor = id_autor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre) && Objects.equals(id_autor, autor.id_autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id_autor);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", id_autor=" + id_autor + '}';
    }
}
