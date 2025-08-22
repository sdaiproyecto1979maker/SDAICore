package sdai.com.sis.procesosdsesion;

import jakarta.ejb.Local;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface GestorDProcesosLocal {

    void iniciar(String codigoDProceso);

    ProcesoDSesionLocal getProcesoDSesionLocal();

}
