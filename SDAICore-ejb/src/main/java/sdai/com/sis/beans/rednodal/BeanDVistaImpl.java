package sdai.com.sis.beans.rednodal;

import sdai.com.sis.beans.BeanDVistaImplLocal;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class BeanDVistaImpl extends ElementoDRed implements BeanDVistaImplLocal {

    protected static final String CODIGONODO = "BEANSVISTA";

    protected static final String CODIGOBEAN = "CODIGOBEAN";
    protected static final String DESCRDBEAN = "DESCRDBEAN";
    protected static final String CODIGNAMED = "CODIGNAMED";

    BeanDVistaImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoDBean() {
        return getValorString(CODIGOBEAN);
    }

    @Override
    public String getDescripcionDBean() {
        return getValorString(DESCRDBEAN);
    }

    @Override
    public String getCodigoDNamed() {
        return getValorString(CODIGNAMED);
    }

}
