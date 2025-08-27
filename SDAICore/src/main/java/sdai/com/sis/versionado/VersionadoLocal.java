package sdai.com.sis.versionado;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface VersionadoLocal {

    void generarCFG() throws ErrorGeneral;
    
    void generarComparacion() throws ErrorGeneral;

}
