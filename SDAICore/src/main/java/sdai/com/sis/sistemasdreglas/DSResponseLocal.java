package sdai.com.sis.sistemasdreglas;

import jakarta.ejb.Local;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.ReglaBasicaLocal;
import sdai.com.sis.sistemasdreglas.rednodal.DSResponseImplLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface DSResponseLocal {

    void setDSResponseImplLocal(DSResponseImplLocal dSResponseImplLocal);

    void setDataSwapLocal(DataSwapLocal dataSwapLocal);

    void procesar(DataSwapLocal dataSwapLocal) throws ErrorGeneral;

    DataSwapLocal getDataSwapLocal();

    void setObjectResponse(Object value);

    ErrorGeneral tratarErrorResponse(ReglaBasicaLocal reglaBasicaLocal);

    String getCodigoDResponse();

}
