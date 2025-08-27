package sdai.com.sis.procesosdsesion;

import sdai.com.sis.procesosdsesion.rednodal.ProcesoDSesionImplLocal;
import jakarta.ejb.Local;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.rednodal.ElementoDRedLocal;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface ProcesoDSesionLocal extends ElementoDRedLocal {        

    void procesarAccion(String codigoDAccion) throws ErrorGeneral;

    String getCodigoDProceso();

    String getPaginaDProceso();

    DataSwapLocal getDataSwapLocal();

    void realizarValidaciones() throws ErrorGeneral;

    ProcesoDSesionImplLocal getProcesoDSesionImplLocal();

}
