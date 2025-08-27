package sdai.com.sis.sistema.rednodal;

import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class QualiferDefaultImpl extends ElementoDRed implements QualiferDefaultImplLocal {

    protected static final String CODIGONODO = "QUALFDEFLT";

    protected static final String CODIGDNODO = "CODIGDNODO";
    protected static final String CDQUALIFER = "CDQUALIFER";

    public QualiferDefaultImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoNodo() {
        return getValorString(CODIGDNODO);
    }

    @Override
    public String getCodigoDQualifer() {
        return getValorString(CDQUALIFER);
    }

}
