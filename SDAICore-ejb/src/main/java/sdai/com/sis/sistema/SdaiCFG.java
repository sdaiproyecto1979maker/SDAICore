package sdai.com.sis.sistema;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import org.w3c.dom.Node;
import sdai.com.sis.excepciones.ErrorGeneral;
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

    @PostConstruct
    public void init() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH);
            DocumentoXML documentoXML = new DocumentoXML(inputStream);
            Node root = documentoXML.getRoot();
            this.versionDCore = DocumentoXML.getValorString(root, VERSIDCORE);
            this.versionDFramework = DocumentoXML.getValorString(root, VERSIFRMWK);
            this.versionDCustom = DocumentoXML.getValorString(root, VERSICUSTM);
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

}
