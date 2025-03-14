package sdai.com.sis.rednodal.nodos.accesoadatos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.accesoadatos.AbstractFabricaDEntidadesCFG;
import sdai.com.sis.accesoadatos.IEntidad;
import sdai.com.sis.aplicaciones.KAplicaciones;
import sdai.com.sis.rednodal.nodos.KNodos;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public final class FabricaDNodos extends AbstractFabricaDEntidadesCFG {

	private FabricaDNodos(NumeroDVersion numeroDVersion, Node[] nodes) throws Exception {
		super(numeroDVersion, nodes);
	}

	@Override
	protected IEntidad versionarElemento(Node root) throws Exception {
		String codigoDNodo = DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KNodo.AtributosDEntidad.CODIGONODO);
		Nodo nodo = Nodo.getInstancia(codigoDNodo);
		if (nodo == null) {
			nodo = new Nodo();
			nodo.addNode(getNumeroDVersion(), Integer.valueOf(1), root);
			crearSituacionDNodo(nodo, Integer.valueOf(1), root);
		} else {
			SituacionDNodo situacionAComparar = obtenerSituacionAComparar(nodo);
			if (existenCambios(situacionAComparar, root)) {
				Integer numeroDSituacion = Integer.valueOf(nodo.getSituacionesDNodo().size());
				crearSituacionDNodo(nodo, numeroDSituacion, root);
				ordenarSituacionesDDatoDSistema(nodo);
			}
		}
		return nodo;
	}

	private void crearSituacionDNodo(Nodo nodo, Integer numeroDSituacion, Node root) {
		SituacionDNodo situacionDNodo = new SituacionDNodo();
		NumeroDVersion numeroDVersion = getNumeroDVersion();
		situacionDNodo.addNode(numeroDVersion, numeroDSituacion, root);
		situacionDNodo.setNodo(nodo);
		nodo.setSituacionDNodo(situacionDNodo);
		nodo.getSituacionesDNodo().add(situacionDNodo);
	}

	private SituacionDNodo obtenerSituacionAComparar(Nodo nodo) {
		NumeroDVersion numeroDVersion = getNumeroDVersion();
		List<SituacionDNodo> situacionesNodo = nodo.getSituacionesDNodo();
		Collections.sort(situacionesNodo);
		for (SituacionDNodo situacionDNodo : situacionesNodo) {
			NumeroDVersion numeroDVersionAux = situacionDNodo.getNumeroDVersion();
			if (NumerosDVersionUtil.isNumeroDVersionMayor(numeroDVersionAux, numeroDVersion).equals(Boolean.valueOf(true))) {
				return situacionDNodo;
			}
		}
		return situacionesNodo.get(situacionesNodo.size() - 1);
	}

	private Boolean existenCambios(SituacionDNodo situacionDNodo, Node root) {
		if (!situacionDNodo.getDescripcionDNodo().equals(DocumentoXML.getStringValueNodeDescendencia(root, KNodos.KSituacionDNodo.AtributosDEntidad.DESCRDNODO)))
			return Boolean.valueOf(true);
		if (!situacionDNodo.getSwEntidadActiva().equals(DocumentoXML.getBooleanValueNodeDescendencia(root, KAplicaciones.AtributosDEntidad.SWENTACTIV)))
			return Boolean.valueOf(true);
		return Boolean.valueOf(false);
	}

	private void ordenarSituacionesDDatoDSistema(Nodo nodo) {
		List<NumeroDVersion> numerosDVersion = new ArrayList<NumeroDVersion>();
		List<SituacionDNodo> situacionesDNodo = nodo.getSituacionesDNodo();
		for (SituacionDNodo situacionDNodo : situacionesDNodo) {
			NumeroDVersion numeroDVersion = situacionDNodo.getNumeroDVersion();
			numerosDVersion.add(numeroDVersion);
		}
		Collections.sort(numerosDVersion);
		for (Integer i = Integer.valueOf(1); i <= numerosDVersion.size(); i++) {
			NumeroDVersion numeroDVersion = numerosDVersion.get(i - 1);
			Long identificador = numeroDVersion.getIdentificador();
			SituacionDNodo situacionDNodo = getSituacionDNodo(identificador, situacionesDNodo);
			situacionDNodo.setNumeroDSituacion(i);
		}
	}

	private SituacionDNodo getSituacionDNodo(Long identificador, List<SituacionDNodo> situacionesDNodo) {
		for (SituacionDNodo situacionDNodo : situacionesDNodo) {
			NumeroDVersion numeroDVersion = situacionDNodo.getNumeroDVersion();
			Long identificadorAux = numeroDVersion.getIdentificador();
			if (identificadorAux.equals(identificador))
				return situacionDNodo;
		}
		return null;
	}

}
