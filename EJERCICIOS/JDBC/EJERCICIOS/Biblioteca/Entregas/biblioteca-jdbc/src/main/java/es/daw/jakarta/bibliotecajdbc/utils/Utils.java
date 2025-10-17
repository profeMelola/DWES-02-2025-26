package es.daw.jakarta.bibliotecajdbc.utils;


import es.daw.jakarta.bibliotecajdbc.model.Autor;

import java.math.BigInteger;
import java.util.List;

public class Utils {
    public static String obtenerNombreAutor(List<Autor> autores, BigInteger id){
        return autores.stream()
                .filter(a -> a.getId_autor().equals(id))
                .findFirst()
                .map(Autor::getNombre)
                .orElse("Desconocido");
    }
}