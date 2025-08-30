package sdai.com.sis.rednodal;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface NodoDRedLocal {

    String getCodigoDNodo();

    String getDescripcionDNodo();

    DatoDNodoLocal[] getDatosDNodo();

    TuplaDNodoLocal[] getTuplasDNodo();

    TuplaDNodoLocal getTuplaDNodo(Object... argumentos) throws ErrorGeneral;

    TuplaDNodoLocal getTuplaDNodo(String... argumentos) throws ErrorGeneral;

    TuplaDNodoLocal[] getTuplasDNodo(String... argumentos);

}
