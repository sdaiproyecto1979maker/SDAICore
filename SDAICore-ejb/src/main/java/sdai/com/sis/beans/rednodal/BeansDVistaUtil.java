package sdai.com.sis.beans.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import sdai.com.sis.beans.rednoal.BeanDVistaImplLocal;
import sdai.com.sis.rednodal.NodosDRedLocal;

/**
 * @date 23/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class BeansDVistaUtil {

    @Inject
    private NodosDRedLocal nodosDRedLocal;

    public BeanDVistaImplLocal getBeanDVista(String codigoDBean) {
        /*
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(BeanDVistaImpl.CODIGONODO);
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(BeanDVistaImpl.CODIGOBEAN, codigoDBean);
        BeanDVistaImplLocal beanDVistaImplLocal = new BeanDVistaImpl(tuplaDNodoLocal);
        return beanDVistaImplLocal;
         */
        return null;
    }

}
