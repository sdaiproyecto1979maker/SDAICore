package sdai.com.sis.sistema.rednodal;

import jakarta.ejb.Local;
import sdai.com.sis.rednodal.ElementoDRedImplLocal;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface QualiferDefaultImplLocal extends ElementoDRedImplLocal {

    String getCodigoNodo();

}
