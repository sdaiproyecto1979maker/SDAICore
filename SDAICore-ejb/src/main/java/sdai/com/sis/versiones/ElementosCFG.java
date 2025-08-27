package sdai.com.sis.versiones;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class ElementosCFG {

    private static final String PATH = "cfgcore/ELEMENTCFG.xml";

    private final List<ElementoCFG> elementosCFG;

    public ElementosCFG() {
        this.elementosCFG = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH);
            DocumentoXML documentoXML = new DocumentoXML(inputStream);
            Node root = documentoXML.getRoot();
            Node[] nodes = DocumentoXML.getDescendencia(root);
            for (Node node : nodes) {
                ElementoCFG elementoCFG = new ElementoCFG(node);
                this.elementosCFG.add(elementoCFG);
            }
        } catch (ErrorGeneral ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ElementoCFG> getElementosCFG() {
        return elementosCFG;
    }

}
