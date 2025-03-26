package sdai.com.sis.cacchesdsistema;

import sdai.com.sis.cacchesdsistema.rednodal.ContenedorDCache;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractCacheDSistema implements ICacheDSistema {

	private final Class<?> clase;
	private final ContenedorDInstancias contenedorDInstancias;
	private ContenedorDCache contenedorDCache;

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
		return this.contenedorDCache == null ? Integer.valueOf(9999999) : this.contenedorDCache.getMinutosEnContenedor();
	}

	@Override
	public Integer getElementosMaximos() {
		return this.contenedorDCache == null ? Integer.valueOf(9999999) : this.contenedorDCache.getElementosMaximos();
	}

	@Override
	public void setContenedorDCache(ContenedorDCache contenedorDCache) {
		this.contenedorDCache = contenedorDCache;
	}

}
