package sdai.com.sis.accionesdsistema.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import sdai.com.sis.accionesdsistema.AccionDSistemaLocal;
import sdai.com.sis.accionesdsistema.AccionesDSistemaLiteral;
import sdai.com.sis.rednodal.NodosDRedLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class AccionesDSistemaUtil {

    @Inject
    private NodosDRedLocal nodosDRedLocal;
    @Inject
    @Any
    private Instance<AccionDSistemaLocal> instancias;

    public AccionDSistemaLocal getAccionDSistemaLocal(String codigoDAccion) {
        /*NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(AccionDSistemaImpl.CODIGONODO);
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(AccionDSistemaImpl.CODIACCION, codigoDAccion);
        AccionDSistemaImpl accionDSistemaImpl = new AccionDSistemaImpl(tuplaDNodoLocal);
        return createAccionDSistemaLocal(accionDSistemaImpl);
         */
        return null;
    }

    private AccionDSistemaLocal createAccionDSistemaLocal(AccionDSistemaImpl accionDSistemaImpl) {
        String qualifer = accionDSistemaImpl.getCodigoDQualifer();
        AccionesDSistemaLiteral accionesDSistemaLiteral = AccionesDSistemaLiteral.of(qualifer);
        Instance<AccionDSistemaLocal> instancia = this.instancias.select(accionesDSistemaLiteral);
        AccionDSistemaLocal accionDSistemaLocal = instancia.get();
        accionDSistemaLocal.setAccionDSistemaImplLocal(accionDSistemaImpl);
        return accionDSistemaLocal;
    }

}
