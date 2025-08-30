package sdai.com.sis.dsentidades;

import sdai.com.sis.dsentidades.rednodal.DSEntidadImplLocal;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.utilidades.EstructuraDatos;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface DSEntidadLocal {

    void setDSEntidadImplLocal(DSEntidadImplLocal dSEntidadImplLocal);

    void validarIntegridad() throws ErrorGeneral;

    void generateDSEntidad() throws ErrorGeneral;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    String getCodigoDEntidad();

    String getCodigoDQualifer();

    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    

    String getValorStringOriginal(String key);

    String getValorString(String key);

    void addValorDDato(String key, Object value);

    DSEntidadImplLocal getDSEntidadImplLocal();

    EstructuraDatos generateEstructuraDDatos();

}
