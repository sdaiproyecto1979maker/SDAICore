package sdai.com.sis.procesosdsesion;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface GestorDProcesosLocal {

    void iniciar(String codigoDProceso) throws ErrorGeneral;

    void procesarAccion(String codigoDAccion) throws ErrorGeneral;

    ProcesoDSesionLocal getProcesoDSesionLocal();

}
