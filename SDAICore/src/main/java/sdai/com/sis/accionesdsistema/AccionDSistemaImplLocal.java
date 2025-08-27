package sdai.com.sis.accionesdsistema;

import jakarta.ejb.Local;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface AccionDSistemaImplLocal {

    String getCodigoDAccion();

    String getDescripcionDAccion();

    String getCodigoDQualifer();

    String getProcesoDestino();

}
