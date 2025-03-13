package sdai.com.sis.rednodal.datosdsistema.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractFabricaDEntidadesCFG;
import sdai.com.sis.accesoadatos.IEntidad;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.rednodal.datosdsistema.KDatosDSistema;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class FabricaDDatosDSistema extends AbstractFabricaDEntidadesCFG {

	private FabricaDDatosDSistema(NumeroDVersion numeroDVersion, Node[] nodes) throws Exception {
		super(numeroDVersion, nodes);
	}

	@Override
	protected IEntidad versionarElemento(Node root) throws Exception {
		String codigoDDato = DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KDatoDSistema.AtributosDEntidad.CODIGODATO);
		DatoDSistema datoDSistema = DatoDSistema.getInstancia(codigoDDato);
		if (datoDSistema == null) {
			datoDSistema = new DatoDSistema();
			datoDSistema.addNode(getNumeroDVersion(), Integer.valueOf(1), root);
			crearSituacionDDatoDSistema(datoDSistema, Integer.valueOf(1), root);
		} else {
			SituacionDDatoDSistema situacionAComparar = obtenerSituacionAComparar(datoDSistema);
			if (existenCambios(situacionAComparar, root)) {
				Integer numeroDSituacion = Integer.valueOf(datoDSistema.getSituacionesDDatoDSistema().size());
				crearSituacionDDatoDSistema(datoDSistema, numeroDSituacion, root);
				ordenarSituacionesDDatoDSistema(datoDSistema);
			}
		}
		return datoDSistema;
	}

	private void crearSituacionDDatoDSistema(DatoDSistema datoDSistema, Integer numeroDSituacion, Node root) {
		SituacionDDatoDSistema situacionDDatoDSistema = new SituacionDDatoDSistema();
		NumeroDVersion numeroDVersion = getNumeroDVersion();
		situacionDDatoDSistema.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDDatoDSistema.setDatoDSistema(datoDSistema);
		datoDSistema.setSituacionDDatoDSistema(situacionDDatoDSistema);
		datoDSistema.getSituacionesDDatoDSistema().add(situacionDDatoDSistema);
	}

	private SituacionDDatoDSistema obtenerSituacionAComparar(DatoDSistema datoDSistema) {
		NumeroDVersion numeroDVersion = getNumeroDVersion();
		List<SituacionDDatoDSistema> situacionesDDatoDSistema = datoDSistema.getSituacionesDDatoDSistema();
		Collections.sort(situacionesDDatoDSistema);
		for (SituacionDDatoDSistema situacionDDatoDSistema : situacionesDDatoDSistema) {
			NumeroDVersion numeroDVersionAux = situacionDDatoDSistema.getNumeroDVersion();
			if (NumerosDVersionUtil.isNumeroDVersionMayor(numeroDVersionAux, numeroDVersion).equals(Boolean.valueOf(true))) {
				return situacionDDatoDSistema;
			}
		}
		return situacionesDDatoDSistema.get(situacionesDDatoDSistema.size() - 1);
	}

	private Boolean existenCambios(SituacionDDatoDSistema situacionDDatoDSistema, Node root) {
		if (!situacionDDatoDSistema.getDescripcionDDato().equals(DocumentoXML.getStringValueNodeDescendencia(root, KDatosDSistema.KSituacionDDatoDSistema.AtributosDEntidad.DESCRDDATO)))
			return Boolean.valueOf(true);
		if (!situacionDDatoDSistema.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	private void ordenarSituacionesDDatoDSistema(DatoDSistema datoDSistema) {
		List<NumeroDVersion> numerosDVersion = new ArrayList<NumeroDVersion>();
		List<SituacionDDatoDSistema> situacionesDDatoDSistema = datoDSistema.getSituacionesDDatoDSistema();
		for (SituacionDDatoDSistema situacionDDatoDSistema : situacionesDDatoDSistema) {
			NumeroDVersion numeroDVersion = situacionDDatoDSistema.getNumeroDVersion();
			numerosDVersion.add(numeroDVersion);
		}
		Collections.sort(numerosDVersion);
		for (Integer i = Integer.valueOf(1); i <= numerosDVersion.size(); i++) {
			NumeroDVersion numeroDVersion = numerosDVersion.get(i - 1);
			Long identificador = numeroDVersion.getIdentificador();
			SituacionDDatoDSistema situacionDDatoDSistema = getSituacionDDatoDSistema(identificador, situacionesDDatoDSistema);
			situacionDDatoDSistema.setNumeroDSituacion(i);
		}
	}

	private SituacionDDatoDSistema getSituacionDDatoDSistema(Long identificador, List<SituacionDDatoDSistema> situacionesDDatoDSistema) {
		for (SituacionDDatoDSistema situacionDDatoDSistema : situacionesDDatoDSistema) {
			NumeroDVersion numeroDVersion = situacionDDatoDSistema.getNumeroDVersion();
			Long identificadorAux = numeroDVersion.getIdentificador();
			if (identificadorAux.equals(identificador))
				return situacionDDatoDSistema;
		}
		return null;
	}

}
