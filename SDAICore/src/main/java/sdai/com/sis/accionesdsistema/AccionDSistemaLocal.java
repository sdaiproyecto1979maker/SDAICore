package sdai.com.sis.accionesdsistema;

import jakarta.ejb.Local;
import sdai.com.sis.accionesdsistema.rednodal.AccionDSistemaImplLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.procesosdsesion.ProcesoDSesionLocal;
import sdai.com.sis.procesosdsesion.rednodal.AccionDProcesoImplLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface AccionDSistemaLocal {

    void setAccionDSistemaImplLocal(AccionDSistemaImplLocal accionDSistemaImplLocal);

    void setAccionDProcesoImplLocal(AccionDProcesoImplLocal accionDProcesoImplLocal);

    void procesarAccion(ProcesoDSesionLocal procesoDSesionLocal) throws ErrorGeneral;

}
