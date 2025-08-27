package sdai.com.sis.versiones;

import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 26/08/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public interface ComparadorDElemento {

    void getMerges(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino);

    void getRemoves(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino);

    void getPersists(DocumentoXML documentoXMLOrigen, DocumentoXML documentoXMLDestino);

}
