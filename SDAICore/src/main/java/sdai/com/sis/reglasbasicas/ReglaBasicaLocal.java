package sdai.com.sis.reglasbasicas;

import jakarta.ejb.Local;
import sdai.com.sis.dataswaps.DataSwapLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.reglasbasicas.rednodal.ReglaBasicaImplLocal;
import sdai.com.sis.sistemasdreglas.DSResponseLocal;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface ReglaBasicaLocal {

    void setReglaBasicaImplLocal(ReglaBasicaImplLocal reglaBasicaImplLocal);

    void evaluar(DSResponseLocal dSResponseLocal) throws ErrorGeneral;

    void evaluar(DataSwapLocal dataSwapLocal) throws ErrorGeneral;

}
