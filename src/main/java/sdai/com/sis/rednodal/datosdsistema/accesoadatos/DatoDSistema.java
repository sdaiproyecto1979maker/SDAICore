package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import sdai.com.sis.accesoadatos.AbstractEntidadCFG;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
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

	DatoDSistema() {

	}

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

}
