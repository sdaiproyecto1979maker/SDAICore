package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

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
import sdai.com.sis.rednodal.CacheDRednodal;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@Entity
@Table(name = KDatosDSistema.KSituacionDDatoDSistema.NOMBRTABLA)
public final class SituacionDDatoDSistema extends AbstractEntidadCFG {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.IDSITDASIS)
	private Long identificador;

	@Column(name = KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.DESCRDDATO, length = 450, nullable = false)
	private String descripcionDDato;

	@ManyToOne
	@JoinColumn(name = KDatosDSistema.KDatoDSistema.AtributosDEntidad.IDDATSISTE)
	private DatoDSistema datoDSistema;

	SituacionDDatoDSistema() {

	}

	public static SituacionDDatoDSistema getInstancia(String codigoDDato) throws Exception {
		KeyCache keyCache = KeyCache.getInstancia(SituacionDDatoDSistema.class, codigoDDato);
		SituacionDDatoDSistema instancia = (SituacionDDatoDSistema) CacheDRednodal.recuperarInstancia(keyCache);
		if (instancia == null) {
			ADDatosDSistema adatos = new ADDatosDSistema();
			instancia = adatos.getSituacionDDatoDSistema(codigoDDato);
			CacheDRednodal.almacenarInstancia(keyCache, instancia);
		}
		return instancia;
	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(numeroDVersion, numeroDSituacion, root);
		this.descripcionDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.DESCRDDATO);
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	public String getDescripcionDDato() {
		return descripcionDDato;
	}

	public void setDescripcionDDato(String descripcionDDato) {
		this.descripcionDDato = descripcionDDato;
	}

	public DatoDSistema getDatoDSistema() {
		return datoDSistema;
	}

	public void setDatoDSistema(DatoDSistema datoDSistema) {
		this.datoDSistema = datoDSistema;
	}

}
