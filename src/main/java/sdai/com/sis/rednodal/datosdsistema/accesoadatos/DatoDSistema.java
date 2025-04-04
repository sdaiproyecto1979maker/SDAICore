package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

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
import sdai.com.sis.cacchesdsistema.InstanciaDContenedor;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.cacchesdsistema.contenedores.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdnodo.accesoadatos.AtributoDNodo;
import sdai.com.sis.rednodal.atributosdtupla.accesoadatos.AtributoDTupla;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@Entity
@Table(name = KDatosDSistema.KDatoDSistema.NOMBRTABLA)
@NamedQueries({ @NamedQuery(name = KDatosDSistema.KDatoDSistema.NamedQueries.SDASIS0000, query = "SELECT D FROM DatoDSistema D WHERE D.codigoDDato =:CODIGODATO") })
public final class DatoDSistema extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KDatosDSistema.KDatoDSistema.AtributosDEntidad.IDDATSISTE)
	private Long identificador;

	@Column(name = KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO, length = 10, nullable = false, unique = true)
	private String codigoDDato;

	@OneToOne
	@JoinColumn(name = KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.IDSITDASIS)
	private SituacionDDatoDSistema situacionDDatoDSistema;

	@OneToMany(mappedBy = "datoDSistema", cascade = CascadeType.ALL)
	private List<SituacionDDatoDSistema> situacionesDDatoDSistema;

	@OneToMany(mappedBy = "datoDSistema", cascade = CascadeType.ALL)
	private List<AtributoDNodo> atributosDNodo;

	@OneToMany(mappedBy = "datoDSistema", cascade = CascadeType.ALL)
	private List<AtributoDTupla> atributosDTupla;

	DatoDSistema() {
		this.situacionesDDatoDSistema = new ArrayList<SituacionDDatoDSistema>();
		this.atributosDNodo = new ArrayList<AtributoDNodo>();
		this.atributosDTupla = new ArrayList<AtributoDTupla>();
	}

	public static DatoDSistema getInstancia(String codigoDDato) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(DatoDSistema.class, codigoDDato);
		DatoDSistema instancia = (DatoDSistema) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADDatosDSistema adatos = new ADDatosDSistema();
			instancia = adatos.getDatoDSistema(codigoDDato);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
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

	public String getCodigoDDato() {
		return codigoDDato;
	}

	public void setCodigoDDato(String codigoDDato) {
		this.codigoDDato = codigoDDato;
	}

	public SituacionDDatoDSistema getSituacionDDatoDSistema() {
		return situacionDDatoDSistema;
	}

	public void setSituacionDDatoDSistema(SituacionDDatoDSistema situacionDDatoDSistema) {
		this.situacionDDatoDSistema = situacionDDatoDSistema;
	}

	public List<SituacionDDatoDSistema> getSituacionesDDatoDSistema() {
		return situacionesDDatoDSistema;
	}

	public void setSituacionesDDatoDSistema(List<SituacionDDatoDSistema> situacionesDDatoDSistema) {
		this.situacionesDDatoDSistema = situacionesDDatoDSistema;
	}

	public List<AtributoDNodo> getAtributosDNodo() {
		return atributosDNodo;
	}

	public void setAtributosDNodo(List<AtributoDNodo> atributosDNodo) {
		this.atributosDNodo = atributosDNodo;
	}

	public List<AtributoDTupla> getAtributosDTupla() {
		return atributosDTupla;
	}

	public void setAtributosDTupla(List<AtributoDTupla> atributosDTupla) {
		this.atributosDTupla = atributosDTupla;
	}

}
