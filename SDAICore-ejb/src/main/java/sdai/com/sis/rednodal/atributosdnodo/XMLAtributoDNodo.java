package sdai.com.sis.rednodal.atributosdnodo;

import org.w3c.dom.Node;
import sdai.com.sis.rednodal.XMLCFGLocal;
import sdai.com.sis.rednodal.datosdsistema.*;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class XMLAtributoDNodo implements XMLCFGLocal {

    private final String codigoDDato;
    private final String codigoDNodo;

    public XMLAtributoDNodo(Node root) {
        this.codigoDDato = DocumentoXML.getValorString(root, KDatosDSistema.AtributosDEntidad.CODIGODATO);
        this.codigoDNodo = DocumentoXML.getValorString(root, KNodos.AtributosDEntidad.CODIGONODO);
    }

    public String getCodigoDDato() {
        return codigoDDato;
    }

    public String getCodigoDNodo() {
        return codigoDNodo;
    }

}
