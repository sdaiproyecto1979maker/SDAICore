package sdai.com.sis.procesosdsesion.rednodal;

import jakarta.ejb.Local;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface AccionDProcesoImplLocal {

    String getCodigoDAccion();

    String getCodigoDProceso();

    String getCodigoDQualifer();

    String getProcesoDestino();

    String getSistemaDReglas();

}
