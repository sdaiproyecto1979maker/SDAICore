package sdai.com.sis.seguridad.dataswaps;

import jakarta.enterprise.context.SessionScoped;
import sdai.com.sis.dsentidades.AbstractDSEntidad;
import sdai.com.sis.dsentidades.DSEntidades;
import sdai.com.sis.dsentidades.KDSEntidades;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@SessionScoped
@DSEntidades(KDSEntidades.DSEntidades.DSDUSUARIO)
public class DSUsuario extends AbstractDSEntidad {

    public static final String DSDUSUARIO = "DSDUSUARIO";
    public static final String CODIGUSUAR = "CODIGUSUAR";
    public static final String PASSWUSUAR = "PASSWUSUAR";

    @Override
    public EstructuraDatos generateEstructuraDDatos() {
        EstructuraDatos eDatos = new EstructuraDatos(DSDUSUARIO);
        eDatos.addDato(CODIGUSUAR, getCodigoDUsuario());
        eDatos.addDato(PASSWUSUAR, getPasswordDUsuario());
        return eDatos;
    }

    public String getCodigoDUsuario() {
        return getValorString(CODIGUSUAR);
    }

    public String getPasswordDUsuario() {
        return getValorStringOriginal(PASSWUSUAR);
    }

}
