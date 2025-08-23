package sdai.com.sis.dsentidades;

import java.io.Serializable;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractDSEntidad implements DSEntidadLocal, Serializable {

    private DSEntidadImplLocal dSEntidadImplLocal;

    @Override
    public void setDSEntidadImplLocal(DSEntidadImplLocal dSEntidadImplLocal) {
        this.dSEntidadImplLocal = dSEntidadImplLocal;
    }

    @Override
    public String getCodigoDEntidad() {
        return this.dSEntidadImplLocal.getCodigoDEntidad();
    }

}
