package sdai.com.sis.cacchesdsistema;

import sdai.com.sis.cacchesdsistema.rednodal.ContenedorDCache;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface ICacheDSistema {

	Class<?> getClase();

	Object recuperarInstancia(KeyCache keyCache);

	void almacenarInstancia(KeyCache keyCache, Object instancia);

	void eliminarInstancia(KeyCache keyCache) throws Exception;

	void eliminarInstancias() throws Exception;

	Boolean existeInstanciaNoDeleteable(KeyCache keyCache);

	Integer getMinutosEnContenedor();

	Integer getElementosMaximos();

	void setContenedorDCache(ContenedorDCache contenedorDCache);

}
