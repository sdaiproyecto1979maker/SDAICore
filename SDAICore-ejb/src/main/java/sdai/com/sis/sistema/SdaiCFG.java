package sdai.com.sis.sistema;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.InputStream;
import org.w3c.dom.Node;
import sdai.com.sis.cachesdsistema.CacheDSistemaLocal;
import sdai.com.sis.cachesdsistema.GlobalCaches;
import sdai.com.sis.cachesdsistema.KCachesDSistema;
import sdai.com.sis.cachesdsistema.KeyCache;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.rednodal.nodos.ADNodos;
import sdai.com.sis.rednodal.nodos.Nodo;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class SdaiCFG implements SdaiCFGLocal {

    private static final String PATH = "VERSIONAPP.xml";
    private static final String VERSIDCORE = "VERSIDCORE";
    private static final String VERSIFRMWK = "VERSIFRMWK";
    private static final String VERSICUSTM = "VERSICUSTM";

    private String versionDCore;
    private String versionDFramework;
    private String versionDCustom;
    private ThreadSdaiCFG threadSdaiCFG;
    @Inject
    private ADNodos adNodos;
    @Inject
    private GlobalCaches globalCaches;
    private Boolean isContinuar = Boolean.TRUE;
    private Boolean isLoad;

    @PostConstruct
    public void init() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH);
            DocumentoXML documentoXML = new DocumentoXML(inputStream);
            Node root = documentoXML.getRoot();
            this.versionDCore = DocumentoXML.getValorString(root, VERSIDCORE);
            this.versionDFramework = DocumentoXML.getValorString(root, VERSIFRMWK);
            this.versionDCustom = DocumentoXML.getValorString(root, VERSICUSTM);
            this.isLoad = Boolean.FALSE;
            this.threadSdaiCFG = new ThreadSdaiCFG();
            this.threadSdaiCFG.start();
        } catch (ErrorGeneral ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getVersionDCore() {
        return versionDCore;
    }

    @Override
    public String getVersionDFramework() {
        return versionDFramework;
    }

    @Override
    public Boolean isAppFramework() {
        return Util.isCadenaNoVacia(getVersionDFramework());
    }

    @Override
    public String getVersionDCustom() {
        return versionDCustom;
    }

    public void precargarCFG() {
        this.isLoad = Boolean.FALSE;
    }

    private class ThreadSdaiCFG extends Thread {

        @Override
        public void run() {
            try {
                while (isContinuar) {
                    if (!isLoad) {
                        CacheDSistemaLocal cacheDSistemaLocal = globalCaches.getCacheDSistemaLocal(KCachesDSistema.CachesDSistema.CACHEREDNO);
                        Nodo[] nodos = adNodos.getNodos();
                        for (Nodo nodo : nodos) {
                            String codigoDNodo = nodo.getCodigoDNodo();
                            KeyCache keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
                            cacheDSistemaLocal.almacenarInstancia(keyCache, nodo);
                        }
                        isLoad = Boolean.TRUE;
                    }
                    Thread.sleep(10000);
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
