package sdai.com.sis.rednodal;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.sistema.rednodal.QualiferDefaultImpl;
import sdai.com.sis.sistema.rednodal.QualiferLiteralImpl;
import sdai.com.sis.sistema.rednodal.QualiferLiteralImplLocal;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.utilidades.Util;

/**
 * @date 24/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class GeneradorDElementos {

    @Inject
    @Any
    private Instance<ElementoDRedLocal> instancias;
    @Inject
    private GlobalCaches globalCaches;
    @Inject
    private NodosDRedLocal nodosDRedLocal;

    public ElementoDRedLocal getElementoDRedLocal(String codigoDNodo, Class<?> clase, Object... argumentos) throws ErrorGeneral {
        ElementoDRedImplLocal elementoDRedImplLocal = getElementoDRedImplLocal(codigoDNodo, clase, argumentos);
        String codigoDQualifer = elementoDRedImplLocal.getCodigoDQualifer();
        if (Util.isCadenaVacia(codigoDQualifer)) {
            QualiferDefaultImpl qualiferDefaultImpl = (QualiferDefaultImpl) getElementoDRedImplLocal("QUALFDEFLT", QualiferDefaultImpl.class, "CODIGDNODO", codigoDNodo);
            codigoDQualifer = qualiferDefaultImpl.getCodigoDQualifer();
        }
        Instance<ElementoDRedLocal> instancia = createInstancia(codigoDNodo, codigoDQualifer);
        ElementoDRedLocal elementoDRedLocal = instancia.get();
        elementoDRedLocal.setElementoDRedImplLocal(elementoDRedImplLocal);
        elementoDRedLocal.iniciar();
        return elementoDRedLocal;
    }

    public ElementoDRedImplLocal getElementoDRedImplLocal(String codigoDNodo, Class<?> clase, Object... argumentos) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(clase, argumentos);
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        ElementoDRedImplLocal elementoDRedImplLocal = (ElementoDRedImplLocal) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (elementoDRedImplLocal == null) {
            NodoDRedLocal nodoDRedLocal = getNodoDRedLocal(codigoDNodo);
            TuplaDNodoLocal tuplaDNodoLocal = getTuplaDNodoLocal(nodoDRedLocal, argumentos);
            elementoDRedImplLocal = createElementoDRedImplLocal(clase, tuplaDNodoLocal);
            cacheDSistemaLocal.almacenarInstancia(keyCache, elementoDRedImplLocal);
        }
        return elementoDRedImplLocal;
    }

    private NodoDRedLocal getNodoDRedLocal(String codigoDNodo) {
        KeyCache keyCache = KeyCache.getInstancia(NodoDRedLocal.class, codigoDNodo);
        CacheDSistemaLocal cacheDSistemaLocal = this.globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        NodoDRedLocal nodoDRedLocal = (NodoDRedLocal) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (nodoDRedLocal == null) {
            nodoDRedLocal = this.nodosDRedLocal.getNodoDRedLocal(codigoDNodo);
            cacheDSistemaLocal.almacenarInstancia(keyCache, nodoDRedLocal);
        }
        return nodoDRedLocal;
    }

    private TuplaDNodoLocal getTuplaDNodoLocal(NodoDRedLocal nodoDRedLocal, Object... argumentos) {
        TuplaDNodoLocal tuplaDNodoLocal = nodoDRedLocal.getTuplaDNodo(argumentos);
        return tuplaDNodoLocal;
    }

    private ElementoDRedImplLocal createElementoDRedImplLocal(Class<?> clase, TuplaDNodoLocal tuplaDNodoLocal) throws ErrorGeneral {
        Class<?>[] tipos = {TuplaDNodoLocal.class};
        Object[] argumentos = {tuplaDNodoLocal};
        Object instancia = Reflexion.createInstancia(clase, tipos, argumentos);
        return (ElementoDRedImplLocal) instancia;
    }

    private Instance<ElementoDRedLocal> createInstancia(String codigoDNodo, String codigoDQualifer) throws ErrorGeneral {
        QualiferLiteralImplLocal qualiferLiteralImplLocal = (QualiferLiteralImplLocal) getElementoDRedImplLocal("QUALFLITER", QualiferLiteralImpl.class, "CODIGDNODO", codigoDNodo);
        String className = qualiferLiteralImplLocal.getClassLiteral();
        Class<?>[] tipos = {Instance.class, String.class};
        Object[] argumentos = {this.instancias, codigoDQualifer};
        Instance<ElementoDRedLocal> instancia = (Instance<ElementoDRedLocal>) Reflexion.invokeMetodoEstatico(className, "getInstancia", tipos, argumentos);
        return instancia;
    }

}
