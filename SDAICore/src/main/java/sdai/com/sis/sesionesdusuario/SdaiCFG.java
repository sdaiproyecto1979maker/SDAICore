package sdai.com.sis.sesionesdusuario;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import sdai.com.sis.xml.XMLUtil;

/**
 * @date 07/05/2025
 * @author Sergio_M
 * @since VERSIONDCOREENCURSO
 */
@ApplicationScoped
public class SdaiCFG {

    private static final String NUMVERSION = "NUMVERSION";
    private static final String VERSIDCORE = "versionado/VERSIDCORE.xml";
    private static final String VERSIFRAMW = "versionado/VERSIFRAMW.xml";
    private static final String VERSCUSTOM = "versionado/VERSCUSTOM.xml";

    @Inject
    private XMLUtil xmlUtil;

    private String versionDCore;
    private String versionDFramework;
    private String versionCustom;

    public SdaiCFG() {

    }

    public void init() {
        try {
            loadVersionDCore();
            loadVersionDFramework();
            loadVersionCustom();
        } catch (Exception ex) {

        }
    }

    private void loadVersionDCore() throws Exception {
        Document document = xmlUtil.createDocumentoXML(VERSIDCORE);
        Node root = xmlUtil.getRoot(document);
        Node node = xmlUtil.getNodeDescendencia(root, NUMVERSION);
        this.versionDCore = node.getTextContent();
    }

    private void loadVersionDFramework() throws Exception {
        Document document = xmlUtil.createDocumentoXML(VERSIFRAMW);
        Node root = xmlUtil.getRoot(document);
        Node node = xmlUtil.getNodeDescendencia(root, NUMVERSION);
        this.versionDFramework = node.getTextContent();
    }

    private void loadVersionCustom() throws Exception {
        Document document = xmlUtil.createDocumentoXML(VERSCUSTOM);
        Node root = xmlUtil.getRoot(document);
        Node node = xmlUtil.getNodeDescendencia(root, NUMVERSION);
        this.versionCustom = node.getTextContent();
    }

    public String getVersionDCore() {
        return versionDCore;
    }

    public String getVersionDFramework() {
        return versionDFramework;
    }

    public String getVersionCustom() {
        return versionCustom;
    }

}
