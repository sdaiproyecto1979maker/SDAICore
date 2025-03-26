package sdai.com.sis.cacchesdsistema;

/**
 * @date 26/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface ICacheDSistema {

	Class<?> getClase();

	Object recuperarInstancia(KeyCache keyCache);

	void almacenarInstancia(KeyCache keyCache, Object instancia);

	void eliminarInstancia(KeyCache keyCache);

	void eliminarInstancias();

	Integer getMinutosEnContenedor();

	Integer getElementosMaximos();

}
