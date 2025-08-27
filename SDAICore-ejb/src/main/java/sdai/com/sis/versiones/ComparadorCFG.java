package sdai.com.sis.versiones;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.xml.BuildDocumentoXML;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@ApplicationScoped
public class ComparadorCFG {

    @Inject
    private ElementosCFG elementosCFG;

    private final Map<String, ComparadorDElemento> comparaciones;

    public ComparadorCFG() {
        this.comparaciones = new HashMap<>();
    }

    public void compararCFG(DocumentoXML documentoXMLOrigen, String entornoAplicacion) throws ErrorGeneral {
        BuildDocumentoXML bdXML = new BuildDocumentoXML();
        bdXML.createCabecera();
        bdXML.createRoot();
        List<ElementoCFG> _elementosCFG = this.elementosCFG.getElementosCFG();
        for (ElementoCFG elementoCFG : _elementosCFG) {
            elementoCFG.generarVersionDElemento(bdXML, entornoAplicacion);
        }
        bdXML.closeRoot();
        DocumentoXML documentoXMLDestino = new DocumentoXML(bdXML.getDocumentoXML());
        for (ElementoCFG elementoCFG : _elementosCFG) {
            String codigoDElemento = elementoCFG.getCodigoDElemento();
            ComparadorDElemento comparadorDElemento = elementoCFG.compararElemento(documentoXMLOrigen, documentoXMLDestino);
            this.comparaciones.put(codigoDElemento, comparadorDElemento);
        }
    }

}
