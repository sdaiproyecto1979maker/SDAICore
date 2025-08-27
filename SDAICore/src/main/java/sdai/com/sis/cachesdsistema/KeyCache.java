package sdai.com.sis.cachesdsistema;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class KeyCache {

    private final Class<?> clase;
    private final Object[] argumentos;

    private KeyCache(Class<?> clase, Object[] argumentos) {
        this.clase = clase;
        this.argumentos = argumentos;
    }

    public static KeyCache getInstancia(Class<?> clase, Object... argumentos) {
        return new KeyCache(clase, argumentos);
    }

    public String getKeyCache() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.clase.getName());
        if (this.argumentos != null && this.argumentos.length > 0) {
            for (Object argumento : this.argumentos) {
                stringBuilder.append("#");
                stringBuilder.append(argumento);
            }
        }
        return stringBuilder.toString();
    }

}
