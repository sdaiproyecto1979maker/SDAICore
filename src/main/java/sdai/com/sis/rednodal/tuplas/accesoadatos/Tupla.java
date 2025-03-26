package sdai.com.sis.rednodal.tuplas.accesoadatos;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdtupla.accesoadatos.AtributoDTupla;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KTuplas.KTupla.NOMBRTABLA)
@NamedQueries({ @NamedQuery(name = KTuplas.KTupla.NamedQueries.STUPLA0000, query = "SELECT T FROM Tupla T WHERE T.codigoDTupla =:CODIGTUPLA") })
public final class Tupla extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KTuplas.KTupla.AtributosDEntidad.IDENTTUPLA)
	private Long identificador;

	@Column(name = KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA, length = 450, nullable = false, unique = true)
	private String codigoDTupla;

	@OneToOne
	@JoinColumn(name = KTuplas.KSituacionDTupla.AtributosDEntidad.IDSITTUPLA)
	private SituacionDTupla situacionDTupla;

	@OneToMany(mappedBy = "tupla", cascade = CascadeType.ALL)
	private List<SituacionDTupla> situacionesDTupla;

	@OneToMany(mappedBy = "tupla", cascade = CascadeType.ALL)
	private List<AtributoDTupla> atibutosDTupla;

	Tupla() {
		this.situacionesDTupla = new ArrayList<SituacionDTupla>();
		this.atibutosDTupla = new ArrayList<AtributoDTupla>();
	}

	public static Tupla getInstancia(String codigoDTupla) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(Tupla.class, codigoDTupla);
		Tupla instancia = (Tupla) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADTuplas adatos = new ADTuplas();
			instancia = adatos.getTupla(codigoDTupla);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.codigoDTupla = DocumentoXML.getStringValueNodeDescendencia(root, KTuplas.KTupla.AtributosDEntidad.CODIGTUPLA);
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getCodigoDTupla() {
		return codigoDTupla;
	}

	public void setCodigoDTupla(String codigoDTupla) {
		this.codigoDTupla = codigoDTupla;
	}

	public SituacionDTupla getSituacionDTupla() {
		return situacionDTupla;
	}

	public void setSituacionDTupla(SituacionDTupla situacionDTupla) {
		this.situacionDTupla = situacionDTupla;
	}

	public List<SituacionDTupla> getSituacionesDTupla() {
		return situacionesDTupla;
	}

	public void setSituacionesDTupla(List<SituacionDTupla> situacionesDTupla) {
		this.situacionesDTupla = situacionesDTupla;
	}

	public List<AtributoDTupla> getAtibutosDTupla() {
		return atibutosDTupla;
	}

	public void setAtibutosDTupla(List<AtributoDTupla> atibutosDTupla) {
		this.atibutosDTupla = atibutosDTupla;
	}

}
