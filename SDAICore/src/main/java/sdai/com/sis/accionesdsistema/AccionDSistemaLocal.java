package sdai.com.sis.accionesdsistema;

import jakarta.ejb.Local;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface AccionDSistemaLocal {

    void procesarAccion(DataSwapLocal dataSwapLocal) throws ErrorGeneral;

}
