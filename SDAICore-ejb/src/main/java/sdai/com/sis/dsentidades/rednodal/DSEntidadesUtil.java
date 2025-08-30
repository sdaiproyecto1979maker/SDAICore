package sdai.com.sis.dsentidades.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import sdai.com.sis.beans.rednoal.BeanDVistaImplLocal;
import sdai.com.sis.beans.rednodal.BeansDVistaUtil;
import sdai.com.sis.dsentidades.DSEntidadLocal;
import sdai.com.sis.dsentidades.DSEntidadesLiteral;
import sdai.com.sis.rednodal.NodosDRedLocal;

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
    @Inject
    private BeansDVistaUtil beansDVistaUtil;

    public DSEntidadLocal getDSEntidadLocal(String codigoDEntidad) {
        /*NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(DSEntidadImpl.CODIGONODO);
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(DSEntidadImpl.CODIENTITY, codigoDEntidad);
        DSEntidadImpl dSEntidadImpl = new DSEntidadImpl(tuplaDNodoLocal);
        return createDSEntidadLocal(dSEntidadImpl);*/
        return null;
    }

    private DSEntidadLocal createDSEntidadLocal(DSEntidadImpl dSEntidadImpl) {
        String qualifer = dSEntidadImpl.getCodigoDQualifer();
        DSEntidadesLiteral dSEntidadesLiteral = DSEntidadesLiteral.of(qualifer);
        Instance<DSEntidadLocal> instancia = this.instancias.select(dSEntidadesLiteral);
        DSEntidadLocal dSEntidadLocal = instancia.get();
        dSEntidadLocal.setDSEntidadImplLocal(dSEntidadImpl);
        return dSEntidadLocal;
    }

    public BeanDVistaImplLocal[] getBeansDVista(String codigoDEntidad) {
        /*List<BeanDVistaImplLocal> lista = new ArrayList<>();
        NodoDRedLocal nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal("BEANENTITY");
        TuplaDNodoLocal[] tuplasDNodo = nodoDRedLocal.getTuplasDNodo(DSEntidadImpl.CODIENTITY, codigoDEntidad);
        for (TuplaDNodoLocal tuplaDNodo : tuplasDNodo) {
            String codigoDBean = tuplaDNodo.getDatoDTupla("CODIGOBEAN").getValorDAtributo();
            BeanDVistaImplLocal beanDVista = this.beansDVistaUtil.getBeanDVista(codigoDBean);
            lista.add(beanDVista);
        }
        return lista.toArray(BeanDVistaImplLocal[]::new);*/
        return null;
    }

    public void destroyDSEntidadLocal(DSEntidadLocal dSEntidadLocal) {
        DSEntidadImpl dSEntidadImpl = (DSEntidadImpl) dSEntidadLocal.getDSEntidadImplLocal();
        String qualifer = dSEntidadImpl.getCodigoDQualifer();
        DSEntidadesLiteral dSEntidadesLiteral = DSEntidadesLiteral.of(qualifer);
        Instance<DSEntidadLocal> instancia = this.instancias.select(dSEntidadesLiteral);
        instancia.destroy(dSEntidadLocal);
    }

}
