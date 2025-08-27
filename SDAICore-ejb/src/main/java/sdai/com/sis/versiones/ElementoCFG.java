package sdai.com.sis.versiones;

import org.w3c.dom.Node;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.utilidades.Reflexion;
import sdai.com.sis.xml.BuildDocumentoXML;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class ElementoCFG {

    private static final String CODIELEMEN = "CODIELEMEN";
    private static final String CLSVERSION = "CLSVERSION";
    private static final String CLSCOMPARA = "CLSCOMPARA";

    private final String codigoDElemento;
    private final String claseDVersionado;
    private final String claseDComparacion;

    public ElementoCFG(Node root) {
        this.codigoDElemento = DocumentoXML.getValorString(root, CODIELEMEN);
        this.claseDVersionado = DocumentoXML.getValorString(root, CLSVERSION);
        this.claseDComparacion = DocumentoXML.getValorString(root, CLSCOMPARA);
    }

    public void generarVersionDElemento(BuildDocumentoXML bdXML, String entornoAplicacion) throws ErrorGeneral {
        String className = getClaseDVersionado();
        VersiondoDElemento versiondoDElemento = (VersiondoDElemento) Reflexion.createInstancia(className);
        versiondoDElemento.getVersion(bdXML, entornoAplicacion);
    }

    public ComparadorDElemento compararElemento(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino) throws ErrorGeneral {
        String className = getClaseDComparacion();
        ComparadorDElemento comparadorDElemento = (ComparadorDElemento) Reflexion.createInstancia(className);
        comparadorDElemento.getMerges(documentoXMLOrigen, documentoXMLDestino);
        return comparadorDElemento;
    }

    public String getCodigoDElemento() {
        return codigoDElemento;
    }

    public String getClaseDVersionado() {
        return claseDVersionado;
    }

    public String getClaseDComparacion() {
        return claseDComparacion;
    }

}
