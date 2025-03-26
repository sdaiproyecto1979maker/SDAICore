package sdai.com.sis.cacchesdsistema;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractCacheDSistema implements ICacheDSistema {

	private final Class<?> clase;
	private final ContenedorDInstancias contenedorDInstancias;

	protected AbstractCacheDSistema(Class<?> clase) {
		this.clase = clase;
		this.contenedorDInstancias = new ContenedorDInstancias(this);
	}

	@Override
	public Class<?> getClase() {
		return clase;
	}

	@Override
	public Object recuperarInstancia(KeyCache keyCache) {
		InstanciaDContenedor instanciaDContenedor = this.contenedorDInstancias.recuperarInstancia(keyCache);
		return instanciaDContenedor == null ? null : instanciaDContenedor.getInstancia();
	}

	@Override
	public void almacenarInstancia(KeyCache keyCache, Object instancia) {
		this.contenedorDInstancias.almacenarInstancia(keyCache, instancia);
	}

	@Override
	public void eliminarInstancia(KeyCache keyCache) {
		this.contenedorDInstancias.eliminarInstancia(keyCache);
	}

	@Override
	public void eliminarInstancias() {
		this.contenedorDInstancias.eliminarInstancias();
	}

	@Override
	public Integer getMinutosEnContenedor() {
		// TODO: Pendiente desarrollo
		return null;
	}

	@Override
	public Integer getElementosMaximos() {
		// TODO: Pendiente desarrollo
		return null;
	}

}
