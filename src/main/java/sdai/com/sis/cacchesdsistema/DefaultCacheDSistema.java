package sdai.com.sis.cacchesdsistema;

/**
 * @date 26/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
public final class DefaultCacheDSistema extends AbstractCacheDSistema {

	private DefaultCacheDSistema(Class<?> clase) {
		super(clase);
	}

	public static ICacheDSistema getInstancia(Class<?> clase) {
		return new DefaultCacheDSistema(clase);
	}

}
