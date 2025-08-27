package sdai.com.sis.utilidades;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class Reflexion {

    public static Object createInstancia(String className) throws ErrorGeneral {
        try {
            Class<?> clase = Class.forName(className);
            return Reflexion.createInstancia(clase);
        } catch (ClassNotFoundException ex) {
            throw new ErrorGeneral(ex);
        }
    }

    public static Object createInstancia(Class<?> clase) throws ErrorGeneral {
        Object[] argumentos = new Object[0];
        return Reflexion.createInstancia(clase, argumentos);
    }

    public static Object createInstancia(Class<?> clase, Object... argumentos) throws ErrorGeneral {
        List<Class<?>> lista = new ArrayList<>();
        for (Object argumento : argumentos) {
            lista.add(argumento.getClass());
        }
        Class<?>[] tipos = lista.toArray(Class<?>[]::new);
        return Reflexion.createInstancia(clase, tipos, argumentos);
    }

    public static Object createInstancia(String className, Class<?>[] tipos, Object... argumentos) throws ErrorGeneral {
        try {
            Class<?> clase = Class.forName(className);
            return Reflexion.createInstancia(clase, tipos, argumentos);
        } catch (ClassNotFoundException ex) {
            throw new ErrorGeneral(ex);
        }
    }

    public static Object createInstancia(Class<?> clase, Class<?>[] tipos, Object... argumentos) throws ErrorGeneral {
        try {
            Constructor<?> constructor = Reflexion.createConstructor(clase, tipos);
            Object instancia = constructor.newInstance(argumentos);
            return instancia;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ErrorGeneral(ex);
        }
    }

    private static Constructor<?> createConstructor(Class<?> clase, Class<?>[] tipos) throws ErrorGeneral {
        try {
            Constructor<?> constructor = clase.getDeclaredConstructor(tipos);
            return constructor;
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new ErrorGeneral(ex);
        }
    }

    public static Object invokeMetodoEstatico(String className, String methodName, Class<?>[] tipos, Object... argumentos) throws ErrorGeneral {
        try {
            Class<?> clase = Class.forName(className);
            Method method = Reflexion.createMethod(clase, methodName, tipos);
            Object resultado = method.invoke(null, argumentos);
            return resultado;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ErrorGeneral(ex);
        }
    }

    public static Object invokeMetodo(Object instancia, String methodName, Class<?>[] tipos, Object... argumentos) throws ErrorGeneral {
        try {
            Class<?> clase = instancia.getClass();
            Method method = Reflexion.createMethod(clase, methodName, tipos);
            Object resultado = method.invoke(instancia, argumentos);
            return resultado;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new ErrorGeneral(ex);
        }
    }

    private static Method createMethod(Class<?> clase, String methodName, Class<?>... tipos) throws ErrorGeneral {
        try {
            Method method = clase.getDeclaredMethod(methodName, tipos);
            return method;
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new ErrorGeneral(ex);
        }
    }
}
