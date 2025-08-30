package sdai.com.sis.sistema;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.w3c.dom.Node;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 22/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class AtributosDSistema implements AtributosDSistemaLocal {

    private static final String PATHDCORE = "sistema/ATRSISCORE.xml";
    private static final String PATHFRMWK = "sistema/ATRSISFRWK.xml";
    private static final String PATHCUSTM = "sistema/ATRSISCSTM.xml";

    private static final String NOMBRATRIB = "NOMBRATRIB";
    private static final String VALORATRIB = "VALORATRIB";

    private final ConcurrentMap<String, Object> atributosDSistema;

    public AtributosDSistema() {
        this.atributosDSistema = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void init() {
        try {
            loadAtributosDCore();
            loadAtributosDFramework();
            loadAtributosDCustom();
        } catch (ErrorGeneral ex) {
            throw new RuntimeException(ex);
        }
    }

    private void loadAtributosDCore() throws ErrorGeneral {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATHDCORE);
        if (inputStream != null) {
            loadAtributos(inputStream);
        }
    }

    private void loadAtributosDFramework() throws ErrorGeneral {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATHFRMWK);
        if (inputStream != null) {
            loadAtributos(inputStream);
        }
    }

    private void loadAtributosDCustom() throws ErrorGeneral {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATHCUSTM);
        if (inputStream != null) {
            loadAtributos(inputStream);
        }
    }

    private void loadAtributos(InputStream inputStream) throws ErrorGeneral {
        DocumentoXML documentoXML = new DocumentoXML(inputStream);
        Node root = documentoXML.getRoot();
        Node[] nodes = DocumentoXML.getDescendencia(root);
        for (Node node : nodes) {
            String nombreDAtributo = DocumentoXML.getValorString(node, NOMBRATRIB);
            String valorDAtributo = DocumentoXML.getValorString(node, VALORATRIB);
            this.atributosDSistema.put(nombreDAtributo, valorDAtributo);
        }
    }

    @Override
    public String getValorString(String nombreDAtributo) {
        Object value = this.atributosDSistema.get(nombreDAtributo);
        return Transform.toString(value);
    }
    
    @Override
    public Integer getValorInteger(String nombreDAtributo) {
        Object value = this.atributosDSistema.get(nombreDAtributo);
        return Transform.toInteger(value);
    }

}
