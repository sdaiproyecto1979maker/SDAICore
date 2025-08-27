package sdai.com.sis.beans;

import jakarta.ejb.Local;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Local
public interface BeanDVistaImplLocal {

    String getCodigoDBean();

    String getDescripcionDBean();

    String getCodigoDNamed();

}
