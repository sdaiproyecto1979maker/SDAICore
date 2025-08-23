package sdai.com.sis.procesosdsesion;

import jakarta.ejb.Local;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface ProcesoDSesionImplLocal {

    String getCodigoDProceso();

    String getDescripcionDProceso();

    String getCodigoDQualifer();

    String getPaginaDProceso();

}
