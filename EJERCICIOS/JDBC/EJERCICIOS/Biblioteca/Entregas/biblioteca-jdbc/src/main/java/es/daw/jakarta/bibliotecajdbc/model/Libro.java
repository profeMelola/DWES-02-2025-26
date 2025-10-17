package es.daw.jakarta.bibliotecajdbc.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class Libro {
    private String titulo;
    private BigInteger id_autor;
    private Date fechaPublicacion;
    private BigInteger id_libro;

    public Libro(String titulo, BigInteger id_autor, Date fechaPublicacion, BigInteger id_libro) {
        this.titulo = titulo;
        this.id_autor = id_autor;
        this.fechaPublicacion = fechaPublicacion;
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigInteger getId_autor() {
        return id_autor;
    }

    public void setId_autor(BigInteger id_autor) {
        this.id_autor = id_autor;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public BigInteger getId_libro() {
        return id_libro;
    }
    public void setId_libro(BigInteger id_libro) {
        this.id_libro = id_libro;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(titulo, libro.titulo) && Objects.equals(id_autor, libro.id_autor) && Objects.equals(fechaPublicacion, libro.fechaPublicacion) && Objects.equals(id_libro, libro.id_libro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, id_autor, fechaPublicacion);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", id_autor=" + id_autor +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
