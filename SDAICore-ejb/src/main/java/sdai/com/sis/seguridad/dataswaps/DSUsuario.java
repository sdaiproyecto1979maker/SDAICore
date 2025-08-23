package sdai.com.sis.seguridad.dataswaps;

import jakarta.enterprise.context.SessionScoped;
import sdai.com.sis.dsentidades.AbstractDSEntidad;
import sdai.com.sis.dsentidades.DSEntidades;
import sdai.com.sis.dsentidades.KDSEntidades;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@DSEntidades(KDSEntidades.DSEntidades.DSDUSUARIO)
public class DSUsuario extends AbstractDSEntidad {

}
