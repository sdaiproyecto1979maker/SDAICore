package sdai.com.sis.cacchesdsistema;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class KeyCache {

	private final Class<?> clase;
	private final Boolean swDeleteable;
	private final Object[] argumentos;

	private KeyCache(Class<?> clase, Boolean swDeleteable, Object[] argumentos) {
		this.clase = clase;
		this.swDeleteable = swDeleteable;
		this.argumentos = argumentos;
	}

	public static KeyCache getInstancia(Class<?> clase, Object... argumentos) {
		return new KeyCache(clase, Boolean.valueOf(true), argumentos);
	}

	public static KeyCache getInstancia(Class<?> clase, Boolean swDeleteable, Object... argumentos) {
		return new KeyCache(clase, swDeleteable, argumentos);
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
