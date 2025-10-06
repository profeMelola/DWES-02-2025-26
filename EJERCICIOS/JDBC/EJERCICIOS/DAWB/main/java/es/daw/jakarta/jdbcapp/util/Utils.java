package es.daw.jakarta.jdbcapp.util;

import es.daw.jakarta.jdbcapp.model.Fabricante;

import java.util.List;

public class Utils {

    public static String obtenerNombreFabricante(List<Fabricante> fabricantes, Integer codigo){
        // FORMA 1: IMPERATIVO
//        String nombreFabricante = "";
//        for (Fabricante fabricante : fabricantes) {
//            if (fabricante.getCodigo().equals(codigo)) {
//                return fabricante.getNombre();
//            }
//        }
//        return "Desconocido";

        // FORMA 2: STREAM() + programación funcional
        return fabricantes.stream()
                .filter(fabricante -> fabricante.getCodigo().equals(codigo))
                .findFirst()
                //.map(f -> f.getNombre())
                .map(Fabricante::getNombre)
                //.get();
                .orElse("Desconocido");

    }
}
