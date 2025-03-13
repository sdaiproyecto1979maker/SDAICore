package sdai.com.sis.utilidades;

import java.util.ArrayList;
import java.util.List;

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

	public static Character[] toCharacters(Object value) {
		List<Character> lista = new ArrayList<Character>();
		String cadena = Transform.toString(value);
		for (Integer i = Integer.valueOf(0); i < cadena.length(); i++) {
			Character caracter = cadena.charAt(i);
			lista.add(caracter);
		}
		return lista.toArray(new Character[0]);
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
