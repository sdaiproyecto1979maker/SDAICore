package sdai.com.sis.accesoadatos;

import org.w3c.dom.Node;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.utilidades.IOrdenacion;
import sdai.com.sis.utilidades.Transform;
import sdai.com.sis.utilidades.Util;
import sdai.com.sis.versionado.KVersionado;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since 1.0.0.0-RELEASE
 * @author Sergio_M
 */
@MappedSuperclass
public abstract class AbstractEntidadCFG extends AbstractEntidad implements IEntidadCFG, IOrdenacion {

	private static final long serialVersionUID = 1L;

	public static final Integer NUMVERSASC = Integer.valueOf(0);
	public static final Integer NUMVERSDES = Integer.valueOf(1);

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
	public String getKeyDOrdenacion(Integer tipoDOrdenacion) {
		if (tipoDOrdenacion.equals(NUMVERSDES)) {
			String cadena = Util.addZeroesLastToCadena(String.valueOf(1), Transform.toString(getNumeroDSituacion()).length() + Integer.valueOf(1));
			Integer integerCadena = Transform.toInteger(cadena) - getNumeroDSituacion();
			return Util.addCharactersFirstToCadena(Transform.toString(integerCadena), Integer.valueOf(10), Character.valueOf('9'));
		}
		return Util.addZeroesFirstToCadena(Transform.toString(getNumeroDSituacion()), Integer.valueOf(10));
	}

}
