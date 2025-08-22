package sdai.com.sis.traducciones;

import jakarta.ejb.Local;
import java.util.List;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface TraductorLocal {

    String traducir(String key);

    List<String> getListaDLocales();

}
