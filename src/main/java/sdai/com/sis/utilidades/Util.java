package sdai.com.sis.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
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

	@SuppressWarnings("unchecked")
	public static <T> T[] createArrayTypes(T[] lista1, T[] lista2, Class<T> type) {
		return (T[]) createArray(lista1, lista2, type);
	}

	public static Object[] createArray(Object[] lista1, Object[] lista2, Class<?> type) {
		Integer size = lista1.length + lista2.length;
		Object[] destino = (Object[]) java.lang.reflect.Array.newInstance(type, size);
		System.arraycopy(lista1, 0, destino, 0, lista1.length);
		System.arraycopy(lista2, 0, destino, lista1.length, lista2.length);
		return destino;
	}

	public static Object[] addItemArray(Object[] lista1, Object nuevoItem) {
		Integer size = lista1 == null ? 1 : lista1.length + 1;
		Object[] destino = (Object[]) java.lang.reflect.Array.newInstance(nuevoItem.getClass(), size);
		if (lista1 != null)
			System.arraycopy(lista1, 0, destino, 0, lista1.length);
		destino[size - 1] = nuevoItem;
		return destino;
	}

	public static IOrdenacion[] ordenar(IOrdenacion[] objetos, Integer tipoDOrdenacion) {
		if ((objetos.length == 0) || (objetos.length == 1))
			return objetos;
		Util.quickSort(objetos, 0, objetos.length - 1, tipoDOrdenacion);
		return objetos;

	}

	private static void quickSort(IOrdenacion[] objetos, Integer izquierda, Integer derecha, Integer tipoDOrdenacion) {
		Integer i = izquierda;
		Integer j = derecha;
		IOrdenacion mitad = objetos[(izquierda + derecha) / Integer.valueOf(2)];
		do {
			while (objetos[i].getKeyDOrdenacion(tipoDOrdenacion).compareTo(mitad.getKeyDOrdenacion(tipoDOrdenacion)) < 0) {
				i++;
			}
			while (objetos[j].getKeyDOrdenacion(tipoDOrdenacion).compareTo(mitad.getKeyDOrdenacion(tipoDOrdenacion)) > 0) {
				j--;
			}
			if (i <= j) {
				IOrdenacion aux = objetos[i];
				objetos[i] = objetos[j];
				objetos[j] = aux;
				i++;
				j--;
			}

		} while (i <= j);
		if (j > izquierda)
			Util.quickSort(objetos, izquierda, j, tipoDOrdenacion);
		if (i < derecha)
			Util.quickSort(objetos, i, derecha, tipoDOrdenacion);
	}

	public static String addZeroesFirstToCadena(String cadena, Integer longitud) {
		Character caracter = Character.valueOf('0');
		return Util.addCharactersFirstToCadena(cadena, longitud, caracter);
	}

	public static String addZeroesLastToCadena(String cadena, Integer longitud) {
		Character caracter = Character.valueOf('0');
		return Util.addCharactersLastToCadena(cadena, longitud, caracter);
	}

	public static String addCharactersFirstToCadena(String cadena, Integer longitud, Character caracter) {
		StringBuilder stringBuilder = new StringBuilder();
		Integer longitudTotal = longitud - cadena.length();
		for (Integer i = Integer.valueOf(0); i < longitudTotal; i++)
			stringBuilder.append(caracter);
		stringBuilder.append(cadena);
		return stringBuilder.toString();
	}

	public static String addCharactersLastToCadena(String cadena, Integer longitud, Character caracter) {
		StringBuilder stringBuilder = new StringBuilder();
		Integer longitudTotal = longitud - cadena.length();
		stringBuilder.append(cadena);
		for (Integer i = Integer.valueOf(0); i < longitudTotal; i++)
			stringBuilder.append(caracter);
		return stringBuilder.toString();
	}

}
