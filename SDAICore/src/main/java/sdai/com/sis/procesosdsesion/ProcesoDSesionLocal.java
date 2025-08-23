package sdai.com.sis.procesosdsesion;

import jakarta.ejb.Local;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface ProcesoDSesionLocal {

    void setProcesoDSesion(ProcesoDSesionImplLocal procesoDSesion);

    void iniciar();

    String getCodigoDProceso();

    String getPaginaDProceso();

    DataSwapLocal getDataSwapLocal();

    void realizarValidaciones() throws ErrorGeneral;

}
