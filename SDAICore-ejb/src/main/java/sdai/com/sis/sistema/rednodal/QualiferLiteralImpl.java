package sdai.com.sis.sistema.rednodal;

import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class QualiferLiteralImpl extends ElementoDRed implements QualiferLiteralImplLocal {

    protected static final String CODIGONODO = "QUALFLITER";

    protected static final String CODIGDNODO = "CODIGDNODO";
    protected static final String CLASSLITER = "CLASSLITER";

    public QualiferLiteralImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    @Override
    public String getCodigoNodo() {
        return getValorString(CODIGDNODO);
    }

    @Override
    public String getClassLiteral() {
        return getValorString(CLASSLITER);
    }

    @Override
    public String getCodigoDQualifer() {
        return "";
    }

}
