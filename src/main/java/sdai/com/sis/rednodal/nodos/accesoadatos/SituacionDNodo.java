package sdai.com.sis.rednodal.nodos.accesoadatos;

import org.w3c.dom.Node;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.cacchesdsistema.InstanciaDContenedor;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@Entity
@Table(name = KNodos.KSituacionDNodo.NOMBRTABLA)
public final class SituacionDNodo extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KNodos.KSituacionDNodo.AtributosDEntidad.IDSITDNODO)
	private Long identificador;

	@Column(name = KNodos.KSituacionDNodo.AtributosDEntidad.DESCRDNODO, length = 450, nullable = false)
	private String descripcionDNodo;

	@ManyToOne
	@JoinColumn(name = KNodos.KNodo.AtributosDEntidad.IDENTINODO)
	private Nodo nodo;

	SituacionDNodo() {

	}

	public static SituacionDNodo getInstancia(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDNodo.class, codigoDNodo);
		SituacionDNodo instancia = (SituacionDNodo) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADNodos adatos = new ADNodos();
			instancia = adatos.getSituacionDNodo(codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.descripcionDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KSituacionDNodo.AtributosDEntidad.DESCRDNODO);
	}

	@Override
	public void deleteCacheInstanciaArray(InstanciaDContenedor instanciaDContenedor) {

	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getDescripcionDNodo() {
		return descripcionDNodo;
	}

	public void setDescripcionDNodo(String descripcionDNodo) {
		this.descripcionDNodo = descripcionDNodo;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

}
