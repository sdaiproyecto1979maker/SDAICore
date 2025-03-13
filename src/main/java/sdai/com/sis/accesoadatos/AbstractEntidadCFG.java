package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
@MappedSuperclass
public abstract class AbstractEntidadCFG extends AbstractEntidad implements IEntidadCFG, Comparable<AbstractEntidadCFG> {

	private static final long serialVersionUID = 1L;

	@Column(name = KAplicaciones.AtributosDEntidad.NUMERSITUA, nullable = false)
	private Integer numeroDSituacion;

	@OneToOne
	@JoinColumn(name = KVersionado.KNumerosDVersion.AtributosDEntidad.IDNUMEVERS)
	private NumeroDVersion numeroDVersion;

	@Column(name = KAplicaciones.AtributosDEntidad.SWENTACTIV, nullable = false)
	private Boolean swEntidadActiva;

	protected AbstractEntidadCFG() {

	}

	@Override
	public void addNode(NumeroDVersion numeroDVersion, Integer numeroDSituacion, Node root) {
		super.addNode(root);
		this.numeroDSituacion = numeroDSituacion;
		this.numeroDVersion = numeroDVersion;
		this.swEntidadActiva = DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV);
	}

	public Integer getNumeroDSituacion() {
		return numeroDSituacion;
	}

	public void setNumeroDSituacion(Integer numeroDSituacion) {
		this.numeroDSituacion = numeroDSituacion;
	}

	public NumeroDVersion getNumeroDVersion() {
		return numeroDVersion;
	}

	public void setNumeroDVersion(NumeroDVersion numeroDVersion) {
		this.numeroDVersion = numeroDVersion;
	}

	public Boolean getSwEntidadActiva() {
		return swEntidadActiva;
	}

	public void setSwEntidadActiva(Boolean swEntidadActiva) {
		this.swEntidadActiva = swEntidadActiva;
	}

	@Override
	public int compareTo(AbstractEntidadCFG o) {
		if (getNumeroDSituacion() > o.getNumeroDSituacion())
			return 1;
		else if (getNumeroDSituacion() < o.getNumeroDSituacion())
			return -1;
		return 0;
	}

}
