package sdai.com.sis.versiones;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import sdai.com.sis.excepciones.ErrorGeneral;
import sdai.com.sis.xml.BuildDocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@RequestScoped
public class GeneradorCFG {

    @Inject
    private ElementosCFG elementosCFG;

    public String getFileVoid() {
        BuildDocumentoXML bdXML = new BuildDocumentoXML();
        bdXML.createCabecera();
        bdXML.createRoot();
        bdXML.closeRoot();
        return bdXML.getDocumentoXML();
    }

    public String getFileVersion(String entornoAplicacion) throws ErrorGeneral {
        BuildDocumentoXML bdXML = new BuildDocumentoXML();
        bdXML.createCabecera();
        bdXML.createRoot();
        List<ElementoCFG> _elementosCFG = this.elementosCFG.getElementosCFG();
        for (ElementoCFG elementoCFG : _elementosCFG) {
            elementoCFG.generarVersionDElemento(bdXML, entornoAplicacion);
        }
        bdXML.closeRoot();
        return bdXML.getDocumentoXML();
    }

}
