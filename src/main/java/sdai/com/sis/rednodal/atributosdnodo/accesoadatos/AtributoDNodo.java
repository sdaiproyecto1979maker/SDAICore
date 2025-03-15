package sdai.com.sis.rednodal.atributosdnodo.accesoadatos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdnodo.KAtributosDNodo;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KAtributosDNodo.KAtributoDNodo.NOMBRTABLA)
@NamedQueries({ @NamedQuery(name = KAtributosDNodo.KAtributoDNodo.NamedQueries.SATRNO0000, query = "SELECT A FROM AtributoDNodo A"),
		@NamedQuery(name = KAtributosDNodo.KAtributoDNodo.NamedQueries.SATRNO0001, query = "SELECT A FROM AtributoDNodo A WHERE A.nodo.codigoDNodo =:CODIGONODO"),
		@NamedQuery(name = KAtributosDNodo.KAtributoDNodo.NamedQueries.SATRNO0002, query = "SELECT A FROM AtributoDNodo A WHERE A.nodo.codigoDNodo =:CODIGONODO AND A.datoDSistema.codigoDDato =:CODIGODATO") })
public final class AtributoDNodo extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KAtributosDNodo.KAtributoDNodo.AtributosDEntidad.IDATRDNODO)
	private Long identificador;

	@ManyToOne
	@JoinColumn(name = KNodos.KNodo.AtributosDEntidad.IDENTINODO)
	private Nodo nodo;

	@ManyToOne
	@JoinColumn(name = KDatosDSistema.KDatoDSistema.AtributosDEntidad.IDDATSISTE)
	private DatoDSistema datoDSistema;

	@OneToOne
	@JoinColumn(name = KAtributosDNodo.KSituacionDAtributoDNodo.AtributosDEntidad.IDSITATRNO)
	private SituacionDAtributoDNodo situacionDAtributoDNodo;

	@OneToMany(mappedBy = "atributoDNodo", cascade = CascadeType.ALL)
	private List<SituacionDAtributoDNodo> situacionesDAtributoDNodo;

	AtributoDNodo() {
		this.situacionesDAtributoDNodo = new ArrayList<SituacionDAtributoDNodo>();
	}

	public static AtributoDNodo[] getInstancias() throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class);
		AtributoDNodo[] instancias = (AtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ADAtributosDNodo adatos = new ADAtributosDNodo();
			instancias = adatos.getAtributosDNodo();
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	public static AtributoDNodo[] getInstancias(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo);
		AtributoDNodo[] instancias = (AtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ADAtributosDNodo adatos = new ADAtributosDNodo();
			instancias = adatos.getAtributosDNodo(codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	public static AtributoDNodo getInstancia(String codigoDNodo, String codigoDDato) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(AtributoDNodo.class, codigoDNodo, codigoDDato);
		AtributoDNodo instancia = (AtributoDNodo) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADAtributosDNodo adatos = new ADAtributosDNodo();
			instancia = adatos.getAtributoDNodo(codigoDNodo, codigoDDato);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public DatoDSistema getDatoDSistema() {
		return datoDSistema;
	}

	public void setDatoDSistema(DatoDSistema datoDSistema) {
		this.datoDSistema = datoDSistema;
	}

	public SituacionDAtributoDNodo getSituacionDAtributoDNodo() {
		return situacionDAtributoDNodo;
	}

	public void setSituacionDAtributoDNodo(SituacionDAtributoDNodo situacionDAtributoDNodo) {
		this.situacionDAtributoDNodo = situacionDAtributoDNodo;
	}

	public List<SituacionDAtributoDNodo> getSituacionesDAtributoDNodo() {
		return situacionesDAtributoDNodo;
	}

	public void setSituacionesDAtributoDNodo(List<SituacionDAtributoDNodo> situacionesDAtributoDNodo) {
		this.situacionesDAtributoDNodo = situacionesDAtributoDNodo;
	}

}
