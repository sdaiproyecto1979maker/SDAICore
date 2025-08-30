package sdai.com.sis.utilidades;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class Transform {

    public static String toString(Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value);
    }

    public static Integer toInteger(Object value) {
        try {
            String cadena = Transform.toString(value);
            return Integer.valueOf(cadena);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

}
