package sdai.com.sis.sistemasdreglas.rednodal;

import jakarta.enterprise.inject.spi.CDI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.rednodal.ElementoDRed;
import sdai.com.sis.rednodal.NodoDRedLocal;
import sdai.com.sis.rednodal.NodosDRed;
import sdai.com.sis.rednodal.TuplaDNodoLocal;

/**
 * @date 28/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public class ReglaDSistemaImpl extends ElementoDRed implements ReglaDSistemaImplLocal, Comparable<ReglaDSistemaImpl> {

    private static final String CODIGONODO = "RULESSISTE";

    private static final String CODIGORULE = "CODIGORULE";
    private static final String CODIGSISTE = "CODIGSISTE";
    private static final String ORDENELEME = "ORDENELEME";

    private ReglaDSistemaImpl(TuplaDNodoLocal tuplaDNodoLocal) {
        super(tuplaDNodoLocal);
    }

    public static ReglaDSistemaImpl[] getInstancias(String codigoDSistema) throws ErrorGeneral {
        KeyCache keyCache = KeyCache.getInstancia(ReglaDSistemaImpl.class, codigoDSistema);
        GlobalCaches globalCaches = CDI.current().select(GlobalCaches.class).get();
        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
        ReglaDSistemaImpl[] instancias = (ReglaDSistemaImpl[]) cacheDSistemaLocal.recuperarInstancia(keyCache);
        if (instancias == null) {
            NodosDRed nodosDRed = CDI.current().select(NodosDRed.class).get();
            NodoDRedLocal nodoDRedLocal = nodosDRed.getNodoDRedLocal(CODIGONODO);
            TuplaDNodoLocal[] tuplasDNodoLocal = nodoDRedLocal.getTuplasDNodo(CODIGSISTE, codigoDSistema);
            if (tuplasDNodoLocal != null && tuplasDNodoLocal.length > 0) {
                List<ReglaDSistemaImpl> lista = new ArrayList<>();
                for (TuplaDNodoLocal tuplaDNodoLocal : tuplasDNodoLocal) {
                    ReglaDSistemaImpl instancia = new ReglaDSistemaImpl(tuplaDNodoLocal);
                    lista.add(instancia);
                }
                instancias = lista.toArray(ReglaDSistemaImpl[]::new);
                Arrays.sort(instancias);
                cacheDSistemaLocal.almacenarInstancia(keyCache, instancias);
            }
        }
        return instancias;
    }

    @Override
    public String getCodigoDRegla() {
        return getValorString(CODIGORULE);
    }

    @Override
    public String getCodigoDSistema() {
        return getValorString(CODIGSISTE);
    }

    @Override
    public Integer getOrdenDRegla() {
        return getValorInteger(ORDENELEME);
    }

    @Override
    public int compareTo(ReglaDSistemaImpl t) {
        if (getOrdenDRegla() < t.getOrdenDRegla()) {
            return -1;
        }
        if (getOrdenDRegla() > t.getOrdenDRegla()) {
            return 1;
        }
        return 0;
    }
}
