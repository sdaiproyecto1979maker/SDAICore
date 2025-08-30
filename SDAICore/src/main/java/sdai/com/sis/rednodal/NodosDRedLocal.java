package sdai.com.sis.rednodal;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface NodosDRedLocal {

    NodoDRedLocal getNodoDRedLocal(String codigoDNodo) throws ErrorGeneral;

}
