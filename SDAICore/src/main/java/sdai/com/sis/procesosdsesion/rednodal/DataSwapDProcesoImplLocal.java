package sdai.com.sis.procesosdsesion.rednodal;

import jakarta.ejb.Local;
import sdai.com.sis.rednodal.ElementoDRedImplLocal;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface DataSwapDProcesoImplLocal extends ElementoDRedImplLocal {

    String getCodigoDProceso();

    String getCodigoDDataSwap();

}
