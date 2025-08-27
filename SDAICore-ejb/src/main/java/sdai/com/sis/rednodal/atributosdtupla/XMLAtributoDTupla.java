package sdai.com.sis.rednodal.atributosdtupla;

import org.w3c.dom.Node;
import sdai.com.sis.rednodal.XMLCFGLocal;
import sdai.com.sis.rednodal.datosdsistema.*;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 25/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class XMLAtributoDTupla implements XMLCFGLocal {

    private final String codigoDDato;
    private final String codigoDTupla;
    private final String valorDAtributo;

    public XMLAtributoDTupla(Node root) {
        this.codigoDDato = DocumentoXML.getValorString(root, KDatosDSistema.AtributosDEntidad.CODIGODATO);
        this.codigoDTupla = DocumentoXML.getValorString(root, KTuplas.AtributosDEntidad.CODIGTUPLA);
        this.valorDAtributo = DocumentoXML.getValorString(root, KAtributosDTupla.AtributosDEntidad.VALORATRIB);
    }

    public String getCodigoDDato() {
        return codigoDDato;
    }

    public String getCodigoDTupla() {
        return codigoDTupla;
    }

    public String getValorDAtributo() {
        return valorDAtributo;
    }

}
