package sdai.com.sis.dsentidades.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.dsentidades.DSEntidadesLiteral;
import sdai.com.sis.rednodal.NodoDRedLocal;
import sdai.com.sis.rednodal.NodosDRedLocal;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class DSEntidadesUtil {

    @Inject
    private NodosDRedLocal nodosDRedLocal;
    @Inject
    @Any
    private Instance<DSEntidadLocal> instancias;

    public DSEntidadLocal getDSEntidadLocal(String codigoDEntidad) {
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(DSEntidadImpl.CODIGONODO);
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(DSEntidadImpl.CODIENTITY, codigoDEntidad);
        DSEntidadImpl dSEntidadImpl = new DSEntidadImpl(tuplaDNodoLocal);
        return createDSEntidadLocal(dSEntidadImpl);
    }

    private DSEntidadLocal createDSEntidadLocal(DSEntidadImpl dSEntidadImpl) {
        String qualifer = dSEntidadImpl.getCodigoDQualifer();
        DSEntidadesLiteral dSEntidadesLiteral = DSEntidadesLiteral.of(qualifer);
        Instance<DSEntidadLocal> instancia = this.instancias.select(dSEntidadesLiteral);
        DSEntidadLocal dSEntidadLocal = instancia.get();
        dSEntidadLocal.setDSEntidadImplLocal(dSEntidadImpl);
        return dSEntidadLocal;
    }

}
