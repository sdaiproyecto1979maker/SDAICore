package sdai.com.sis.procesosdsesion.rednodal;

import jakarta.ejb.Local;
import sdai.com.sis.rednodal.ElementoDRedImplLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface ProcesoDSesionImplLocal extends ElementoDRedImplLocal {

    String getCodigoDProceso();

    String getDescripcionDProceso();    

    String getPaginaDProceso();

}
