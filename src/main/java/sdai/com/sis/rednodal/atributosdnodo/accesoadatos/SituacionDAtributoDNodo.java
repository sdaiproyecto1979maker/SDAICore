package sdai.com.sis.rednodal.atributosdnodo.accesoadatos;

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
import sdai.com.sis.rednodal.atributosdnodo.KAtributosDNodo;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.nodos.accesoadatos.Nodo;

/**
 * @date 15/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@Entity
@Table(name = KAtributosDNodo.KSituacionDAtributoDNodo.NOMBRTABLA)
public final class SituacionDAtributoDNodo extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KAtributosDNodo.KSituacionDAtributoDNodo.AtributosDEntidad.IDSITATRNO)
	private Long identificador;

	@ManyToOne
	@JoinColumn(name = KAtributosDNodo.KAtributoDNodo.AtributosDEntidad.IDATRDNODO)
	private AtributoDNodo atributoDNodo;

	SituacionDAtributoDNodo() {

	}

	public static SituacionDAtributoDNodo getInstancia(String codigoDNodo, String codigoDDato) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo, codigoDDato);
		SituacionDAtributoDNodo instancia = (SituacionDAtributoDNodo) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADAtributosDNodo adatos = new ADAtributosDNodo();
			instancia = adatos.getSituacionDAtributoDNodo(codigoDNodo, codigoDDato);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	public static SituacionDAtributoDNodo[] getInstancias(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo);
		SituacionDAtributoDNodo[] instancias = (SituacionDAtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			ADAtributosDNodo adatos = new ADAtributosDNodo();
			instancias = adatos.getSituacionesDAtributoDNodo(codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	@Override
	public void deleteCacheInstanciaArray(InstanciaDContenedor instanciaDContenedor) throws Exception {
		Boolean isInstanciasNoDeleteables = Boolean.valueOf(true);
		SituacionDAtributoDNodo[] instancias = (SituacionDAtributoDNodo[]) instanciaDContenedor.getInstancia();
		for (SituacionDAtributoDNodo instancia : instancias) {
			AtributoDNodo atributoDNodo = instancia.getAtributoDNodo();
			Nodo nodo = atributoDNodo.getNodo();
			String codigoDNodo = nodo.getCodigoDNodo();
			DatoDSistema datoDSistema = atributoDNodo.getDatoDSistema();
			String codigoDDato = datoDSistema.getCodigoDDato();
			KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, Boolean.valueOf(false), codigoDNodo, codigoDDato);
			if (CacheDRednodal.existeInstanciaNoDeleteable(keyCache)) {
				isInstanciasNoDeleteables = Boolean.valueOf(false);
				break;
			}
		}
		if (isInstanciasNoDeleteables.equals(Boolean.valueOf(true))) {
			KeyCache keyCache = instanciaDContenedor.getKeyCache();
			CacheDRednodal.eliminarInstancia(keyCache);
		}
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public AtributoDNodo getAtributoDNodo() {
		return atributoDNodo;
	}

	public void setAtributoDNodo(AtributoDNodo atributoDNodo) {
		this.atributoDNodo = atributoDNodo;
	}

}
