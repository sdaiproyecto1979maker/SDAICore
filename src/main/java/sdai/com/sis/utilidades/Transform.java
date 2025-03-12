package sdai.com.sis.utilidades;

/**
 * @date 12/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class Transform {

	public static String toString(Object value) {
		if (value == null)
			return "";
		String cadena = String.valueOf(value);
		return cadena;
	}

	public static Integer toInteger(Object value) {
		if (value == null)
			return Integer.valueOf(0);
		try {
			String cadena = Transform.toString(value);
			return Integer.parseInt(cadena);
		} catch (NumberFormatException ex) {
			return Integer.valueOf(0);
		}
	}

}
