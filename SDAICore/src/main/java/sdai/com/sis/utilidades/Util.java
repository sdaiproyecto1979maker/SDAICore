package sdai.com.sis.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class Util {

    public static Boolean isCadenaVacia(String cadena) {
        if (cadena == null || cadena.length() == 0) {
            return Boolean.TRUE;
        }
        char[] caracteres = cadena.toCharArray();
        for (char caracter : caracteres) {
            if (caracter != ' ') {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean isCadenaNoVacia(String cadena) {
        return !Util.isCadenaVacia(cadena);
    }

    public static String[] separarEnPalabras(String cadena, String delimitador) {
        List<String> lista = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(cadena, delimitador);
        while (stringTokenizer.hasMoreElements()) {
            lista.add(stringTokenizer.nextToken());
        }
        return lista.toArray(String[]::new);
    }

}
