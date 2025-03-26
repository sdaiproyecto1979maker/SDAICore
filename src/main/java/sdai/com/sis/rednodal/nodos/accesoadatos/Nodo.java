package sdai.com.sis.rednodal.nodos.accesoadatos;

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
import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.AtributoDNodo;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.tuplas.accesoadatos.Tupla;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KNodos.KNodo.NOMBRTABLA)
@NamedQueries({ @NamedQuery(name = KNodos.KNodo.NamedQueries.SNODOS0000, query = "SELECT N FROM Nodo N WHERE N.codigoDNodo =:CODIGONODO"),
		@NamedQuery(name = KNodos.KNodo.NamedQueries.SNODOS0001, query = "SELECT N FROM Nodo N") })
public final class Nodo extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KNodos.KNodo.AtributosDEntidad.IDENTINODO)
	private Long identificador;

	@Column(name = KNodos.KNodo.AtributosDEntidad.CODIGONODO, length = 10, nullable = false, unique = true)
	private String codigoDNodo;

	@OneToOne
	@JoinColumn(name = KNodos.KSituacionDNodo.AtributosDEntidad.IDSITDNODO)
	private SituacionDNodo situacionDNodo;

	@OneToMany(mappedBy = "nodo", cascade = CascadeType.ALL)
	private List<SituacionDNodo> situacionesDNodo;

	@OneToMany(mappedBy = "nodo", cascade = CascadeType.ALL)
	private List<AtributoDNodo> atributosDNodo;

	@OneToMany(mappedBy = "nodo", cascade = CascadeType.ALL)
	private List<Tupla> tuplas;

	Nodo() {
		this.situacionesDNodo = new ArrayList<SituacionDNodo>();
		this.atributosDNodo = new ArrayList<AtributoDNodo>();
		this.tuplas = new ArrayList<Tupla>();
	}

	public static Nodo getInstancia(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(Nodo.class, codigoDNodo);
		Nodo instancia = (Nodo) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADNodos adatos = new ADNodos();
			instancia = adatos.getNodo(codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	public static Nodo[] getInstancias() throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(Nodo.class);
		Nodo[] instancias = (Nodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ADNodos adatos = new ADNodos();
			instancias = adatos.getNodos();
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getCodigoDNodo() {
		return codigoDNodo;
	}

	public void setCodigoDNodo(String codigoDNodo) {
		this.codigoDNodo = codigoDNodo;
	}

	public SituacionDNodo getSituacionDNodo() {
		return situacionDNodo;
	}

	public void setSituacionDNodo(SituacionDNodo situacionDNodo) {
		this.situacionDNodo = situacionDNodo;
	}

	public List<SituacionDNodo> getSituacionesDNodo() {
		return situacionesDNodo;
	}

	public void setSituacionesDNodo(List<SituacionDNodo> situacionesDNodo) {
		this.situacionesDNodo = situacionesDNodo;
	}

	public List<AtributoDNodo> getAtributosDNodo() {
		return atributosDNodo;
	}

	public void setAtributosDNodo(List<AtributoDNodo> atributosDNodo) {
		this.atributosDNodo = atributosDNodo;
	}

	public List<Tupla> getTuplas() {
		return tuplas;
	}

	public void setTuplas(List<Tupla> tuplas) {
		this.tuplas = tuplas;
	}

}
