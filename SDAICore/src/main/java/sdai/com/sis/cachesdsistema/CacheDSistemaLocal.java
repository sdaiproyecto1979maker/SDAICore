package sdai.com.sis.cachesdsistema;

import jakarta.ejb.Local;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface CacheDSistemaLocal {

    Object recuperarInstancia(KeyCache keyCache);

    void almacenarInstancia(KeyCache keyCache, Object instancia);

    void eliminarInstancia(KeyCache keyCache);

}
