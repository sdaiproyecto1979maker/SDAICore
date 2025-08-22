package sdai.com.sis.sistema;

import jakarta.ejb.Local;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface AtributosDSistemaLocal {

    String getValorString(String nombreDAtributo);

}
