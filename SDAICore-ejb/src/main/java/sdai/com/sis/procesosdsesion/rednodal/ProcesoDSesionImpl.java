package sdai.com.sis.procesosdsesion.rednodal;

import sdai.com.sis.procesosdsesion.ProcesoDSesionImplLocal;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ProcesoDSesionImpl extends ElementoDRed implements ProcesoDSesionImplLocal {

    protected static final String CODIGONODO = "PROCSESION";

    protected static final String CODIGPROCE = "CODIGPROCE";
    protected static final String DESCRPROCE = "DESCRPROCE";
    protected static final String CDQUALIFER = "CDQUALIFER";
    protected static final String PAGEDPROCE = "PAGEDPROCE";

    ProcesoDSesionImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoDProceso() {
        return getValorString(CODIGPROCE);
    }

    @Override
    public String getDescripcionDProceso() {
        return getValorString(DESCRPROCE);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }
    
    @Override
    public String getPaginaDProceso() {
        return getValorString(PAGEDPROCE);
    }

}
