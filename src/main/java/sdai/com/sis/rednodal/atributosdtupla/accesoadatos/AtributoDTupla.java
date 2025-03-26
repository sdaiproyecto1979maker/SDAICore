package sdai.com.sis.rednodal.atributosdtupla.accesoadatos;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.rednodal.atributosdtupla.KAtributosDTupla;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.rednodal.datosdsistema.accesoadatos.DatoDSistema;
import sdai.com.sis.rednodal.tuplas.KTuplas;
import sdai.com.sis.rednodal.tuplas.accesoadatos.Tupla;

/**
 * @date 15/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KAtributosDTupla.KAtributoDTupla.NOMBRTABLA)
public final class AtributoDTupla extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KAtributosDTupla.KAtributoDTupla.AtributosDEntidad.IDATRTUPLA)
	private Long identificador;

	@ManyToOne
	@JoinColumn(name = KDatosDSistema.KDatoDSistema.AtributosDEntidad.IDDATSISTE)
	private DatoDSistema datoDSistema;

	@ManyToOne
	@JoinColumn(name = KTuplas.KTupla.AtributosDEntidad.IDENTTUPLA)
	private Tupla tupla;

	@OneToOne
	@JoinColumn(name = KAtributosDTupla.KSituacionDAtributoDTupla.AtributosDEntidad.IDSITATRTU)
	private SituacionDAtributoDTupla situacionDAtributoDTupla;

	@OneToMany(mappedBy = "atributoDTupla", cascade = CascadeType.ALL)
	private List<SituacionDAtributoDTupla> situacionesDAtributoDTupla;

	AtributoDTupla() {
		this.situacionesDAtributoDTupla = new ArrayList<SituacionDAtributoDTupla>();
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public DatoDSistema getDatoDSistema() {
		return datoDSistema;
	}

	public void setDatoDSistema(DatoDSistema datoDSistema) {
		this.datoDSistema = datoDSistema;
	}

	public Tupla getTupla() {
		return tupla;
	}

	public void setTupla(Tupla tupla) {
		this.tupla = tupla;
	}

	public SituacionDAtributoDTupla getSituacionDAtributoDTupla() {
		return situacionDAtributoDTupla;
	}

	public void setSituacionDAtributoDTupla(SituacionDAtributoDTupla situacionDAtributoDTupla) {
		this.situacionDAtributoDTupla = situacionDAtributoDTupla;
	}

}
