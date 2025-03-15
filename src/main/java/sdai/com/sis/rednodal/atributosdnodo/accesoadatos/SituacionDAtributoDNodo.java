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
import sdai.com.sis.cacchesdsistema.KeyCache;
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.atributosdnodo.AtributosDNodoUtil;
import sdai.com.sis.rednodal.atributosdnodo.KAtributosDNodo;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
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

	public static SituacionDAtributoDNodo[] getInstancias(String codigoDNodo) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo);
		SituacionDAtributoDNodo[] instancias = (SituacionDAtributoDNodo[]) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancias == null) {
			instancias = AtributosDNodoUtil.getSituacionesDAtributoDNodo(codigoDNodo);
			CacheDRednodal.almacenarInstancia(keyCache, instancias);
		}
		return instancias;
	}

	public static SituacionDAtributoDNodo getInstancia(String codigoDNodo, String codigoDDato) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDAtributoDNodo.class, codigoDNodo, codigoDDato);
		SituacionDAtributoDNodo instancia = (SituacionDAtributoDNodo) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			instancia = AtributosDNodoUtil.getSituacionDAtributoDNodo(codigoDNodo, codigoDDato);
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

	public AtributoDNodo getAtributoDNodo() {
		return atributoDNodo;
	}

	public void setAtributoDNodo(AtributoDNodo atributoDNodo) {
		this.atributoDNodo = atributoDNodo;
	}

}
