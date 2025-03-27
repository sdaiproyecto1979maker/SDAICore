package sdai.com.sis.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @date 12/03/2025
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

	public static String[] separarCadena(String cadena, Character caracter) {
		return Util.separarCadena(cadena, Transform.toString(caracter));
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
	
	public static Object[] createArray(Object[] lista1, Object[] lista2, @SuppressWarnings("rawtypes") Class type) {
		int size = lista1.length + lista2.length;
		Object[] destino = (Object[]) java.lang.reflect.Array.newInstance(type, size);
		System.arraycopy(lista1, 0, destino, 0, lista1.length);
		System.arraycopy(lista2, 0, destino, lista1.length, lista2.length);
		return destino;
	}

}
