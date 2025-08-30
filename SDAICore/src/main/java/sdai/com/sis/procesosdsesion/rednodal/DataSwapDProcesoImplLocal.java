package sdai.com.sis.procesosdsesion.rednodal;

import jakarta.ejb.Local;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface DataSwapDProcesoImplLocal {

    String getCodigoDProceso();

    String getCodigoDDataSwap();

}
