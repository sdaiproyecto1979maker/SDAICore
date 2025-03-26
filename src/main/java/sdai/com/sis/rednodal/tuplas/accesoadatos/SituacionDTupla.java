package sdai.com.sis.rednodal.tuplas.accesoadatos;

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
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KTuplas.KSituacionDTupla.NOMBRTABLA)
public final class SituacionDTupla extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KTuplas.KSituacionDTupla.AtributosDEntidad.IDSITTUPLA)
	private Long identificador;

	@Column(name = KTuplas.KSituacionDTupla.AtributosDEntidad.DESCRTUPLA, length = 450, nullable = false)
	private String descripcionDTupla;

	@ManyToOne
	@JoinColumn(name = KTuplas.KTupla.AtributosDEntidad.IDENTTUPLA)
	private Tupla tupla;

	SituacionDTupla() {

	}

	public static SituacionDTupla[] getInstancias(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDTupla.class, codigoDNodo);
		SituacionDTupla[] instancias = (SituacionDTupla[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ADTuplas adatos = new ADTuplas();
			instancias = adatos.getSituacionesDTupla(codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	public static SituacionDTupla getInstancia(String codigoDTupla) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDTupla.class, codigoDTupla);
		SituacionDTupla instancia = (SituacionDTupla) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADTuplas adatos = new ADTuplas();
			instancia = adatos.getSituacionDTupla(codigoDTupla);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.descripcionDTupla = DocumentoXML.getStringValueNodeDescendencia(root, KTuplas.KSituacionDTupla.AtributosDEntidad.DESCRTUPLA);
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getDescripcionDTupla() {
		return descripcionDTupla;
	}

	public void setDescripcionDTupla(String descripcionDTupla) {
		this.descripcionDTupla = descripcionDTupla;
	}

	public Tupla getTupla() {
		return tupla;
	}

	public void setTupla(Tupla tupla) {
		this.tupla = tupla;
	}

}
