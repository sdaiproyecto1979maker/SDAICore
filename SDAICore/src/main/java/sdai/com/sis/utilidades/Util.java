package sdai.com.sis.utilidades;

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

}
