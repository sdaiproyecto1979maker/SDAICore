package sdai.com.sis.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @date 09/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class Util {

	public static Boolean isCadenaVacia(String cadena) {
		if (cadena == null || cadena.length() == 0)
			return Boolean.valueOf(true);
		Character[] caracteres = Transform.toCharacters(cadena);
		for (Character caracter : caracteres)
			if (caracter != ' ')
				return Boolean.valueOf(false);
		return Boolean.valueOf(true);
	}

	public static Boolean isCadenaNoVacia(String cadena) {
		return !Util.isCadenaVacia(cadena);
	}

	public static String[] separarCadena(String cadena, Character delimitador) {
		String cadenaDelimitador = Transform.toString(delimitador);
		return Util.separarCadena(cadena, cadenaDelimitador);
	}

	public static String[] separarCadena(String cadena, String delimitador) {
		List<String> lista = new ArrayList<String>();
		StringTokenizer stringTokenizer = new StringTokenizer(cadena, delimitador);
		while (stringTokenizer.hasMoreElements()) {
			String token = stringTokenizer.nextToken();
			lista.add(token);
		}
		return lista.toArray(new String[0]);
	}

}
