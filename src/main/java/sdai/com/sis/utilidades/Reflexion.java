package sdai.com.sis.utilidades;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @date 12/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public abstract class Reflexion {

	public static Object createInstancia(String className) throws Exception {
		Object[] argumentos = new Object[0];
		return Reflexion.createInstancia(className, argumentos);
	}

	public static Object createInstancia(String className, Object... argumentos) throws Exception {
		Class<?>[] tipos = Reflexion.createTipos(argumentos);
		return Reflexion.createInstancia(className, tipos, argumentos);
	}

	public static Object createInstancia(String className, Class<?>[] tipos, Object... argumentos) throws Exception {
		Class<?> clase = Reflexion.createClass(className);
		return Reflexion.createInstancia(clase, tipos, argumentos);
	}

	public static Object createInstancia(Class<?> clase) throws Exception {
		Object[] argumentos = new Object[0];
		return Reflexion.createInstancia(clase, argumentos);
	}

	public static Object createInstancia(Class<?> clase, Object... argumentos) throws Exception {
		Class<?>[] tipos = Reflexion.createTipos(argumentos);
		return Reflexion.createInstancia(clase, tipos, argumentos);
	}

	public static Object createInstancia(Class<?> clase, Class<?>[] tipos, Object... argumentos) throws Exception {
		Constructor<?> constructor = Reflexion.createConstructor(clase, tipos);
		return Reflexion.createInstancia(constructor, argumentos);
	}

	@SuppressWarnings("deprecation")
	public static Object createInstancia(Constructor<?> constructor, Object... argumentos) throws Exception {
		Boolean isAccesible = constructor.isAccessible();
		constructor.setAccessible(Boolean.valueOf(true));
		Object instancia = constructor.newInstance(argumentos);
		constructor.setAccessible(isAccesible);
		return instancia;
	}

	public static Object invokeMetodoEstatico(String className, String methodName) throws Exception {
		Object[] argumentos = new Object[0];
		return Reflexion.invokeMetodoEstatico(className, methodName, argumentos);
	}

	public static Object invokeMetodoEstatico(String className, String methodName, Object... argumentos) throws Exception {
		Class<?>[] tipos = Reflexion.createTipos(argumentos);
		return Reflexion.invokeMetodoEstatico(className, methodName, tipos, argumentos);
	}

	public static Object invokeMetodoEstatico(String className, String methodName, Class<?>[] tipos, Object... argumentos) throws Exception {
		Class<?> clase = Reflexion.createClass(className);
		return Reflexion.invokeMetodoEstatico(clase, methodName, tipos, argumentos);
	}

	public static Object invokeMetodoEstatico(Class<?> clase, String methodName) throws Exception {
		Object[] argumentos = new Object[0];
		return Reflexion.invokeMetodoEstatico(clase, methodName, argumentos);
	}

	public static Object invokeMetodoEstatico(Class<?> clase, String methodName, Object... argumentos) throws Exception {
		Class<?>[] tipos = Reflexion.createTipos(argumentos);
		return Reflexion.invokeMetodoEstatico(clase, methodName, tipos, argumentos);
	}

	public static Object invokeMetodoEstatico(Class<?> clase, String methodName, Class<?>[] tipos, Object... argumentos) throws Exception {
		Method method = Reflexion.createMethod(clase, methodName, tipos);
		return Reflexion.invokeMetodo(null, method, argumentos);
	}

	public static Object invokeMetodo(Object instancia, String methodName) throws Exception {
		Object[] argumentos = new Object[0];
		return Reflexion.invokeMetodo(instancia, methodName, argumentos);
	}

	public static Object invokeMetodo(Object instancia, String methodName, Object... argumentos) throws Exception {
		Class<?>[] tipos = Reflexion.createTipos(argumentos);
		return Reflexion.invokeMetodo(instancia, methodName, tipos, argumentos);
	}

	public static Object invokeMetodo(Object instancia, String methodName, Class<?>[] tipos, Object... argumentos) throws Exception {
		Class<?> clase = instancia.getClass();
		Method method = Reflexion.createMethod(clase, methodName, tipos);
		return Reflexion.invokeMetodo(instancia, method, argumentos);
	}

	@SuppressWarnings("deprecation")
	public static Object invokeMetodo(Object instancia, Method method, Object... argumentos) throws Exception {
		Boolean isAccesible = method.isAccessible();
		method.setAccessible(Boolean.valueOf(true));
		Object resultado = method.invoke(instancia, argumentos);
		method.setAccessible(isAccesible);
		return resultado;
	}

	public static Class<?>[] createTipos(Object... argumentos) throws Exception {
		List<Class<?>> lista = new ArrayList<Class<?>>();
		for (Object argumento : argumentos) {
			Class<?> clase = argumento.getClass();
			lista.add(clase);
		}
		Class<?>[] tipos = lista.toArray(new Class<?>[0]);
		return tipos;
	}

	public static Class<?> createClass(String className) throws Exception {
		Class<?> clase = Class.forName(className);
		return clase;
	}

	public static Constructor<?> createConstructor(Class<?> clase, Class<?>... tipos) throws Exception {
		Constructor<?> constructor = clase.getDeclaredConstructor(tipos);
		return constructor;
	}

	public static Method createMethod(Class<?> clase, String methodName, Class<?>... tipos) throws Exception {
		Method method = clase.getDeclaredMethod(methodName, tipos);
		return method;
	}
}
