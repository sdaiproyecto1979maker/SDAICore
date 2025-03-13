package sdai.com.sis.versionado.versionesCFG;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Node;

import sdai.com.sis.versionado.elementosCFG.ElementosCFG;
import sdai.com.sis.versionado.numerosdversion.NumerosDVersionUtil;
import sdai.com.sis.versionado.numerosdversion.accesoadatos.NumeroDVersion;
import sdai.com.sis.versionado.proyectosdaplicacion.IProyecto;
import sdai.com.sis.versionado.proyectosdaplicacion.accesoadatos.ProyectoDAplicacion;
import sdai.com.sis.xml.DocumentoXML;

/**
 * @date 13/03/2025
 * @since VERSIONDCOREENCURSO
 * @author Sergio_M
 */
public abstract class AbstractVersionCFG implements IVersionCFG {

	private static final String PATH = "/com/sis/versionado/versionesCFG/xml/VERSIONCFG.xml";
	private static final String VERSIONCFG = "VERSIONCFG";
	private static final String NUMVERSION = "NUMVERSION";

	private final IProyecto proyecto;
	private final List<NumeroDVersion> numerosDVersion;
	private final List<NumeroDVersion> numerosDVersionInstalables;

	protected AbstractVersionCFG(IProyecto proyecto) {
		this.proyecto = proyecto;
		this.numerosDVersion = new ArrayList<NumeroDVersion>();
		this.numerosDVersionInstalables = new ArrayList<NumeroDVersion>();
	}

	@Override
	public void createNumerosDVersion() throws Exception {
		Node[] versiones = getVersiones();
		for (Node version : versiones) {
			String numeroDVersion = DocumentoXML.getStringValueNodeDescendencia(version, NUMVERSION);
			NumeroDVersion instancia = NumeroDVersion.getInstancia(numeroDVersion, this.proyecto);
			if (instancia == null) {
				String codigoDProyectoDAplicacion = proyecto.getCodigoDProyecto();
				ProyectoDAplicacion proyectoDAplicacion = ProyectoDAplicacion.getInstancia(codigoDProyectoDAplicacion);
				NumeroDVersion.createInstancia(proyecto, proyectoDAplicacion);
				instancia = NumeroDVersion.getInstancia(numeroDVersion, this.proyecto);
			}
			this.numerosDVersion.add(instancia);
		}
		ordenarNumerosDVersion();
	}

	private Node[] getVersiones() throws Exception {
		String pack = this.proyecto.getPackageDProyecto();
		String path = pack.concat(PATH);
		InputStream inputStream = AbstractVersionCFG.class.getResourceAsStream(path);
		DocumentoXML documentoXML = new DocumentoXML(inputStream);
		Node root = documentoXML.getRoot();
		Node[] nodes = DocumentoXML.getDescendencia(root, VERSIONCFG);
		return nodes;
	}

	private void ordenarNumerosDVersion() throws Exception {
		Collections.sort(this.numerosDVersion);
		for (Integer i = Integer.valueOf(1); i <= this.numerosDVersion.size(); i++) {
			NumeroDVersion numeroDVersion = this.numerosDVersion.get(i - 1);
			numeroDVersion.setNumeroDSituacion(i);
			NumeroDVersion.updateInstancia(numeroDVersion);
		}
	}

	@Override
	public void establecerVersionesInstalables() {
		for (NumeroDVersion numeroDVersion : this.numerosDVersion) {
			Boolean swInstalada = numeroDVersion.getSwInstalada();
			if (swInstalada.equals(Boolean.valueOf(false)))
				this.numerosDVersionInstalables.add(numeroDVersion);
		}
	}

	@Override
	public void versionar() throws Exception {
		for (NumeroDVersion numeroDVersion : this.numerosDVersionInstalables) {
			String numeroVersion = NumerosDVersionUtil.getCadenaNumeroDVersion(numeroDVersion);
			String pack = this.proyecto.getPackageDProyecto();
			String path = pack.concat(PATH);
			path = path.replace(VERSIONCFG, numeroVersion);
			InputStream inputStream = AbstractVersionCFG.class.getResourceAsStream(path);
			DocumentoXML documentoXML = new DocumentoXML(inputStream);
			Node root = documentoXML.getRoot();
			ElementosCFG.getInstancia().instalarVersion(numeroDVersion, root);
		}
	}

	public IProyecto getProyecto() {
		return proyecto;
	}

}
