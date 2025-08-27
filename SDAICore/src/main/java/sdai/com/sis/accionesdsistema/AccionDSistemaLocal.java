package sdai.com.sis.accionesdsistema;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface AccionDSistemaLocal {

    void setAccionDSistemaImplLocal(AccionDSistemaImplLocal accionDSistemaImplLocal);

    void procesarAccion(ProcesoDSesionLocal procesoDSesionLocal) throws ErrorGeneral;

}
