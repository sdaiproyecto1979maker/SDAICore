package sdai.com.sis.gruposdvalidacion;

import jakarta.ejb.Local;
import sdai.com.sis.excepciones.ErrorGeneral;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface ValidadorDIntegridadLocal {

    void validar(Object bean, Class<?>... grupos) throws ErrorGeneral;

}
