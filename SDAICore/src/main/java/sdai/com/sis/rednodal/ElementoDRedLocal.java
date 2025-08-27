package sdai.com.sis.rednodal;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author sergio
 */
@Local
public interface ElementoDRedLocal {

    void setElementoDRedImplLocal(ElementoDRedImplLocal elementoDRedImplLocal);

    void iniciar() throws ErrorGeneral;

}
