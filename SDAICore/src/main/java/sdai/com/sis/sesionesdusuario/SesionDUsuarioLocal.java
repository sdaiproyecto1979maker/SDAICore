package sdai.com.sis.sesionesdusuario;

import jakarta.ejb.Local;
import java.util.Locale;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface SesionDUsuarioLocal {

    Locale getLocale();

    void setLocale(String locale);

    Boolean isLocaleSeleccionado(String key);

}
